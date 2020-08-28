package com.yexiao.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.UserDO;

/**
 * @author xuhf
 * @date 2020/8/20 10:59
 **/
public interface UserService extends IService<UserDO> {

    /**
     * 物理删除
     * */
    boolean deleteById(String id);

    UserDO login(String username,String password);

    void logout();

}
