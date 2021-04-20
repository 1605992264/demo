package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.yexiao.demo.utils.UserUtils;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author xuhf
 * @date 2020/10/26 10:54
 * 表必须有这些字段
 * 记录创建人，修改人等
 **/

public class UserInfoBaseEntity extends BaseEntity {

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    protected String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date",fill = FieldFill.INSERT)
    protected Date createDate;

    @ApiModelProperty(value = "修改人id")
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    protected String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE)
    protected Date updateDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "UserInfoBaseEntity{" +
                "createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", id='" + id + '\'' +
                '}';
    }
}
