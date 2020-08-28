package com.yexiao.demo.base.utils.annotation;

import java.lang.annotation.*;

/**
 * @author xuhf
 * @date 2020/8/12 16:24
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAspect {
    /**
     * 完成基于spring AOP 的自定义注解
     * */
    String value() default "";
    String message() default "";
}