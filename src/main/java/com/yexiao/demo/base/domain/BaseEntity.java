package com.yexiao.demo.base.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

}
