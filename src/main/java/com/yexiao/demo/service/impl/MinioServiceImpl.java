package com.yexiao.demo.service.impl;

import com.yexiao.demo.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private final String bucketName = "test/aa/cc";

    @Override
    public void fileDownLoad(HttpServletResponse response, String path) {
//        String[] split = path.split("");
//        minioClient.getObject()
    }

    @Override
    public String fileUpload(MultipartFile file) {
        try {
            boolean b = minioClient.bucketExists(bucketName);
            if(!b){
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName,file.getOriginalFilename(),file.getInputStream(),new PutObjectOptions(file.getSize(),0L));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bucketName + "/" + file.getOriginalFilename();
    }
}
