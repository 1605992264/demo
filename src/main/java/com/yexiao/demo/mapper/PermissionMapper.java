package com.yexiao.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yexiao.demo.domain.PermissionDO;
import com.yexiao.demo.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author xuhf
 * @date 2020/8/20 10:48
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    List<PermissionDO> getPermissionList(String roleId);

}
