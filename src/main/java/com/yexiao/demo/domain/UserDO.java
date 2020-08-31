package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuhf
 * @date 2020/8/20 10:49
 **/
@ApiModel(value = "User对象", description = "用户表")
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class UserDO extends BaseEntity {

    @ApiModelProperty(value = "名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "登录名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    @TableField("birth")
    private Long birth;

    @ApiModelProperty(value = "token")
    @TableField(exist = false)
    private String token;

}
