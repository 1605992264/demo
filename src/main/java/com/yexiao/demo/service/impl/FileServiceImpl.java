package com.yexiao.demo.service.impl;


import com.yexiao.demo.base.utils.FileUtils;
import com.yexiao.demo.service.FileService;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author xuhf
 * @date 2020/11/18 14:20
 **/
@Service
public class FileServiceImpl implements FileService {

    @Value("${param.uploadPath}")
    private String uploadPath;

    @Override
    public String fileUpload(MultipartFile file){
        String name = file.getOriginalFilename();
        File uploadFile = null;
        String realPath = getRealPath(name);
        try {
            uploadFile = new File(uploadPath + realPath);
            if(!uploadFile.getParentFile().exists()){
                uploadFile.getParentFile().mkdirs();
            }
            file.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return realPath;
    }

    private String getRealPath(String fileName){
        StringBuilder builder = new StringBuilder();
        LocalDate now = LocalDate.now();
        builder.append(UserUtils.getUser().getUsername())
                .append("/")
                .append(now.getYear())
                .append("/")
                .append(now.getMonthValue())
                .append("/")
                .append(now.getDayOfMonth())
                .append("/")
                .append(System.currentTimeMillis())
                .append("/")
                .append(fileName);
        return builder.toString();
    }




    /**
     * 文件下载
     * */
    @Override
    public void fileDownLoad(HttpServletResponse response, String path){
        FileInputStream fileInputStream = null;
        try {
            File file = new File(uploadPath,path);
            fileInputStream = new FileInputStream(file);
            FileUtils.download(response,file.getName(),fileInputStream);
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败");
        }
    }


}
