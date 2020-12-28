package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 10:49
 **/
@ApiModel(value = "Role对象", description = "角色表")

@TableName("sys_role")
public class RoleDO extends UserInfoBaseEntity {

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "别名")
    @TableField("alias")
    private String alias;

    @TableField(exist = false)
    private List<PermissionDO> permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<PermissionDO> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<PermissionDO> permissionList) {
        this.permissionList = permissionList;
    }
}
