package hyh.controller;

import hyh.action.UserAction;
import hyh.entity.Invitationcode;
import hyh.entity.User;
import hyh.service.InvitationcodeService;
import hyh.service.UserService;
import hyh.util.GetRandomString;
import hyh.util.Ip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping("/makeinvitcode")
    public String makeInvitcode(int count){
        String code = GetRandomString.getRandomString(20);
        Invitationcode invitationcode = new Invitationcode();

        invitationcode.setSurplus((short)count);
        invitationcode.setInvitationcode(code);

        return invitationcodeservice.add(invitationcode) == 1 ? code : null;
    }
}
