package hyh.controller;

import hyh.entity.PushContent;
import hyh.entity.User;
import hyh.service.local.UserFileService;
import hyh.service.local.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static hyh.action.PushAction.*;
/**
 * 内容推送相关操作controller
 * @author ITcathyh
 * @version 1.0
 */
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
