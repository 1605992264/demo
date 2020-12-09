package com.yexiao.demo.base.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author xuhf
 * @date 2020/9/21 15:09
 **/
public class FileUtils {

    /**
     * @param createZipFile 要创建的zip文件
     * @param zipFiles 要压缩的文件
     * */
    public static void createZip(File[] zipFiles, File createZipFile) throws Exception {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(createZipFile));
        for(File file : zipFiles){
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            while (fileInputStream.read(bytes) != -1) {
                zipOutputStream.write(bytes);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        System.out.println("压缩文件成功!");
    }

    /**
     * @param createZipFile 要创建的zip文件
     * @param files 要压缩的文件map
     * key(String) 压缩包内的路径  value(InputStream) 要压缩的文件
     * */
    public static void createZip(Map<String,InputStream> files, File createZipFile) throws Exception{
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(createZipFile));
        for(Map.Entry<String,InputStream> file : files.entrySet()){
            ZipEntry zipEntry = new ZipEntry(file.getKey());
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            while (file.getValue().read(bytes) != -1) {
                zipOutputStream.write(bytes);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        System.out.println("压缩文件成功!");
    }

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

    /**
     * 解压zip文件
     * @param zipFilePath zip文件
     * @param unzipPath 解压路径
     * */
    public static void unzip(String zipFilePath,String unzipPath) throws IOException {
        if(StrUtil.isEmpty(unzipPath)){
            throw new RuntimeException("解压路径不能为空!");
        }
        File unzipFile = new File(unzipPath);
        if(!unzipFile.exists() || !unzipFile.isDirectory()){
            unzipFile.mkdirs();
        }
        ZipFile zipFile = new ZipFile(zipFilePath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()){
            ZipEntry zipEntry = entries.nextElement();
            if(zipEntry.isDirectory()){
                continue;
            }
            StringBuilder zipEntryPath = new StringBuilder(unzipPath);
            zipEntryPath.append("/").append(zipEntry.getName());
            File file = new File(zipEntryPath.toString());
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[1024];
            while (bufferedInputStream.read(bytes) > 0){
                bufferedOutputStream.write(bytes);
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
        zipFile.close();
    }


}
