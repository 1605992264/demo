package com.yexiao.demo.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yexiao.demo.base.utils.FileUtils;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author xuhf
 * @date 2021/2/20 10:45
 **/
@Service
public class TestService {

    @Autowired
    private MinioClient minioClient;

    /**
     * 上传附件
     * */
    public boolean upload(MultipartFile file){
        try {
            boolean test = minioClient.bucketExists("test/aa/vv");
            if(!test){
                minioClient.makeBucket("test/aa/vv");
            }
            minioClient.putObject("test/aa/vv",file.getOriginalFilename(),file.getInputStream(),new PutObjectOptions(file.getSize(),0L));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取minio上的文件列表
     * */
    public List<FileVO> fileList(String name){
        List<FileVO> fileList = new ArrayList<>();
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(name);
            results.forEach(new Consumer<Result<Item>>() {
                @Override
                public void accept(Result<Item> itemResult) {
                    try {
                        Item item = itemResult.get();
                        FileVO fileVO = new FileVO();
                        fileVO.name = item.objectName();
                        fileVO.size = FileUtils.readFileSize(item.size());
                        fileVO.setCreateDate(item.lastModified().toLocalDateTime().plusHours(8));
                        Map<String, String> reqParams = new HashMap<String, String>();
                        reqParams.put("response-content-type", "application/octet-stream");
                        reqParams.put("Content-disposition","attachment;filename=" + URLEncoder.encode(item.objectName(), "UTF-8"));
                        fileVO.setUrl(minioClient.presignedGetObject(name,item.objectName(),60*60,reqParams));
                        fileList.add(fileVO);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileList;
    }

    class FileVO{

        private String name;
        @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
        private LocalDateTime createDate;
        private String size;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getCreateDate() {
            return createDate;
        }

        public void setCreateDate(LocalDateTime createDate) {
            this.createDate = createDate;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

}
