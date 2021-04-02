package com.yexiao.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.yexiao.demo.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author xuhf
 * @date 2021/2/25 16:27
 *
 * 使用minio存放多级目录时
 * 不能使用minio的桶存储多级目录
 * 需要使用ObjectName 来存储多级
 **/
@Service
public class MinioServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;
    private final String bucketName = "test";

    @Override
    public void fileDownLoad(HttpServletResponse response, String path) {
//        String[] split = path.split("");
//        minioClient.getObject()
    }

    @Override
    public String fileUpload(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        try {
            boolean b = minioClient.bucketExists(bucketName);
            if(!b){
                minioClient.makeBucket(bucketName);
            }
            builder.append(LocalDate.now())
                    .append("/")
                    .append(IdUtil.fastSimpleUUID())
                    .append("/")
                    .append(file.getOriginalFilename());
            minioClient.putObject(bucketName,builder.toString(),
                    file.getInputStream(),new PutObjectOptions(file.getSize(),0L));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传失败");
        }
        return bucketName + "/" + builder;
    }
}
