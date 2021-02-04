package com.yexiao.demo.conf.interceptor.exception;

/**
 * @author xuhf
 * @date 2021/2/4 9:58
 * 可预知的异常
 **/
public class CustomizeException extends RuntimeException{

    public CustomizeException(String message){
        super(message);
    }

}
