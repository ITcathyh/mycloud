package hyh.action;

import hyh.entity.Admin;
import hyh.entity.Invitationcode;
import hyh.entity.User;
import hyh.Info.Variable;
import hyh.service.AdminService;
import hyh.service.InvitationcodeService;
import hyh.service.UserService;
import hyh.util.Ip;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Pattern;
/**
 * 用户登录相关行为
 * @author ITcathyh
 * @version 1.0
 */
public final class UserAction {
    private static Logger log = Logger.getLogger(UserAction.class);
    private static final String EMAIL_PAT = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$";
    private static final String PWD_PAT = "^[a-zA-z][a-zA-Z0-9.-]{5,16}";
    private static final String QQ_PAT = "^[1-9][0-9]{4,}";
    private static final String NAME_PAT = "^[\\u4e00-\\u9fa5a-zA-Z0-9.-]{4,12}$";

    public static boolean filterBadAction(HttpServletRequest request) {
        String token = request.getParameter("token");
        Object sessiontoke = request.getSession().getAttribute("csrftoken");

        return token != null && sessiontoke != null && sessiontoke.toString().equals(token);
    }

    public static boolean checkNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                return false;
            }
        }

        return true;
    }

    public static void filter(List<Object> list, Object... objects) {
        for (Object obj : objects) {
            if (obj != null) {
                list.add(obj);
            }
        }
    }

    public static synchronized boolean addUser(User user, Invitationcode invitcode,
                                               UserService userservice, InvitationcodeService invitationcodeservice) {
        try {
            if (userservice.add(user) == 1) {
                invitcode.setSurplus((short) (invitcode.getSurplus() - 1));

                return invitationcodeservice.update(invitcode) == 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean checkUserInfo(User user) {
        return Pattern.matches(EMAIL_PAT, user.getEmail()) &&
                checkPwd(user.getPassword()) &&
                Pattern.matches(QQ_PAT, user.getQq()) &&
                Pattern.matches(NAME_PAT, user.getName());
    }

    public static boolean checkPwd(String pwd){
        return Pattern.matches(PWD_PAT, pwd);
    }

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
