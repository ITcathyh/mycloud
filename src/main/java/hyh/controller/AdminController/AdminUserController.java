package hyh.controller.AdminController;

import hyh.entity.Invitationcode;
import hyh.entity.User;
import hyh.service.InvitationcodeService;
import hyh.service.UserService;
import hyh.util.GetRandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 管理员相关操作controller
 * @author ITcathyh
 * @version 1.0
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private UserService userservice;
    @Autowired
    private InvitationcodeService invitationcodeservice;

    @RequestMapping("/makeinvitcode")
    public String makeInvitcode(int count) {
        String code = GetRandomString.getRandomString(20);
        Invitationcode invitationcode = new Invitationcode();

        invitationcode.setSurplus((short) count);
        invitationcode.setInvitationcode(code);

        return invitationcodeservice.add(invitationcode) == 1 ? code : null;
    }

    @RequestMapping("/deleteuser")
    public String deleteUser(long id) {
        if (userservice.deleteById(id) == 1) {
            return "done";
        }

        return "error";
    }

    @RequestMapping("/edituser")
    public String editUser(long id, String qq) {
        if (userservice.editInfo(id, qq) == 1) {
            return "done";
        }

        return "error";
    }
}
