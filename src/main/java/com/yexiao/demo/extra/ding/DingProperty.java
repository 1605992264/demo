package com.yexiao.demo.extra.ding;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2021/3/1 14:58
 **/
@ConfigurationProperties(prefix = "param.ding")
@Component
public class DingProperty {

    private String appKey;
    private String appSecret;
    private String getTokenURL;

    public String getGetTokenURL() {
        return getTokenURL;
    }

    public void setGetTokenURL(String getTokenURL) {
        this.getTokenURL = getTokenURL;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
