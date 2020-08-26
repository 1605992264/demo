package com.yexiao.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.PermissionDO;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.domain.UserDO;

import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 10:59
 **/
public interface RoleService extends IService<RoleDO> {

    /**
     * 获取用户角色列表
     * */
    List<RoleDO> getRoleList(String userId);

}
