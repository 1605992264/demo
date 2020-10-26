package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/8/20 10:49
 **/
@ApiModel(value = "Role对象", description = "角色表")
@Data
@Accessors(chain = true)
@TableName("sys_role")
public class RoleDO extends UserInfoBaseEntity {

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "别名")
    @TableField("alias")
    private String alias;

}
