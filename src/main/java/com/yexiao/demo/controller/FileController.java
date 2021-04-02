package com.yexiao.demo.controller;

import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.service.impl.FileServiceImpl;
import com.yexiao.demo.service.impl.MinioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/11/18 14:17
 **/
@Controller
public class FileController {

    @Autowired
    private MinioServiceImpl fileService;

    @ResponseBody
    @PostMapping("fileUpload")
    public R fileUpload(MultipartFile file){
        String s = fileService.fileUpload(file);
        Map map = new HashMap(1);
        map.put("url",s);
        return R.success(map);
    }

    @PostMapping("fileDownload")
    public void fileDownload(HttpServletResponse response,String path){
        fileService.fileDownLoad(response,path);
    }

}
