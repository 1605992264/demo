package com.yexiao.demo.conf.security.springsecurity;


import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.conf.ConfigParams;
import com.yexiao.demo.conf.interceptor.exception.CustomizeException;
import com.yexiao.demo.service.UserService;
import com.yexiao.demo.service.impl.UserServiceImpl;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2021/3/15 14:12
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private ConfigParams configParams;

    public WebSecurityConfig(UserServiceImpl userSecurityService
            , PasswordSecurity passwordEncoder, ConfigParams configParams){
        this.userService = userSecurityService;
        this.passwordEncoder = passwordEncoder;
        this.configParams = configParams;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginProcessingUrl("login")
                .and()
                .authorizeRequests()
                .mvcMatchers(configParams.getWebInterceptors().ExcludePathPatternsToArray()).permitAll()
                .anyRequest().access("isAuthenticated()")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        throw new CustomizeException(e.getMessage());
                    }
                });

        super.configure(http);
    }
}
