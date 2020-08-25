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

    private String appId;
    private String privateKey;
    private String publicKey;

    private String appIdLYH;
    private String privateKeyLYH;
    private String publicKeyLYH;

    public String getAppIdLYH() {
        return appIdLYH;
    }

    public void setAppIdLYH(String appIdLYH) {
        this.appIdLYH = appIdLYH;
    }

    public String getPrivateKeyLYH() {
        return privateKeyLYH;
    }

    public void setPrivateKeyLYH(String privateKeyLYH) {
        this.privateKeyLYH = privateKeyLYH;
    }

    public String getPublicKeyLYH() {
        return publicKeyLYH;
    }

    public void setPublicKeyLYH(String publicKeyLYH) {
        this.publicKeyLYH = publicKeyLYH;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

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
