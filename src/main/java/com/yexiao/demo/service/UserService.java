package com.yexiao.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.UserDO;

/**
 * @author xuhf
 * @date 2020/8/20 10:59
 **/
public interface UserService extends IService<UserDO> {

    IPage<UserDO> page(IPage<UserDO> basePage, UserDO userDO);

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

}
