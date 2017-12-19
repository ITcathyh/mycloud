package hyh.controller.AdminController;

import hyh.action.PushAction;
import hyh.action.UserAction;
import hyh.entity.PushContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminPushContentController {
    @RequestMapping("/setnotice")
    public boolean setNotice(String content, String href){
        if (!UserAction.checkNull(content, href)){
            return false;
        }

        synchronized (this){
            PushAction.setNotice(new PushContent(content, href));
        }

        return true;
    }
}
