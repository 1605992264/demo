package com.yexiao.demo.conf;


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

    private String uploadPath;
    private String zipPath;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }
}
