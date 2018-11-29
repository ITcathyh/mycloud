package hyh.config;

import hyh.interceptor.CSRFInterceptor;
import hyh.interceptor.MaliciousRequestInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置
 */
@Configuration
public class WebAdepter extends WebMvcConfigurerAdapter {

    @Bean
    public MaliciousRequestInteceptor getMaliciousRequestInteceptor() {
        return new MaliciousRequestInteceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();

        excludePathPatterns.add("/css/**");
        excludePathPatterns.add("/fonts/**");
        excludePathPatterns.add("/font-awesome/**");
        excludePathPatterns.add("/img/**");
        excludePathPatterns.add("/js/**");
        excludePathPatterns.add("/ueditor/**");

     //   registry.addInterceptor(new ErrorPageInterceptor());
        registry.addInterceptor(getMaliciousRequestInteceptor()).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
        super.addInterceptors(registry);
    }
}
