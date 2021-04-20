package com.yexiao.demo.base.utils;

/**
 * @author xuhf
 * @date 2021/4/20 14:54
 **/
public class SQLUtils {

    /***
     * 模糊查询转义
     */
    public static String mysqlLikeSearch(String queryCondition){
        if( queryCondition != null && !queryCondition.isEmpty() ) {
            String condition = queryCondition;
            condition = condition.replaceAll("%", "\\\\%");
            condition = condition.replaceAll("_", "\\\\_");
            return condition;
        }
        return queryCondition;
    }

}
