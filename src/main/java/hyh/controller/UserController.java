package hyh.controller;

import hyh.action.FileAction;
import hyh.action.UserAction;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/editfile")
    public String EditFile(String filename, String tag,
                           String type, String summary, String id) {
        if (!UserAction.checkNull(filename, tag, type, summary, id)) {
            return "error";
        }

        UserFile file;

        try {
            file = userfileservice.getById(Long.valueOf(id));
        } catch (Exception e) {
            return "error";
        }

        if (file == null) {
            log.error("Excetional update");
            return "notfound";
        }

        file.setFilename(filename);
        file.setTag(tag);
        file.setType(FileAction.getType(type));
        file.setSummary(summary);

        if (userfileservice.update(file) == 1) {
            return "done";
        }

        return "error";
    }

    @RequestMapping("/deletefile")
    public String deleteFile(String id) {
        UserFile userfile;

        synchronized (this) {
            try {
                userfile = userfileservice.getById(Long.valueOf(id));
            } catch (Exception e) {
                return "error";
            }

            if (userfile == null) {
                return "notfound";
            } else if (userfileservice.deleteById(userfile.getId()) != 1) {
                return "error";
            }
        }

        User user = userservice.getById(userfile.getUserid());

        if (user != null) {
            user.setSurplus(user.getSurplus() - userfile.getSize());

            if (userservice.update(user) == 1) {
                return "done";
            }
        }

        return "error";
    }

    @RequestMapping("/editpwd")
    public String editPwd(String pwd, String newpwd,
                          HttpSession session, HttpServletRequest request) {
        if (UserAction.checkNull(pwd, newpwd)) {
            User user = (User) session.getAttribute("user");

            if (!user.getPassword().equals(pwd)) {
                return "pwderror";
            } else if (!UserAction.checkPwd(newpwd)) {
                return "error";
            }

            user.setPassword(newpwd);

            if (userservice.editPwd(user) == 1){
                return "done";
            }
        }

        return "error";
    }
}
