package hyh.controller.AdminController;

import hyh.action.AdminAction;
import hyh.action.PushAction;
import hyh.entity.DailyInfo;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/login")
    public String adminLoginPage(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            request.setAttribute("loginurl", "/checkadminlogin");
            request.setAttribute("successurl", "/admin");
            return "login";
        } else {
            return "forward:/admin";
        }
    }

    @RequestMapping("")
    public String adminPage(HttpServletRequest request, HttpSession session) {
        DailyInfo dailyinfo = AdminAction.getDailyifo();

        request.setAttribute("userscount", 1);
        request.setAttribute("newusers", dailyinfo.getNewusers());
        request.setAttribute("active", dailyinfo.getActive());
        request.setAttribute("filescount", 1);
        request.setAttribute("newfiles", dailyinfo.getNewfiles());
        request.setAttribute("downloads", 1);
        request.setAttribute("newdownload", dailyinfo.getNewdownloads());
        request.setAttribute("notice", PushAction.getNotice());

        return "admin";
    }
}
