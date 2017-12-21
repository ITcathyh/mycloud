package hyh.controller;

import hyh.action.DownloadAction;
import hyh.action.RecordAction;
import hyh.global.Variable;
import hyh.action.FileAction;
import hyh.action.UserAction;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@RestController
public class FileController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;
    @Autowired
    private DownloadAction downloadaction;
    @Autowired
    private RecordAction recordaction;

    @RequestMapping("/user/uploadfile")
    public String uploadFile(@RequestParam("file") CommonsMultipartFile uploadfile,
                             HttpServletRequest request, HttpSession session) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        String summary, name, type, tag;

        if (uploadfile == null || !UserAction.filterBadAction(mRequest)) {
            return "error";
        }

        try {
            name = mRequest.getParameter("name");
            tag = mRequest.getParameter("tag");
            summary = mRequest.getParameter("summary");
            type = mRequest.getParameter("type");

            if (!UserAction.checkNull(summary, name, tag, type)) {
                return "notfound";
            }
        } catch (Exception e) {
            return "error";
        }

        UserFile userfile = new UserFile();

        try {
            String path = FileAction.getFilePath() + type + "/";
            long userid = (long) session.getAttribute("userid");
            int size = (int) uploadfile.getSize();

            userfile.setFilename(name);
            userfile.setSize(size);
            userfile.setType((FileAction.getType(type)));
            userfile.setUserid(userid);
            userfile.setSummary(summary);
            userfile.setTag(tag);
            userfile.setPath(path + uploadfile.getOriginalFilename());
            userfile.setOriginname(uploadfile.getOriginalFilename());

            synchronized (Long.toString(userid)) {
                User user = userservice.getById(userid);

                if (user == null) {
                    return "error";
                } else if (size > user.getSurplus()) {
                    return "full";
                }

                if (FileAction.transferFile(path, uploadfile) &&
                        userfileservice.add(userfile) == 1) {
                    user.setSurplus(user.getSurplus() - size);

                    if (userservice.update(user) == 1) {
                        return "done";
                    } else {
                        userfileservice.deleteById(userfile.getId());
                        return "error";
                    }
                } else {
                    return "error";
                }
            }
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
            e.printStackTrace();
        }

        new File(userfile.getPath()).deleteOnExit();
        return "error";
    }

    @RequestMapping("/download/{id}")
    public String download(@PathVariable("id") String fileid,
                           HttpServletResponse response, HttpSession session) {
        long id;

        try {
            id = Long.valueOf(fileid);
        } catch (Exception e) {
            return "error";
        }

        UserFile userfile = userfileservice.getById(id);

        if (userfile == null) {
            return "notexist";
        }

        File file = new File(userfile.getPath());

        if (!file.exists()) {
            return "notexist";
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + userfile.getOriginname());

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
            return "error";
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (os != null) {
                    os.close();
                }
            } catch (Exception ignored) {
            }
        }

        downloadaction.updateDownloadInfo(userfile, userfileservice, userservice, session.getAttribute("user"));
        recordaction.addRecord(session.getAttribute("userid"), userfile.getTag(), userfile.getType());
        return "done";
    }
}
