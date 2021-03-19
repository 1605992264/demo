package com.yexiao.demo.base.annotation;

import com.yexiao.demo.base.utils.WebUtils;
import com.yexiao.demo.domain.LogDO;
import com.yexiao.demo.service.LogService;
import com.yexiao.demo.utils.UserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xuhf
 * @date 2021/2/4 14:04
 **/
@Aspect
@Component
public class ErrorLogAspect {

    @Autowired
    private LogService logService;

    /**
     * 切面异常拦截
     * */
    @Pointcut("execution( * com.yexiao.demo.conf.interceptor.exception.GlobalExceptionHandler.*(..))")
    public void errorLog(){
    }

    @Around("errorLog()")
    public Object doAround(ProceedingJoinPoint joinPoint){
        String userName = "未知用户";
        String userId = "-1";
        if(UserUtils.getUser() != null){
            userName = UserUtils.getUser().getName();
            userId = UserUtils.getUser().getId();
        }
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            if(UserUtils.getUser() != null){
                userName = UserUtils.getUser().getName();
                userId = UserUtils.getUser().getId();
            }
            LogDO logDO = new LogDO();
            logDO.setType(LogDO.ERROR);
            logDO.setUserName(userName);
            logDO.setUserId(userId);
            logDO.setIp(WebUtils.getClientIPAddress());
            logDO.setMethod(WebUtils.getHttpServletRequest().getServletPath());
            logDO.setMessage(getExceptionArg(joinPoint.getArgs()).getMessage());
            logDO.setCreateDate(new Date());
            logService.save(logDO);
        }
        return null;
    }

    private Exception getExceptionArg(Object[] args){
        for (Object arg : args) {
            if(arg instanceof Exception){
                return (Exception) arg;
            }
        }
        return null;
    }

}
