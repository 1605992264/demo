package com.yexiao.demo.conf.interceptor;

import com.yexiao.demo.conf.interceptor.MyHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截配置
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截处理器
     *  */
    @Autowired
    private MyHandlerInterceptor myHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myHandlerInterceptor);
        // 设置不拦截的路径
        interceptorRegistration.excludePathPatterns("/error");
        interceptorRegistration.excludePathPatterns("/static/**");
        interceptorRegistration.excludePathPatterns("/login");
        interceptorRegistration.excludePathPatterns("/logout");
        interceptorRegistration.excludePathPatterns("/test/**");
        interceptorRegistration.excludePathPatterns("/generator/**");
        // 设置拦截路径
        interceptorRegistration.addPathPatterns("/**");
    }


}
