package com.yexiao.demo.base.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author xuhf
 * @date 2020/8/12 16:24
 * 完成基于spring AOP 的自定义注解
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 操作详情
     * */
    String message() default "";
}
