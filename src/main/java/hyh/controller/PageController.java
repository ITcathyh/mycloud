package hyh.controller;

import hyh.action.FileAction;
import hyh.action.PushAction;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.UserFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    private UserFileService userfileservice;
    private static final int MAX_PAGE_PER_SIZE = 12;

    @RequestMapping("/")
    public String getToHomepage(HttpSession session, HttpServletRequest request) {
        setLoginBox(session, request);
        request.setAttribute("hots", PushAction.getHot());

        return "homepage";
    }

    @RequestMapping("/search")
    public String getToSearchpage(String text, String type,
                                  HttpSession session, HttpServletRequest request) {
        List<UserFile> userfiles = null;
        int page = 0;

        try {
            page = Integer.valueOf(request.getParameter("page"));
        } catch (Exception ignored) {
        }

        if (type != null &&  !type.equals("All") && text != null){
            userfiles = userfileservice.searchByType(FileAction.getType(type),page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1, text);
        } else if (text != null) {
            userfiles = userfileservice.search(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1, text);
        }

        if (userfiles == null || userfiles.size() < 13) {
            request.setAttribute("nextdisabled", 1);
        } else {
            request.setAttribute("nextdisabled", 0);
        }

        request.setAttribute("files", userfiles);
        request.setAttribute("page", page);
        request.setAttribute("text", text);
        setLoginBox(session, request);

        return "searchpage";
    }

    @RequestMapping("/signup")
    public String getToSignup(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "forward:/";
        }

        return "signup";
    }

    @RequestMapping("/login")
    public String getToLoginPage(HttpServletRequest request, HttpSession session) {
        if (session.getAttribute("user") == null) {
            request.setAttribute("loginurl", "/checklogin");
            request.setAttribute("successurl", "/");
            return "login";
        } else {
            return "forward:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.removeAttribute("userid");
        session.removeAttribute("admin");

        return "redirect:/login";
    }


    @RequestMapping("/user")
    public String getToUserpage(HttpServletRequest request) {
        request.setAttribute("notice", PushAction.getNotice());

        return "user";
    }

    @RequestMapping("/user/files")
    public String getToUserfiles(HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("user");

        request.setAttribute("files", userfileservice.getByUserid(user.getId()));
        return "userfiles";
    }

    @RequestMapping("/user/file")
    public String FileDetail(HttpServletRequest request){
        UserFile userfile;

        try {
            userfile = userfileservice.getById(Long.valueOf(request.getParameter("fileid")));
        }catch (Exception e){
            return "redirect:/user/files";
        }

        if (userfile == null){
            return "redirect:/user/files";
        }

        request.setAttribute("file",userfile);
        request.setAttribute("filetype", FileAction.fileTypeToString(userfile.getType()));

        return "file";
    }

    private final void setLoginBox(HttpSession session, HttpServletRequest request) {
        Object obj = session.getAttribute("user");

        if (obj == null) {
            request.setAttribute("username", "0");
        } else {
            request.setAttribute("username", ((User) obj).getName());
        }
    }
}
