package hyh.controller;

import hyh.global.Variable;
import hyh.action.FileAction;
import hyh.action.UserAction;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import hyh.service.UserService;
import hyh.util.TimeUtil;
import hyh.util.Zip;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;

@Controller
public class FileController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/uploadfile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") CommonsMultipartFile uploadfile,
                             HttpServletRequest request, HttpSession session) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        int userid;
        short type;
        User user;
        String summary;

        if (uploadfile == null || !UserAction.filterBadAction(mRequest)) {
            return "error";
        }

        try {
            userid = Integer.valueOf(mRequest.getParameter("userid"));
            type = Short.valueOf(mRequest.getParameter("type"));
            user = userservice.getById(userid);
            summary = mRequest.getParameter("content");

            if (user == null) {
                return "notfound";
            }
        } catch (Exception e) {
            return "error";
        }

        String path = FileAction.getFilePath() + userid + "/";

        if (!FileAction.uploadFile(path,uploadfile)){
            return "error";
        }

        try {
            UserFile userfile = new UserFile();
            int size = (int)uploadfile.getSize();

            if (size > user.getSurplus()) {
                return "full";
            }

            userfile.setFilename(uploadfile.getName());
            userfile.setSize(size);
            userfile.setType(type);
            userfile.setUserid(userid);
            userfile.setSummary(summary);
            userfile.setPath(path + uploadfile.getName());

            if (userfileservice.add(userfile) == 1) {
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

        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
            return "error";
        }
    }

    @RequestMapping("/download")
    public String download(@RequestParam("type") String type, @RequestParam("id") long id,
                           HttpServletResponse response) {
        try {
            UserFile userfile = userfileservice.getById(id);

            if (userfile == null) {
                return "notexist";
            }

            File file = new File(userfile.getPath());

            if (!file.exists()) {
                return "error";
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + userfile.getFilename());

            InputStream inputStream = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;

            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            os.close();
            inputStream.close();
        } catch (Exception e) {
            Variable.errornum++;
            log.error(e);
        }

        return null;
    }
}
