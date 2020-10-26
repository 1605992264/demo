package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xuhf
 * @date 2020/10/26 10:54
 * 表必须有这些字段
 * 记录创建人，修改人等
 **/
@Data
public class UserInfoBaseEntity extends BaseEntity {

    @ApiModelProperty(value = "创建人id")
    @TableField(value = "create_by",fill = FieldFill.INSERT,strategy = FieldStrategy.NOT_NULL)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_date",fill = FieldFill.INSERT,strategy = FieldStrategy.NOT_NULL)
    private Long createDate;

    @ApiModelProperty(value = "修改人id")
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE,strategy = FieldStrategy.NOT_NULL)
    private String updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "update_date",fill = FieldFill.INSERT_UPDATE,strategy = FieldStrategy.NOT_NULL)
    private Long updateDate;

}
