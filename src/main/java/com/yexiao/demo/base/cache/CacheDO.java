package com.yexiao.demo.base.cache;

/**
 * @author xuhf
 * @date 2020/9/17 9:51
 **/
public class CacheDO {

    private Object data;
    /**
     * 超时时间 默认分
     * */
    private Integer timeout;
    /**
     * 创建时间戳
     * */
    private Long createTime;


    public CacheDO(Object data) {
        this.data = data;
        this.timeout = 5;
        this.createTime = System.currentTimeMillis();
    }

    public CacheDO(Object data, Integer timeout) {
        this.data = data;
        this.timeout = timeout;
        this.createTime = System.currentTimeMillis();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Long getCreateTime() {
        return createTime;
    }
}
