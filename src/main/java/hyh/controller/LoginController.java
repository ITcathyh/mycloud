package hyh.controller;

import hyh.entity.User;
import hyh.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userservice;

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");

        return "redirect:/login";
    }

    @RequestMapping("/checklogin")
    @ResponseBody
    public String checkLogin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session) {
        if (session.getAttribute("user") == null ) {
            if ( session.getAttribute("loginlock") != null){
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
                session.setAttribute("user", user);
                session.setAttribute("userid", user.getId());
                return "done";
            }
        } else {
            return "error";
        }
    }

}
