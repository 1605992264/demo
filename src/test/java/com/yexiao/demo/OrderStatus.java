package com.yexiao.demo;

/**
 * @author xuhf
 * @date 2021/1/7 16:11
 **/
public enum OrderStatus {
    /**
     *  状态 0-驳回 1-待审核 2-结束  3-待处理
     * */
    reject(0,"驳回"),
    notApprove(1,"待审核"),
    finish(2,"结束"),
    Pending(3,"待处理");


    private int code;
    private String message;

    @Override
    public String toString() {
        return String.valueOf(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    OrderStatus(int code, String message){
       this.code = code;
       this.message = message;
    }

}
