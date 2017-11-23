package hyh.action;

import hyh.service.UserService;
import hyh.util.Ip;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserAction {
    public static boolean filterBadAction(HttpServletRequest request){
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
}
