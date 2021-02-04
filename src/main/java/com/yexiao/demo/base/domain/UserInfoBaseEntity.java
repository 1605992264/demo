package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.yexiao.demo.utils.UserUtils;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author xuhf
 * @date 2020/10/26 10:54
 * 表必须有这些字段
 * 记录创建人，修改人等
 **/

public class UserInfoBaseEntity extends BaseEntity {

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    private Long createDate;

    @ApiModelProperty(value = "修改人id")
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    private Long updateDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
