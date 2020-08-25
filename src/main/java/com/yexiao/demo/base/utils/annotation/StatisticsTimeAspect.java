package com.yexiao.demo.base.utils.annotation;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

 /**
 * @author xuhf
 * @date 2020/8/25 10:05
 **/
@Aspect
@Component
public class StatisticsTimeAspect {

    public static final Logger logger = LoggerFactory.getLogger(StatisticsTimeAspect.class);

    private long time;

    @Pointcut("@annotation(com.yexiao.demo.base.utils.annotation.MyAspect)")
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
        try {
            time = System.currentTimeMillis();
            // 执行被切入的方法
            returnResult = joinPoint.proceed();
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            MyAspect annotation = methodSignature.getMethod().getAnnotation(MyAspect.class);
            logger.info(annotation.message() + "\n" + joinPoint.getSignature().toLongString() + "    执行时间:    " +
                    (System.currentTimeMillis() - time) + " 毫秒");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }
        return returnResult;
    }



}
