package hyh.controller;

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

        return "homepage";
    }

    @RequestMapping("/search")
    public String getToSearchpage(String text, HttpSession session, HttpServletRequest request) {
        List<UserFile> userfiles = null;
        int page = 0;

        try {
            page = Integer.valueOf(request.getParameter("page"));
        } catch (Exception e) {
        }

        if (text != null) {
            userfiles = userfileservice.search(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE, text);

            if (userfiles.size() == 0) {
                userfiles = null;
            }
        }

        request.setAttribute("files", userfiles);
        setLoginBox(session, request);

        return "searchpage";
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
