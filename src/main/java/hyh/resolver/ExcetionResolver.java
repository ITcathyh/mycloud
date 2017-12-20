package hyh.resolver;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcetionResolver implements HandlerExceptionResolver {
    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        log.error(e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/jsp/error.jsp");

        return modelAndView;
    }
}
