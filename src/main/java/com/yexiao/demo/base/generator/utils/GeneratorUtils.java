package com.yexiao.demo.base.generator.utils;

import cn.hutool.core.util.StrUtil;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import com.yexiao.demo.base.generator.domain.Column;
import com.yexiao.demo.base.generator.domain.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author xuhf
 * @date 2020/8/28 10:03
 **/
public class GeneratorUtils {

    private static Configuration cfg;

    //ftl模板位置
    private static String templateFolder = "/templates/generator";

     static{
         cfg = new freemarker.template.Configuration(new Version("2.3.0"));
         cfg.setDefaultEncoding("utf-8");
         cfg.setClassForTemplateLoading(GeneratorUtils.class, templateFolder);
    }

    /**
     * 获取模板路径
     * */
    public static List<String> getTemplateNames(){
        List<String> templates = new ArrayList<String>();
        templates.add("Controller.java.ftl");
        templates.add("Mapper.java.ftl");
        templates.add("Entity.java.ftl");
        templates.add("Mapper.xml.ftl");
        templates.add("Service.java.ftl");
        templates.add("ServiceImpl.java.ftl");
        return templates;
    }

    public static ByteArrayOutputStream generatorCode(Table table){
        List<String> templateNames = getTemplateNames();
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(byteStream);
        for (String templateName : templateNames) {
            Template template = null;
            try {
                template = cfg.getTemplate(templateName);
                String packageName = table.getPackageName().replace(".","/");
                String filePath = getZipFilePath("generatorCode/" + packageName,
                        table.getClassName() + templateName.replace(".ftl", ""));
                zip.putNextEntry(new ZipEntry(filePath));
                OutputStreamWriter writer =new OutputStreamWriter(zip,"UTF-8");
                template.process(table,writer);
                writer.flush();
                zip.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return byteStream;
    }

    /**
     * 生成代码
     * */
    public static void generatorCode(Table table, HttpServletResponse response){
        ByteArrayOutputStream byteStream = generatorCode(table);
        /**
         * 设置浏览器下载
         * */
        response.setHeader("content-disposition", "attachment;filename="+table.getClassName() + ".zip");
        int len = 0;
        //5.创建数据缓冲区
        byte[] buffer = new byte[1024];
        //6.通过response对象获取OutputStream流
        OutputStream out = null;
        InputStream in = new ByteArrayInputStream(byteStream.toByteArray());
        try {
            out = response.getOutputStream();
            //7.将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                //8.使用OutputStream将缓冲区的数据输出到客户端浏览器
                out.write(buffer,0,len);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

    /**
     * 填充table对象
     * */
    public static Table FillTable(Table table){
         // 设置包名
        table.setPackageName("com.yexiao.demo");
        // 设置javaClassName名
        table.setClassName(getClassName(table.getTableName()));
        // 读取配置文件的类型
        Properties properties = new Properties();
        try {
            properties.load(GeneratorUtils.class.getClassLoader().getResourceAsStream("generator.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 设置java类型和属性名
        for(Column column : table.getColumns()){
            column.setJavaName(getJavaName(column.getName()));
            column.setJavaType(properties.getProperty(column.getType()));
        }
        return fillIsExtendsUserInfoBaseEntity(table);
    }

    /**
     * 是否需要继承UserInfoBaseEntity
     * */
    private static Table fillIsExtendsUserInfoBaseEntity(Table table){
        List<Column> columns = table.getColumns();
        int count = 0;
        for(Column column : columns){
            switch (column.getName()){
                case "create_date":
                case "create_by":
                case  "update_date":
                case  "update_by":
                    count++;
                    break;
            }
        }
        if(count == 4){
            table.setExtendsUserInfoBaseEntity(true);
        }else {
            table.setExtendsUserInfoBaseEntity(false);
        }
        return table;

    }

    /**
     * 获取ClassName名
     * */
    private static String getClassName(String tableName){
        if(tableName == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] s = tableName.split("_");
        if(s.length > 1){
            for(int i = 1;i<s.length ; i++){
                builder.append(StrUtil.upperFirst(s[i]));
            }
        }else{
            throw new RuntimeException("命名不规范");
        }
        return builder.toString();
    }

    /**
     * 获取实现名
     * */
    private static String getJavaName(String columnName){
        if(columnName == null){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String[] s = columnName.split("_");
        for(int i = 0;i<s.length ; i++){
            if( i == 0){
                builder.append(s[i]);
            }else {
                builder.append(StrUtil.upperFirst(s[i]));
            }
        }
        return builder.toString();
    }

    /**
     * 获取压缩路径
     * */
    private static String getZipFilePath(String packageName,String fileName){

         if(fileName.contains("Mapper.xml")){
             return "generatorCode/mybatis/" + fileName;
         }
         if(fileName.contains("Controller.java")){
             return packageName + "/controller/" + fileName;
         }else  if(fileName.contains("Entity.java")){
             return packageName + "/domain/" + fileName.replace("Entity","DO");
         }else if(fileName.contains("Mapper.java")){
             return packageName + "/mapper/" + fileName;
         }else if(fileName.contains("ServiceImpl.java")){
             return packageName + "/service/impl/" + fileName;
         }else if(fileName.contains("Service.java")){
             return packageName + "/service/" + fileName;
         }
         return "";
    }

}
