package com.yexiao.demo.base.utils.execl;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.domain.UserDO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/10/21 10:50
 **/
public class ExeclDemo {


    public static void main(String[] args) throws Exception {
        Workbook workbook = new XSSFWorkbook(new File("D://a.xlsx"));

        for(String tableName : ExeclMap.tableInfo.keySet()){
            // 替换标题
            Sheet sheet = workbook.getSheet(tableName);
            ExeclUtils.setTitleName(ExeclMap.tableInfo.get(tableName),sheet);

            List<JSONObject> sheetData = ExeclUtils.getSheetData(tableName, workbook);

            switch (tableName){
                case ExeclMap.userInfo:
                    /**
                     * 做处理
                     * */
                    for(JSONObject jsonObject : sheetData){
                        UserDO userDO = jsonObject.toJavaObject(UserDO.class);
                        System.out.println(userDO);
                    }
                    break;
            }
        }
    }





}
