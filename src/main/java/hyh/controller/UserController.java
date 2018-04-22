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
/**
 * 用户行为controller
 * @author ITcathyh
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userservice;

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
