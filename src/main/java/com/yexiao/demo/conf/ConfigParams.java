package com.yexiao.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xuhf
 * @date 2021/3/15 10:23
 **/
@ConfigurationProperties(prefix = "config")
@Component
public class ConfigParams {

    private WebInterceptors webInterceptors;


    public static class WebInterceptors{

        private List<String> excludePathPatterns;

        public List<String> getExcludePathPatterns() {
            return excludePathPatterns;
        }

        public void setExcludePathPatterns(List<String> excludePathPatterns) {
            this.excludePathPatterns = excludePathPatterns;
        }
    }

    public WebInterceptors getWebInterceptors() {
        return webInterceptors;
    }

    public void setWebInterceptors(WebInterceptors webInterceptors) {
        this.webInterceptors = webInterceptors;
    }
}
