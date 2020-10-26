package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xuhf
 * @date 2020/8/20 10:49
 **/
@ApiModel(value = "Permission对象", description = "权限表")
@Data
@Accessors(chain = true)
@TableName("sys_permission")
public class PermissionDO extends UserInfoBaseEntity {

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "别名")
    @TableField("alias")
    private String alias;

}
