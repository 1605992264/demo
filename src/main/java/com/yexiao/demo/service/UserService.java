package com.yexiao.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.UserDO;

import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 10:59
 **/
public interface UserService extends IService<UserDO> {

    List<UserDO> getList();

}
