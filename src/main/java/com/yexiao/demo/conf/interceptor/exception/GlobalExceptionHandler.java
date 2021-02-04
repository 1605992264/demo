package com.yexiao.demo.conf.interceptor.exception;


import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/26 15:36
 * 全局异常处理器
 **/

@RestControllerAdvice
public class GlobalExceptionHandler{



    /**
     * 捕获shiro没有权限异常
     * 自定义返回信息
     * */
    @ExceptionHandler(UnauthorizedException.class)
    public Map<String, Object> UnauthorizedException(Exception exception) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", 777);
        map.put("message", "该用户没有权限");
//        map.put("errorMsg",exception.toString());
        return map;
    }

    /**
     * 自定义方法异常 可预知的异常
     * */
    @ExceptionHandler({CustomizeException.class})
    public Map<String, Object> methodException(CustomizeException exception) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", 500);
        map.put("message",exception.getMessage());
//        map.put("errorMsg",exception.toString());
        return map;
    }

    /**
     * 其他异常 无法预知的异常
     * */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> handleException(Exception exception) {
        exception.printStackTrace();
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", 500);
        map.put("message","服务器错误！请联系管理员");
//        map.put("errorMsg",exception.toString());
        return map;
    }

}
