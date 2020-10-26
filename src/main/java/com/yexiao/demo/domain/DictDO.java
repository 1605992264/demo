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
* @date 2020-08-28 17:30:47.717
**/
@ApiModel(value = "Dict对象", description = "字典表")
@Data
@Accessors(chain = true)
@TableName("sys_dict")
public class DictDO extends UserInfoBaseEntity {

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    @TableField("name")
    private String name;

    /**
     * 父级编号
     */
    @ApiModelProperty("父级编号")
    @TableField("parent_id")
    private String parentId;

    /**
     * 备注信息
     */
    @ApiModelProperty("备注信息")
    @TableField("remarks")
    private String remarks;

    /**
     * 排序（升序）
     */
    @ApiModelProperty("排序（升序）")
    @TableField("sort")
    private Integer sort;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    @TableField("type")
    private String type;

    /**
     * 数据值
     */
    @ApiModelProperty("数据值")
    @TableField("value")
    private String value;


}
