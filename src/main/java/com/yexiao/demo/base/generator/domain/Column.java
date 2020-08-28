package com.yexiao.demo.base.generator.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/8/27 14:54
 **/
@Data
public class Column implements Serializable {

    @ApiModelProperty("数据库字段名")
    private String name;

    @ApiModelProperty("java字段名")
    private String javaName;

    @ApiModelProperty("数据库字段类型")
    private String type;

    @ApiModelProperty("java类型")
    private String javaType;

    @ApiModelProperty("注释")
    private String comment;

}
