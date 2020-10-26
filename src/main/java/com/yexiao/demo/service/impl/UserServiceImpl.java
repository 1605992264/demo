package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.base.utils.UserUtils;
import com.yexiao.demo.conf.interceptor.ErrorMethodException;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.mapper.UserMapper;
import com.yexiao.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xuhf
 * @date 2020/8/20 11:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    /**
     * 物理删除
     * */
    @Override
    public boolean deleteById(String id) {
        if(baseMapper.removeById(id) >= 1){
            return true;
        }
        return false;
    }

    @Override
    public UserDO login(String username, String password) {
        // 由前端加密更为安全
        String newPassword = UserUtils.newPassword(password, username);
        // 登入
        UsernamePasswordToken token = new UsernamePasswordToken(username,newPassword);
        Subject subject = SecurityUtils.getSubject();
        UserDO userDO = (UserDO) subject.getPrincipal();
        if(userDO != null){
            throw new ErrorMethodException("请先退出原用户");
        }
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            throw new RuntimeException("用户名或密码错误！");
        }
        return (UserDO) subject.getPrincipal();
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    /**
     * 验证登入名唯一性
     * @param username 登入名
     * */
    @Override
    public boolean verificationUserName(String username) {
        Integer integer = baseMapper.verificationUserName(username);
        if(integer == null || integer.equals(0)){
            return false;
        }
        return true;
    }

    /**
     * 注册
     * */
    @Override
    public boolean register(UserDO userDO) {
        boolean flag = verificationUserName(userDO.getUsername());
        // 加密
        userDO.setPassword(UserUtils.newPassword(userDO.getPassword(),userDO.getUsername()));
        if(flag){
            int insert = baseMapper.insert(userDO);
            if (insert > 0) {
                return true;
            }
        }
        return false;
    }

}
