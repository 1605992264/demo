package com.yexiao.demo.base.annotation;


import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpUtil;
import com.yexiao.demo.base.utils.HttpRequestUtils;
import com.yexiao.demo.base.utils.UserUtils;
import com.yexiao.demo.conf.elasticsearch.ElasticSearchUtils;
import com.yexiao.demo.conf.interceptor.ErrorMethodException;
import com.yexiao.demo.domain.LogDO;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.net.util.IPAddressUtil;

import java.io.IOException;
import java.time.LocalDateTime;
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
    public Object doAround(ProceedingJoinPoint joinPoint){
        Object returnResult = null;
        long time = System.currentTimeMillis();
        UserDO user = UserUtils.getUser();
        LogDO logDO = new LogDO();
        try {
            // 执行被切入的方法
            returnResult = joinPoint.proceed();
            logDO.setStatus(LogDO.SUCCESS);
        } catch (Throwable throwable) {
            // 填充错误日志信息
            logDO.setStatus(LogDO.ERROR);
            logDO.setLineNumber(throwable.getStackTrace()[0].getLineNumber());
            logDO.setExceptionInfo(throwable.getMessage());
            throwable.printStackTrace();
            // 抛出异常 让拦截器捕获
            if( throwable instanceof ErrorMethodException) {
                throw (ErrorMethodException) throwable;
            }else {
                throw new ErrorMethodException(throwable.getMessage(),throwable.getStackTrace()[0]);
            }
        } finally {
            // 操作时间
            long runTime = System.currentTimeMillis() - time;
            if(user == null){
                user = UserUtils.getUser();
                if(user == null){
                    //用户没有登入
                    user = new UserDO();
                    user.setName("");
                    user.setId("");
                }
            }
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            // 封装参数
            String params = getParams(methodSignature.getParameterTypes()
                    , methodSignature.getParameterNames(),joinPoint.getArgs());
            Log annotation = methodSignature.getMethod().getAnnotation(Log.class);
            StringBuilder builder = new StringBuilder();
            builder.append("\n用户[").append(user.getName()).append("]")
                    .append("操作了[").append(annotation.message()).append("]")
                    .append(logDO.getStatus()== LogDO.SUCCESS ? "":"报错:"+logDO.getExceptionInfo())
                    .append("\n")
                    .append("方法[").append(methodSignature.toShortString()).append("]")
                    .append("耗时[").append(runTime).append("]毫秒");
            if(logDO.getStatus()== LogDO.SUCCESS) {
                logger.info(builder.toString());
            }else {
                logger.error(builder.toString());
            }
            // 封装日志对象
            logDO.setTime((int)(runTime));
            logDO.setCreateDate(time);
            logDO.setMessage(annotation.message());
            logDO.setUserId(user.getId());
            logDO.setParams(params);
            logDO.setMethod(methodSignature.toShortString());
            logDO.setIp(HttpRequestUtils.getClientIPAddress());
            logService.save(logDO);
            try {
                elasticSearchUtils.save("log_test",logDO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
