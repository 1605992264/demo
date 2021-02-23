package com.yexiao.demo.conf.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2021/2/20 10:32
 * 属性
 **/
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperty {

    private String serverUrl = "http://localhost:9000";
    private String accessKey = "minioadmin";
    private String secretKey = "minioadmin";

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
