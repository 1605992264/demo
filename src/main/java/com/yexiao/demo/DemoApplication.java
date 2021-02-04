package com.yexiao.demo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author xuhf
 * */
@SpringBootApplication
@MapperScan({"com.yexiao.demo.mapper","com.yexiao.demo.**.mapper","com.yexiao.demo.xxx.schedule"})
//@EnableAdminServer
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ   启动成功 ");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        SpringApplicationBuilder sources = builder.sources(DemoApplication.class);
        return sources;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //以war包方式获取获取applicationContext  可以默认调用一些方法
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
}
