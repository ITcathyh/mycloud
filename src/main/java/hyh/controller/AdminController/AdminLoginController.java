package hyh.controller.AdminController;

import hyh.action.UserAction;
import hyh.entity.Admin;
import hyh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 管理员登录相关操作controller
 * @author ITcathyh
 * @version 1.0
 */
@RestController
public class AdminLoginController {
    @Autowired
    private AdminService adminservice;

    @RequestMapping("/checkadminlogin")
    public String checkAdminLogin(String email, String password,
                                  HttpSession session, HttpServletRequest request){
        if (!UserAction.checkNull(email, password)){
            return "error";
        }

        if (session.getAttribute("admin") == null ) {
            if ( session.getAttribute("loginlock") != null){
                return "lock";
            }

            Admin user = adminservice.login(email, password);

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
                UserAction.updateLoginInfor(user, adminservice, request);

                session.setAttribute("admin", user);
                return "done";
            }
        } else {
            return "error";
        }
    }
}
