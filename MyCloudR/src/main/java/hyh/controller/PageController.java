package hyh.controller;

import hyh.action.FileAction;
import hyh.action.PushAction;
import hyh.action.RecordAction;
import hyh.entity.User;
import hyh.entity.UserFile;
import hyh.service.local.AdvertisementService;
import hyh.service.local.UserFileService;
import hyh.service.local.UserService;
import hyh.util.TranslationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.*;

/**
 * 页面映射controller
 *
 * @author ITcathyh
 * @version 1.0
 */
@Controller
public class PageController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;
    @Autowired
    private RecordAction recordaction;
    @Autowired
    private AdvertisementService advertisementservice;
    private static final int MAX_PAGE_PER_SIZE = 12;

    @RequestMapping("/")
    public String getToHomepage(HttpSession session, HttpServletRequest request) {
        setLoginBox(session, request);
        userservice.getById(1);
        request.setAttribute("hots", PushAction.getHots(userfileservice));

        return "homepage";
    }

    @RequestMapping("/search")
    public String getToSearchpage(String text, String type, String spage,
                                  HttpSession session, HttpServletRequest request) {
        List<UserFile> userfiles = new ArrayList<>();
        int page = 0;

        try {
            page = Integer.valueOf(spage);
        } catch (Exception ignored) {
        }

        if (type == null) {
            type = "All";
        }

        if (type.length() > 0) {
            if (text != null && text.length() > 0) {
                List<String> keys = new ArrayList<>();
                String tran = TranslationUtil.zhTransToEn(text);
                keys.add(text);

                if (!tran.equals(text)) {
                    Collections.addAll(keys, Objects.requireNonNull(TranslationUtil.getWords(tran)));
                }

                Set<UserFile> fileSet = new HashSet<>();

                for (String key : keys) {
                    if (!type.equals("All")) {
                        fileSet.addAll(userfileservice.searchByType(FileAction.getType(type),
                                page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1, key));
                    } else {
                        fileSet.addAll(userfileservice.search(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1, key));
                    }
                }

                int i = 0;

                for (UserFile userFile : fileSet) {
                    if (i++ == MAX_PAGE_PER_SIZE) {
                        break;
                    }

                    userfiles.add(userFile);
                }
            } else {
                if (!type.equals("All")) {
                    userfiles = userfileservice.getByType(FileAction.getType(type),
                            page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1);
                } else {
                    userfiles = userfileservice.getAll(page * MAX_PAGE_PER_SIZE, MAX_PAGE_PER_SIZE + 1);
                }
            }
        }

        if (userfiles == null) {
            request.setAttribute("nextdisabled", 1);
            request.setAttribute("cot", 0);
        } else {
            if (userfiles.size() < 13) {
                request.setAttribute("nextdisabled", 1);
            }

            request.setAttribute("nextdisabled", 0);

            if (userfiles.size() == 13) {
                userfiles.remove(12);
            }

            request.setAttribute("cot", userfiles.size());
        }

        request.setAttribute("files", userfiles);
        request.setAttribute("page", page);
        request.setAttribute("text", text);
        request.setAttribute("type", type);
        request.setAttribute("ads", advertisementservice.getByTime(new Timestamp(System.currentTimeMillis())));
        setLoginBox(session, request);
        recordaction.addRecord(session.getAttribute("userid"), null, FileAction.getType(type));

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

        return "redirect:/";
    }


    @RequestMapping("/user")
    public String getToUserpage(HttpServletRequest request, HttpSession session) {
        User user = userservice.getById((long) session.getAttribute("userid"));

        if (user == null) {
            return "redirect:logout";
        }

        user.setFilecount(userfileservice.getCountByUserid(user.getId()));

        session.setAttribute("user", user);
        request.setAttribute("notice", PushAction.getNotice());

        return "user/user";
    }

    @RequestMapping("/user/files")
    public String getToUserfiles(String spage, String key, HttpServletRequest request, HttpSession session) {
        List<UserFile> userfiles = null;
        int page = 0;
        long userid = (long) session.getAttribute("userid");

        try {
            page = Integer.valueOf(spage);
        } catch (Exception ignored) {
        }

        if (key == null || key.length() < 1) {
            userfiles = userfileservice.getByUserid(userid, page * MAX_PAGE_PER_SIZE,
                    MAX_PAGE_PER_SIZE + 1);
        } else {
            userfiles = userfileservice.searchByUserid(userid, page * MAX_PAGE_PER_SIZE,
                    MAX_PAGE_PER_SIZE + 1, key);
        }

        if (userfiles == null || userfiles.size() < 13) {
            request.setAttribute("nextdisabled", 1);
        } else {
            request.setAttribute("nextdisabled", 0);
        }

        request.setAttribute("files", userfiles);
        request.setAttribute("page", page);
        request.setAttribute("key", key);

        return "user/userfiles";
    }

    @RequestMapping("/user/file")
    public String FileDetail(String fileid, HttpServletRequest request) {
        UserFile userfile;

        try {
            userfile = userfileservice.getById(Long.valueOf(fileid));
        } catch (Exception e) {
            return "redirect:/user/files";
        }

        if (userfile == null) {
            return "redirect:/user/files";
        }

        request.setAttribute("file", userfile);
        request.setAttribute("filetype", FileAction.fileTypeToString(userfile.getType()));

        return "user/file";
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
