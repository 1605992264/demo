package com.yexiao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexiao.demo.domain.RoleDO;
import com.yexiao.demo.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author xuhf
 * @date 2020/8/20 10:48
 **/
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {

    /**
     * 获取用户角色列表
     * */
    List<RoleDO> getRoleList(String userId);

}
