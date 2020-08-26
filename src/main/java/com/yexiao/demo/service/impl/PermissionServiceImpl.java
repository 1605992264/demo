package com.yexiao.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.PermissionDO;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.mapper.PermissionMapper;
import com.yexiao.demo.mapper.RoleMapper;
import com.yexiao.demo.service.PermissionService;
import com.yexiao.demo.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 11:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionDO> implements PermissionService {


    @Override
    public List<PermissionDO> getPermissionList(String roleId) {
        return baseMapper.getPermissionList(roleId);
    }
}
