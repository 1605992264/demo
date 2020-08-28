package com.yexiao.demo.conf.interceptor;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/26 15:36
 * 全局异常处理器
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获shiro没有权限异常
     * 自定义返回信息
     * */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public Map<String, Object> UnauthorizedException(Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", "该用户没有权限");
        map.put("errorMsg",exception.toString());
        return map;
    }

    /**
     * 其他异常
     * */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("errorMsg",exception.toString());
        return map;
    }
}