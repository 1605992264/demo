package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/8/20 10:55
 **/
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.UUID)
    private String id;

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

    @ApiModelProperty(value = "删除标识")
    @TableField(value = "delete_flag")
    @TableLogic(value = "0",delval = "1")
    private Integer deleteFlag;

}
