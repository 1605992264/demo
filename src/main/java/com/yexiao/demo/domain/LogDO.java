package com.yexiao.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yexiao.demo.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
* @author xuhf
* @date 2021-02-04 15:15:55.730
**/
@ApiModel(value = "Log对象", description = "操作日志表")
@TableName("sys_log")
public class LogDO extends BaseEntity{

    public final static String ERROR = "0";
    public final static String SUCCESS = "1";

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

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
     * 访问者IP地址
     */
    @ApiModelProperty("访问者IP地址")
    @TableField("ip")
    private String ip;

    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    @TableField("create_date")
    private Date createDate;

    /**
     * 操作类型 0-失败 1-成功
     */
    @ApiModelProperty("操作类型 0-失败 1-成功")
    @TableField("type")
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
