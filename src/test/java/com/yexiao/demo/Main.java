package com.yexiao.demo;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.base.generator.domain.Column;
import com.yexiao.demo.base.generator.domain.Table;
import com.yexiao.demo.base.generator.utils.GeneratorUtils;
import com.yexiao.demo.base.utils.FileUtils;
import org.apache.xmlbeans.impl.common.ReaderInputStream;
import sun.misc.Resource;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Main.class.getResourceAsStream("/static/form/form.json");
        Reader fileReader = new InputStreamReader(resourceAsStream);
        JSONObject jsonObject = JSONObject.parseObject(IoUtil.read(fileReader));
        JSONObject base = jsonObject.getJSONObject("base");
        Table table = new Table();
        table.setTableName("aaa_" + base.getString("name"));
        table.setTableComment(base.getString("remarks"));
        ArrayList<Column> columns = new ArrayList<>();
        for(JSONObject item :jsonObject.getJSONArray("fields").toJavaList(JSONObject.class)) {
            Column column = new Column();
            column.setComment(item.getString("remarks"));
            column.setName(item.getString("name"));
            column.setType(getFieldType(item.getString("type")));
            columns.add(column);
        }
        table.setColumns(columns);
        table = GeneratorUtils.FillTable(table);
        ByteArrayOutputStream byteArrayOutputStream = GeneratorUtils.generatorCode(table);
        FileOutputStream fileOutputStream = new FileOutputStream("D://a.zip");
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        fileOutputStream.close();
        byteArrayOutputStream.close();
        System.out.println(jsonObject);
        System.out.println(createTableSql(table));
    }

    public static String getFieldType(String type){
        switch (type){
            case "date":
                return "date";
            case "radio":
            case "input":
                return "varchar";
            default:
                return "varchar";
        }
    }

    public static String getSqlType(String type,Integer length){
        switch (type){
            case "date":
                return "date";
            case "varchar":
                return "varchar(" + length + ")";
        }
        return null;
    }

    public static String createTableSql(Table table){
        StringBuilder builder = new StringBuilder();
        builder.append("create table ")
                .append(table.getTableName())
                .append("( ");
        for(Column column : table.getColumns()){
            builder.append(column.getName())
                    .append(" ")
                    .append(getSqlType(column.getType(),64))
                    .append(" comment ")
                    .append("'")
                    .append(column.getComment())
                    .append("'")
                    .append(",");
        }
        builder.replace(builder.length()-1,builder.length(),")");
        return builder.toString();
    }


}




