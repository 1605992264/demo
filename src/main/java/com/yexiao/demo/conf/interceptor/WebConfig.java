package com.yexiao.demo.conf.interceptor;

import com.yexiao.demo.conf.ConfigParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * @author xuhf
 * @date 2020/8/26 15:36
 * 拦截配置
 * */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 拦截处理器
     *  */
    @Autowired
    private MyHandlerInterceptor myHandlerInterceptor;
    @Autowired
    private NoCacheHandler noCacheHandler;

    private ConfigParams configParams;

    public WebConfig(ConfigParams configParams){
        this.configParams = configParams;
    }

    /***
     * 添加链式处理器
     * 以添加拦截顺序来执行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noCacheHandler);
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(myHandlerInterceptor);
        // 设置不拦截的路径
        interceptorRegistration.excludePathPatterns(
                configParams.getWebInterceptors().getExcludePathPatterns());
        // 设置拦截路径
        interceptorRegistration.addPathPatterns("/**");
    }


}
