package hyh.controller.AdminController;

import hyh.action.DailyInfoAction;
import hyh.action.PushAction;
import hyh.entity.DailyInfo;
import hyh.entity.User;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPageController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;
    private static final int MAX_PAGE_PER_SIZE = 12;

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
    public String adminPage(HttpServletRequest request) {
        request.setAttribute("userscount", userservice.getCount());
        request.setAttribute("newusers", DailyInfoAction.getNewusers());
        request.setAttribute("active", DailyInfoAction.getActive());
        request.setAttribute("filescount", userfileservice.getCount());
        request.setAttribute("newfiles", DailyInfoAction.getNewfiles());
        request.setAttribute("downloads", userfileservice.getDownloads());
        request.setAttribute("newdownload", DailyInfoAction.getNewdownloads());
        request.setAttribute("notice", PushAction.getNotice());

        return "admin";
    }

    @RequestMapping("/queryusers")
    public String queryUsers(String email, HttpServletRequest request) {
        List<User> users = null;
        int page = 0;

        try {
            page = Integer.valueOf(request.getParameter("page"));
        } catch (Exception ignored) {
        }

        if (email != null){
           // userservice.
        }else {
            users = userservice.getAll(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1);
        }

        if (users == null || users.size() < 13) {
            request.setAttribute("nextdisabled", 1);
        } else {
            request.setAttribute("nextdisabled", 0);
        }

        request.setAttribute("users", users);
        request.setAttribute("page", page);

        return "queryusers";
    }
}
