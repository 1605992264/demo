package com.yexiao.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.UserDO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 10:59
 **/
public interface UserService extends IService<UserDO> {

    IPage<UserDO> page(IPage<UserDO> basePage, UserDO userDO);

    UserDO findUserInfo(String id);

    /**
     * 物理删除
     * */
    boolean deleteById(String id);

    UserDO login(String username,String password);

    void logout();

    /**
     * 验证登录名唯一性
     * */
    boolean verificationUserName(String name);

    boolean register(UserDO userDO);

    /**
     * 获取在线人数
     * */
    List<UserDO> onlineUsers();

    /**
     * 强制登出
     * @param sessionId 要登出的用户token
     * */
    void forcedLogout(String sessionId);
}
