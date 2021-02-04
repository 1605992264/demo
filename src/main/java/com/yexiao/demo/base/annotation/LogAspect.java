package com.yexiao.demo.base.annotation;


import com.yexiao.demo.base.utils.HttpRequestUtils;
import com.yexiao.demo.conf.elasticsearch.ElasticSearchUtils;
import com.yexiao.demo.conf.interceptor.exception.CustomizeException;
import com.yexiao.demo.domain.LogDO;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.LogService;
import com.yexiao.demo.utils.UserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xuhf
 * @date 2020/8/25 10:05
 **/
@Aspect
@Component
public class LogAspect {

    public static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;
    @Autowired
    private ElasticSearchUtils elasticSearchUtils;

    @Pointcut("@annotation(com.yexiao.demo.base.annotation.Log)")
    public void addAdvice(){

    }

    /**
     * spring 注解通知
     * 后置通知和最终通知有问题
     * 尽量用环绕通知
     * */
//    /**
//     * 方法进入之前
//     * */
//    @Before("addAdvice()")
//    public void beforePointcut(JoinPoint joinPoint){
//        logger.info("前置通知");
//        time = System.currentTimeMillis();
//    }
//
//    /**
//     * 方法执行之后
//     * */
//    @AfterReturning("addAdvice()")
//    public void afterPointcut(JoinPoint joinPoint){
//        logger.info("888888");
//        logger.info(joinPoint.getSignature().toLongString() + "执行时间:" +
//                (System.currentTimeMillis() - time) + "毫秒");
//    }
//
//     /**
//      * 最终通知
//      * */
//     @After("addAdvice()")
//     public void finalPointcut(){
//        logger.info("777777");
//     }
//
//     @AfterThrowing("addAdvice()")
//     public void returning(){
//        logger.info("异常通知");
//     }


     /**
      * 环绕通知
      * @param joinPoint 切入点
      * */
    @Around("addAdvice()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        String userName = "未知用户";
        String userId = "-1";
        if(UserUtils.getUser() != null){
            userName = UserUtils.getUser().getName();
            userId = UserUtils.getUser().getId();
        }
        // 执行被切入的方法
        Object returnResult = joinPoint.proceed(joinPoint.getArgs());
        long runTime = System.currentTimeMillis()-time;
        if(UserUtils.getUser() != null){
            userName = UserUtils.getUser().getName();
            userId = UserUtils.getUser().getId();
        }
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
        StringBuilder builder = new StringBuilder();
        builder.append("\n[").append(userName).append("]")
                .append("操作了[").append(annotation.message()).append("]")
                .append("\n")
                .append("方法[").append(methodSignature.toShortString()).append("]")
                .append("耗时[").append(runTime).append("]毫秒");
        logger.info(builder.toString());
        // 封装日志对象
        LogDO logDO = new LogDO();
        logDO.setType(LogDO.SUCCESS);
        logDO.setCreateDate(new Date());
        logDO.setMessage(annotation.message());
        logDO.setUserId(userId);
        logDO.setUserName(userName);
        logDO.setMethod(HttpRequestUtils.getHttpServletRequest().getServletPath());
        logDO.setIp(HttpRequestUtils.getClientIPAddress());
        logService.save(logDO);
        return returnResult;
    }

    /**
     * 拼接方法的参数
     * @param argsTypes 参数类型
     * @param argNames 参数名称
     * @param args 参数值
     * */
    private String getParams(Class[] argsTypes,String[] argNames,Object[] args){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<argsTypes.length;i++){
            builder.append(argsTypes[i]).append(" ")
                    .append(argNames[i]).append("=")
                    .append(args[i])
                    .append("\b");
        }
        return builder.toString();
    }

}
