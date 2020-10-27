package com.yexiao.demo.flow.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.Date;

/**
 * @author weix
 * @version 1.0
 * @description
 * @date 2020/7/7 11:19
 * @since JDK1.8
 */
public class ConstantMethod {

    /**生成对应的编号
     * 以为首，中间为时间戳，尾部有四位随机数字
     * @return
     */
    public static String autoGenerateCode(String top,int tail){
        String GeneralCode = top+ DateUtil.format(new Date(), "yyyyMMddhhmmssSSS")+ RandomUtil.randomNumbers(tail);
        return GeneralCode;
    }
}
