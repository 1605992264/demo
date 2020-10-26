package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.channels.Pipe;
import java.util.Date;

/**
* @author xuhf
* @date 2020-10-23 15:14:47.743
**/
@ApiModel(value = "Log对象", description = "操作日志表")
@Data
@Accessors(chain = true)
@TableName("sys_log")
public class LogDO extends BaseEntity{

    public final static String SUCCESS = "1";
    public final static String ERROR = "0";

    /**
     * 操作状态 0-失败 1-成功
     */
    @ApiModelProperty("操作状态 0-失败 1-成功")
    @TableField("status")
    private String status;

    /**
     * 异常信息
     */
    @ApiModelProperty("异常信息")
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * 访问者IP地址
     */
    @ApiModelProperty("访问者IP地址")
    @TableField("ip")
    private String ip;

    /**
     * 错误行号
     */
    @ApiModelProperty("错误行号")
    @TableField("line_number")
    private Integer lineNumber;

    /**
     * 操作详情
     */
    @ApiModelProperty("操作详情")
    @TableField("message")
    private String message;

    /**
     * 请求方法
     */
    @ApiModelProperty("请求方法")
    @TableField("method")
    private String method;

    /**
     * 传入参数
     */
    @ApiModelProperty("传入参数")
    @TableField("params")
    private String params;

    /**
     * 花费时间
     */
    @ApiModelProperty("花费时间")
    @TableField("time")
    private Integer time;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    /**
     * 用户id
     */
    @ApiModelProperty("操作时间")
    @TableField("create_date")
    private long createDate;

}
