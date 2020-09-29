package com.yexiao.demo.base.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author xuhf
 * @date 2020/9/21 15:09
 **/
public class FileUtils {

    /**
     * 文件下载
     * @param file 文件
     * @param response 响应
     * @param fileName 文件名
     * */
    public static void download(HttpServletResponse response, String fileName, InputStream file) throws Exception {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[2048];
        int len;
        while ((len = file.read(b)) != -1) {
            out.write(b, 0, len);
        }
        out.flush();
        out.close();
        file.close();
    }

}
