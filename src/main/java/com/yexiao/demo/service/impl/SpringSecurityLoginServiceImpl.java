package com.yexiao.demo.service.impl;

import com.yexiao.demo.conf.interceptor.exception.CustomizeException;
import com.yexiao.demo.conf.security.springsecurity.PasswordSecurity;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.LoginService;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author xuhf
 * @date 2021/3/18 9:48
 **/
@Service
public class SpringSecurityLoginServiceImpl implements LoginService {

    @Autowired
    private PasswordSecurity passwordSecurity;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDO login(String username, String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getPrincipal() instanceof UserDO){
            throw new CustomizeException("请先退出原用户！");
        }
        //  用户登入
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username,password);
            // 设置认证方式
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(userService);
            daoAuthenticationProvider.setPasswordEncoder(passwordSecurity);
            // 进行认证
            Authentication authenticate = daoAuthenticationProvider.authenticate(authenticationToken);
            // 塞进SecurityContextHolder中
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            emptyContext.setAuthentication(authenticate);
            SecurityContextHolder.setContext(emptyContext);
        }catch (Exception e){
            throw new CustomizeException("用户名或密码错误！");
        }
        return (UserDO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
