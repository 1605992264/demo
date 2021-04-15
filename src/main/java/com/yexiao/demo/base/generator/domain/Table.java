package com.yexiao.demo.base.generator.domain;

import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/27 14:56
 **/

public class Table extends BaseEntity {

    @ApiModelProperty("表名")
    private String tableName;

    @ApiModelProperty("表注释")
    private String tableComment;

    @ApiModelProperty("java对象名")
    private String className;

    @ApiModelProperty("表字段对象")
    private List<Column> columns;

    @ApiModelProperty("日期")
    private LocalDate DATE = LocalDate.now();

    @ApiModelProperty("时间")
    private LocalTime TIME = LocalTime.now();

    @ApiModelProperty("包名")
    private String packageName;

    /**
     * 不包含create_date,update_by等的表
     * 不需要继承UserInfoBaseEntity
     * */
    @ApiModelProperty("是否继承UserInfoBaseEntity")
    private boolean isExtendsUserInfoBaseEntity;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public LocalDate getDATE() {
        return DATE;
    }

    public void setDATE(LocalDate DATE) {
        this.DATE = DATE;
    }

    public LocalTime getTIME() {
        return TIME;
    }

    public void setTIME(LocalTime TIME) {
        this.TIME = TIME;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isExtendsUserInfoBaseEntity() {
        return isExtendsUserInfoBaseEntity;
    }

    public void setExtendsUserInfoBaseEntity(boolean extendsUserInfoBaseEntity) {
        isExtendsUserInfoBaseEntity = extendsUserInfoBaseEntity;
    }
}
