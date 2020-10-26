package com.yexiao.demo.conf.interceptor;

/**
 * @author xuhf
 * @date 2020/10/26 9:44
 **/
public class ErrorMethodException extends RuntimeException {

    private StackTraceElement errorMethod;

    public ErrorMethodException(String message,StackTraceElement errorMethod){
        super(message);
        this.errorMethod = errorMethod;
    }

    public ErrorMethodException(String message){
        super(message);
        if(errorMethod == null) {
            this.errorMethod = this.getStackTrace()[0];
        }
    }

    public StackTraceElement getErrorMethod() {
        return errorMethod;
    }

    public void setErrorMethod(StackTraceElement errorMethod) {
        this.errorMethod = errorMethod;
    }
}
