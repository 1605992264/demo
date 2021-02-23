package com.yexiao.demo.conf.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author xuhf
 * @date 2021/2/20 10:29
 **/
@Configuration
public class MinioConfig {

    @Bean
    public MinioClient minioClient(MinioProperty property) throws Exception {
        MinioClient minioClient = new MinioClient(property.getServerUrl(),property.getAccessKey(),property.getSecretKey());
        return minioClient;
    }

}
