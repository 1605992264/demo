package com.yexiao.demo.service;

import com.yexiao.demo.domain.UserDO;

/**
 * @author xuhf
 * @date 2021/3/18 9:45
 **/
public interface LoginService {


    UserDO login(String username,String password);

    void logout();

}
