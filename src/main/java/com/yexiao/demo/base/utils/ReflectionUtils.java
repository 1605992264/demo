package com.yexiao.demo.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/12 16:22
 **/
public class ReflectionUtils {

    public static final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    /**
     * 基于反射获取对象的值
     * */
    public static Object getAttributes(Object o,String attributesName){
        Class<?> aClass = o.getClass();
        try {
            Field declaredField = aClass.getDeclaredField(attributesName);
            declaredField.setAccessible(true);
            return declaredField.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 基于反射设置对象的值
     * */
    public static Object setAttributes(Object o,String attributesName,Object value){
        Class<?> aClass = o.getClass();
        try {
            Field declaredField = aClass.getDeclaredField(attributesName);
            declaredField.setAccessible(true);
            declaredField.set(o,value);
            return declaredField.get(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反射获取全字段的值
     * */
    public static<T extends Object> void getAllFieIdValue(T o){
        Map<String,Object> map = new HashMap<>();
        Class aClass = o.getClass();
        while (!"java.lang.Object".equals(aClass.getName())){
            for(Field field : aClass.getDeclaredFields()){
                try {
                    field.setAccessible(true);
                    map.put(field.getName(),field.get(o));
                    logger.info(field.getName() + " : " + field.get(o));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            aClass = aClass.getSuperclass();
        }
    }


}
