package hyh.controller;

import hyh.entity.PushContent;
import hyh.entity.User;
import hyh.service.UserFileService;
import hyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static hyh.action.PushAction.*;

@RestController
@RequestMapping("/push")
public class PushContentController {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserFileService userfileservice;

    @RequestMapping("/getnewrecommend")
    public PushContent getNewRecommend(HttpSession session) {
        Object obj = session.getAttribute("user");
        PushContent newcon;

        if (obj != null) {
            newcon = getRecommend((User) obj, userservice);
        } else {
            newcon = getNotice();
        }

       return newcon;
    }
}
