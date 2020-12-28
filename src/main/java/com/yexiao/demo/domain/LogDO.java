package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.nio.channels.Pipe;
import java.util.Date;

/**
* @author xuhf
* @date 2020-10-23 15:14:47.743
**/
@ApiModel(value = "Log对象", description = "操作日志表")

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
