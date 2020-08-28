package com.yexiao.demo.base.generator.domain;

import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/27 14:56
 **/
@Data
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
    private LocalDate DATE;

    @ApiModelProperty("时间")
    private LocalTime TIME;

    @ApiModelProperty("包名")
    private String packageName;


}
