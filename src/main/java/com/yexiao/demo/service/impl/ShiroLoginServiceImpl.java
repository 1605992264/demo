package com.yexiao.demo.service.impl;

import com.yexiao.demo.conf.interceptor.exception.CustomizeException;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.LoginService;
import com.yexiao.demo.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

/**
 * @author xuhf
 * @date 2021/3/18 9:47
 **/
@ConditionalOnClass(name = "com.yexiao.demo.conf.security.shiro.ShiroConfig")
@Service
public class ShiroLoginServiceImpl implements LoginService {
    @Override
    public UserDO login(String username, String password) {
        // 由前端一起加密更为安全
        // 登入
        Subject subject = SecurityUtils.getSubject();
        UserDO userDO = (UserDO) subject.getPrincipal();
        if(userDO != null){
            throw new CustomizeException("请先退出原用户");
        }
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            // 把sessionId 存到redis中
        }catch (IncorrectCredentialsException e){
            throw new CustomizeException("用户名或密码错误！");
        }
        return (UserDO) subject.getPrincipal();
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
