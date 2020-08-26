package com.yexiao.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.mapper.RoleMapper;
import com.yexiao.demo.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 11:00
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleDO> implements RoleService {


    @Override
    public List<RoleDO> getRoleList(String userId) {
        return baseMapper.getRoleList(userId);
    }
}
