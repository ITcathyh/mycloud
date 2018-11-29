package hyh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 访问人次统计
 */
public class MaliciousRequestInteceptor implements HandlerInterceptor {
    private static final short MIN_TIME = 1000;
    private static final short WAIT_TIME = 1000 * 5;
    private static final short MAX_FAST_REQUEST = 5;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Object timeObj = session.getAttribute("requestTime");
        boolean result = false;

        if (timeObj == null) {
            synchronized (this) {
                timeObj = session.getAttribute("requestTime");

                if (timeObj == null) {
                    session.setAttribute("requestTime", new AtomicLong(System.currentTimeMillis()));
                    session.setAttribute("requestNum", new AtomicInteger(1));
                    return true;
                }
            }
        }

        if (session.getAttribute("banObj") != null) {
            AtomicLong time = (AtomicLong) timeObj;

            if (System.currentTimeMillis() - time.get() > WAIT_TIME) {
                time.set(System.currentTimeMillis());
                ((AtomicInteger) session.getAttribute("requestNum")).set(1);
                session.removeAttribute("banObj");

                result = true;
            }
        } else {
            AtomicLong time = (AtomicLong) timeObj;
            AtomicInteger num = (AtomicInteger) session.getAttribute("requestNum");

            if (System.currentTimeMillis() - time.get() >= MIN_TIME) {
                num.set(1);
                result = true;
            } else if (num.incrementAndGet() > MAX_FAST_REQUEST) {
                session.setAttribute("banObj", true);
            } else {
                result = true;
            }

            time.set(System.currentTimeMillis());
        }

        if (!result) {
            httpServletResponse.sendRedirect("error");
        }

        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}