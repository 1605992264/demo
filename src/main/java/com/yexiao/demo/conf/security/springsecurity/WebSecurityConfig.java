package com.yexiao.demo.conf.security.springsecurity;

import com.yexiao.demo.conf.ConfigParams;
import com.yexiao.demo.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

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
        http.sessionManagement()
                // 现在同一账号只能在一个地方登入
                .maximumSessions(1)
                .and().and()
                // 不启用csrf定牌
                .csrf().disable()
                // 设置登入登出接口
                .logout().logoutUrl("logout")
                .and()
                .formLogin().loginProcessingUrl("login")
                .and()
                // 对请求做过滤
                .authorizeRequests()
                .mvcMatchers(configParams.getWebInterceptors().ExcludePathPatternsToArray()).permitAll()
                .anyRequest().access("isAuthenticated()")
                .and();
//                .exceptionHandling()
//                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());

        super.configure(http);
    }
}
