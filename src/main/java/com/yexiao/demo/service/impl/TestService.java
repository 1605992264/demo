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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            boolean test = minioClient.bucketExists("test");
            if(!test){
                minioClient.makeBucket("test");
            }
            minioClient.putObject("test",file.getOriginalFilename(),file.getInputStream(),new PutObjectOptions(file.getSize(),0L));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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
