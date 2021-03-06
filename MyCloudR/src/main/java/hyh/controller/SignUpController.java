package hyh.controller;

import hyh.action.UserAction;
import hyh.entity.Invitationcode;
import hyh.entity.User;
import hyh.service.local.InvitationcodeService;
import hyh.service.local.UserService;
import hyh.util.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 用户注册相关操作controller
 * @author ITcathyh
 * @version 1.0
 */
@RestController
public class SignUpController {
    @Autowired
    private UserService userservice;
    @Autowired
    private InvitationcodeService invitationcodeservice;

    @RequestMapping("/checksignup")
    public String checkSignup(String email, String password,
                              String name, String qq,
                              String invitationcode, HttpSession session,
                              HttpServletRequest request) {
        if (session.getAttribute("signlock") != null) {
            return "lock";
        } else if (!UserAction.checkNull(email, password, name, qq, invitationcode)) {
            return "error";
        } else if (userservice.isExist(email, name)) {
            return "isexist";
        }

        User user = new User();
        Invitationcode invitcode = invitationcodeservice.getByCode(invitationcode);

        if (invitcode == null) {
            return "invitcodeerror";
        } else if (invitcode.getSurplus() <= 0) {
            return "ivitcodefull";
        }

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setQq(qq);
        user.setInvitationid(invitcode.getId());
        user.setIp(Ip.getIp(request));

        /* 防止恶意post */
        if (!UserAction.checkUserInfo(user)) {
            session.setAttribute("signlock", 1);
            return "lock";
        }

        if (UserAction.addUser(user, invitcode, userservice, invitationcodeservice)) {
            return "done";
        }

        return "error";
    }
}
