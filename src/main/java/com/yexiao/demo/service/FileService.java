package com.yexiao.demo.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xuhf
 * @date 2021/2/25 16:28
 **/
public interface FileService {

    void fileDownLoad(HttpServletResponse response, String path);

    String fileUpload(MultipartFile file);

}
