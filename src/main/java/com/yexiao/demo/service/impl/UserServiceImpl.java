package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.conf.interceptor.ErrorMethodException;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.mapper.UserMapper;
import com.yexiao.demo.service.UserService;
import com.yexiao.demo.utils.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author xuhf
 * @date 2020/8/20 11:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 查询列表
     * @return*/
    @Override
    public IPage<UserDO> page(IPage<UserDO> basePage, UserDO userDO){
        QueryWrapper<UserDO> wrapper = new QueryWrapper(userDO);
        IPage<UserDO> userDOIPage = baseMapper.selectPage(basePage, wrapper);
        return userDOIPage;
    }

    @Override
    public UserDO findUserInfo(String id) {
        UserDO userInfo = baseMapper.findUserInfo(id);
        return userInfo;
    }

    @Override
    public UserDO findUserByUserName(String username) {
         return baseMapper.findUserByName(username);
    }

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
        // 由前端一起加密更为安全
        UserDO userByUserName = findUserByUserName(username);
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
            // 把sessionId 存到redis中
        }catch (IncorrectCredentialsException e){
            throw new RuntimeException("用户名或密码错误！");
        }
        return UserUtils.getUser();
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

    /**
     * 获取当前在线用户
     * */
    @Override
    public List<UserDO> onlineUsers() {
        //todo: 需要的功能 分页  定时删除
        List<UserDO> userDOList = new LinkedList<>();
        Collection<Session> activeSessions = sessionDAO.getActiveSessions();
        activeSessions.forEach(session -> {
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            userDOList.add((UserDO)coll.getPrimaryPrincipal());
        });
        return userDOList;
    }

    /**
     * 强制登出
     * @param sessionId 要登出用户的sessionId(token)
     * */
    @Override
    public void forcedLogout(String sessionId) {
        //todo: 需要的功能 分页  定时删除
        Session session = sessionDAO.readSession(sessionId);
        sessionDAO.delete(session);
    }


}
