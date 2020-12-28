package com.yexiao.demo.base.generator.domain;

import io.swagger.annotations.ApiModelProperty;


import java.io.Serializable;

/**
 * @author xuhf
 * @date 2020/8/27 14:54
 **/

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
