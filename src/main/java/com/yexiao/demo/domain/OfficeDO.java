package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
* @author xuhf
* @date 2020-10-26 15:38:18.574
**/
@ApiModel(value = "Office对象", description = "机构表")

@TableName("sys_office")
public class OfficeDO extends UserInfoBaseEntity{

    /**
    * 父级编号
    */
    @ApiModelProperty("父级编号")
    @TableField("parent_id")
    private String parentId;

    /**
    * 所有父级编号
    */
    @ApiModelProperty("所有父级编号")
    @TableField("parent_ids")
    private String parentIds;

    /**
    * 名称
    */
    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    /**
    * 排序
    */
    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    /**
    * 备注信息
    */
    @ApiModelProperty("备注信息")
    @TableField("remarks")
    private String remarks;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标识")
    @TableField(value = "delete_flag")
    @TableLogic(value = "0",delval = "1")
    private Integer deleteFlag;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
