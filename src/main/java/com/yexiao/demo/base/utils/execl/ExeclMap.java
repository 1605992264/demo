package com.yexiao.demo.base.utils.execl;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/10/21 10:42
 **/
public class ExeclMap {

    public static Map<String,Map<String,String>> tableInfo = new HashMap<>();

    public final static String userInfo = "人员信息";
    public final static String office = "部门信息";

    static {
        // 每一个map 存的是表的信息
        Map<String,String> userMap = new HashMap<>();
        userMap.put("姓名","name");
        userMap.put("性别","sex");
        userMap.put("手机号","mobile");
        //userMap.put("出生日期","birth");
        tableInfo.put(userInfo,userMap);

//        Map<String,String> officeMap = new HashMap<>();
//        officeMap.put("名称","name");
//        officeMap.put("领导","leader");
//        officeMap.put("类型","type");
//        tableInfo.put(office,officeMap);

    }


}
