package com.yexiao.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author xuhf
 * @date 2020/8/12 16:27
 * 跨域配置
 **/
@Component
@Configuration
public class CrossDomainConfig {


    @Bean
    public CorsFilter corsFilter() {
        // 1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        // 放行哪些原始域
        config.addAllowedOrigin("*");
        // 是否发送Cookie信息
        config.setAllowCredentials(true);
        // 放行哪些原始域(请求方式)
        config.addAllowedMethod("*");
        // 放行哪些原始域(头部信息)
        config.addAllowedHeader("*");
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）springboot配置此项会报错，不允许设置*
//		config.addExposedHeader("*");

        // 2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        // 3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }

}
