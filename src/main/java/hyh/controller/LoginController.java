package hyh.controller;

import hyh.action.DailyInfoAction;
import hyh.action.LoginAction;
import hyh.entity.DailyInfo;
import hyh.entity.User;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/checklogin")
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session, HttpServletRequest request) {
        if (session.getAttribute("user") == null) {
            if (session.getAttribute("loginlock") != null) {
                return "lock";
            }

            User user = userservice.login(email, password);

            if (user == null) {
                Object obj = session.getAttribute("errortime");

                if (obj == null) {
                    session.setAttribute("errortime", 1);
                } else {
                    int time = (Integer) obj;

                    if (time > 2) {
                        session.setAttribute("loginlock", 1);
                        session.removeAttribute("errortime");
                        return "loginlock";
                    } else {
                        session.setAttribute("errortime", ++time);
                    }
                }

                return "error";
            } else {
                LoginAction.updateLoginInfor(user, userservice, request);

                user.setFilecount(userfileservice.getCountByUserid(user.getId()));
                session.setAttribute("user", user);
                session.setAttribute("userid", user.getId());
                DailyInfoAction.setActive(DailyInfoAction.getActive() + 1);
                return "done";
            }
        } else {
            return "error";
        }
    }

}
