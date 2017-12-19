package hyh.action;

import hyh.entity.Invitationcode;
import hyh.entity.User;
import hyh.service.InvitationcodeService;
import hyh.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

public final class UserAction {
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

}
