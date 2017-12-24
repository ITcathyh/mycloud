package hyh.controller.AdminController;

import hyh.action.DailyInfoAction;
import hyh.action.FileAction;
import hyh.action.PushAction;
import hyh.entity.Advertisement;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.AdvertisementService;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class AdminPageController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;
    @Autowired
    private AdvertisementService advertisementservice;
    private static final int MAX_PAGE_PER_SIZE = 12;

    @RequestMapping("/adminlogin")
    public String adminLoginPage(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("admin") == null) {
            request.setAttribute("loginurl", "/checkadminlogin");
            request.setAttribute("successurl", "/admin");
            return "login";
        } else {
            return "admin/admin";
        }
    }

    @RequestMapping("/admin")
    public String adminPage(HttpServletRequest request) {
        request.setAttribute("userscount", userservice.getCount());
        request.setAttribute("newusers", DailyInfoAction.getNewusers());
        request.setAttribute("active", DailyInfoAction.getActive());
        request.setAttribute("filescount", userfileservice.getCount());
        request.setAttribute("newfiles", DailyInfoAction.getNewfiles());
        request.setAttribute("downloads", userfileservice.getDownloads());
        request.setAttribute("newdownload", DailyInfoAction.getNewdownloads());
        request.setAttribute("notice", PushAction.getNotice());

        return "admin/admin";
    }

    @RequestMapping("/admin/queryusers")
    public String queryUsers(String spage, String key, HttpServletRequest request) {
        List<User> users;
        int page = 0;

        try {
            page = Integer.valueOf(spage);
        } catch (Exception ignored) {
        }

        if (key == null || key.length() < 1) {
            users = userservice.getAll(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1);
        } else {
            users = userservice.search(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1, key);
        }

        if (users == null || users.size() < 13) {
            request.setAttribute("nextdisabled", 1);
        } else {
            request.setAttribute("nextdisabled", 0);
        }

        request.setAttribute("users", users);
        request.setAttribute("page", page);
        request.setAttribute("key", key);

        return "admin/queryusers";
    }

    @RequestMapping("/admin/getuserdetail")
    public String getUserDetail(long userid, HttpServletRequest request) {
        User user = userservice.getById(userid);

        if (user == null) {
            request.setAttribute("nextdisabled", 1);
            return "admin/queryusers";
        }

        user.setFilecount(userfileservice.getCountByUserid(userid));
        request.setAttribute("suser", user);
        return "admin/userdetail";

    }

    @RequestMapping("/admin/queryfiles")
    public String queryFiles(String spage, String key, HttpServletRequest request) {
        List<UserFile> files;
        int page = 0;

        try {
            page = Integer.valueOf(spage);
        } catch (Exception ignored) {
        }

        if (key == null || key.length() < 1) {
            files = userfileservice.getAll(page * MAX_PAGE_PER_SIZE,
                    MAX_PAGE_PER_SIZE + 1);
        } else {
            Pattern pattern = Pattern.compile("[0-9]*");

            if (!pattern.matcher(key).matches()) {
                files = userfileservice.search(page * MAX_PAGE_PER_SIZE,
                        MAX_PAGE_PER_SIZE + 1, key);
            } else {
                files = userfileservice.searchByUseridOrFiledetail(page * MAX_PAGE_PER_SIZE,
                        MAX_PAGE_PER_SIZE + 1, key, Long.valueOf(key));
            }
        }

        if (files == null || files.size() < 13) {
            request.setAttribute("nextdisabled", 1);
        } else {
            request.setAttribute("nextdisabled", 0);
        }

        request.setAttribute("files", files);
        request.setAttribute("page", page);
        request.setAttribute("key", key);

        return "admin/queryfiles";
    }

    @RequestMapping("/admin/file")
    public String FileDetail(String fileid, HttpServletRequest request) {
        UserFile userfile;

        try {
            userfile = userfileservice.getById(Long.valueOf(fileid));
        } catch (Exception e) {
            return "redirect:/admin/queryfiles";
        }

        if (userfile == null) {
            return "redirect:/admin/queryfiles";
        }

        request.setAttribute("file", userfile);
        request.setAttribute("filetype", FileAction.fileTypeToString(userfile.getType()));

        return "admin/file";
    }

    @RequestMapping("/admin/queryad")
    public String getToSetAd(HttpServletRequest request){
        request.setAttribute("ads", advertisementservice.getAll());

        return "admin/queryad";
    }

    @RequestMapping("/admin/setad")
    public String getToAddAd(String id, HttpServletRequest request){
        if (id != null){
            Advertisement ad = advertisementservice.getById(Short.valueOf(id));

            if (ad == null){
                return "redirect:/admin/queryad";
            }

            request.setAttribute("href", ad.getHref());
            request.setAttribute("deadline", new Timestamp(ad.getDeadline()));
            request.setAttribute("id", ad.getId());
        }

        return "admin/setad";
    }
}
