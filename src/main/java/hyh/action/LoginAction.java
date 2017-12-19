package hyh.action;

import hyh.entity.Admin;
import hyh.entity.User;
import hyh.global.Variable;
import hyh.service.AdminService;
import hyh.service.UserService;
import hyh.util.Ip;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class LoginAction {
    private static Logger log = Logger.getLogger(LoginAction.class);

    public static void updateLoginInfor(User user, UserService userservice, HttpServletRequest request) {
        user.setIp(Ip.getIp(request));
        user.setLogintime(new Timestamp(System.currentTimeMillis()));

        try {
            userservice.update(user);
        } catch (Exception e) {
            log.error(e);
            Variable.errornum++;
        }
    }

    public static void updateLoginInfor(Admin user, AdminService adminservice, HttpServletRequest request) {
        user.setIp(Ip.getIp(request));
        user.setLogintime(new Timestamp(System.currentTimeMillis()));

        try {
            adminservice.updateLoginInfor(user);
            ;
        } catch (Exception e) {
            log.error(e);
            Variable.errornum++;
        }
    }
}
