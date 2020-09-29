package com.yexiao.demo.base.utils;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author xuhf
 * @date 2020/8/12 16:27
 * 静态参数配置类
 * */
@Component
@ConfigurationProperties(prefix="param")
public class ConfigParams {

    private static String uploadPath;
    private static String zipPath;

    public static String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        uploadPath = uploadPath;
    }

}
