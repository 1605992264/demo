package com.yexiao.demo.base.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/9/23 11:20
 **/
@Slf4j
public class ExeclUtils {

    public static List<JSONObject> getSheetData(Integer sheetNum, Workbook workbook){
        List<JSONObject> jsonObjectList = new LinkedList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        for( int nowRow = 1 ; nowRow <= sheet.getLastRowNum(); nowRow++){
            JSONObject rowData = getRowData(nowRow,sheet,getRowTitle(0,sheet));
            log.info(rowData.toJSONString());
            jsonObjectList.add(rowData);
        }
        return jsonObjectList;
    }

    public static List<JSONObject> getSheetData(String sheetName, Workbook workbook){
        List<JSONObject> jsonObjectList = new LinkedList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            throw new RuntimeException("没有名字叫:" + sheetName + " 这个sheet");
        }
        for( int nowRow = 1 ; nowRow <= sheet.getLastRowNum(); nowRow++){
            JSONObject rowData = getRowData(nowRow,sheet,getRowTitle(0,sheet));
            log.info(rowData.toJSONString());
            if(rowData.size() != 0){
                jsonObjectList.add(rowData);
            }
        }
        return jsonObjectList;
    }


    public static JSONObject getRowData(Integer nowRow, Sheet sheet, List<String> title){
        JSONObject jsonObject = new JSONObject();
        Row row = sheet.getRow(nowRow);
        if(row == null){
            return jsonObject;
        }
        for( int nowCell = 0; nowCell < row.getLastCellNum() ; nowCell ++){
            Cell cell = row.getCell(nowCell);
            if(!"".equals(getCellValue(cell))) {
                log.info(getCellValue(cell));
                jsonObject.put(title.get(nowCell), getCellValue(cell));
            }
        }
        return jsonObject;
    }

    public static List<String> getRowTitle(Integer nowRow, Sheet sheet){
        List<String> title = new LinkedList<>();
        Row row = sheet.getRow(nowRow);
        for( int nowCell = 0; nowCell < row.getLastCellNum() ; nowCell ++){
            Cell cell = row.getCell(nowCell);
            if(!"".equals(getCellValue(cell))) {
                title.add(getCellValue(cell));
            }
        }
        return title;
    }

    /**
     * 获取cell的值
     * */
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(CellType.NUMERIC.equals(cell.getCellTypeEnum())){
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellTypeEnum()){
            case NUMERIC:
                //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                //空值
                cellValue = "";
                break;
            case ERROR:
                //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue.trim();
    }

}
