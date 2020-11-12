package com.yexiao.demo.base.utils.execl;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/9/23 11:20
 **/
public class ExeclUtils {

    private static List<String> titleName = new ArrayList<>();


    /**
     * 根据sheet位置获取数据
     * @param sheetNum sheet位置
     * @param workbook 工作簿
     * */
    public static List<JSONObject> getSheetData(Integer sheetNum, Workbook workbook){
        List<JSONObject> jsonObjectList = new LinkedList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        if(sheet == null){
            throw new RuntimeException("没有页数为:" + sheetNum + " 这个sheet");
        }
        for( int nowRow = 1 ; nowRow <= sheet.getLastRowNum(); nowRow++){
            JSONObject rowData = getRowData(nowRow,sheet,getRowTitle(sheet));
            jsonObjectList.add(rowData);
        }
        return jsonObjectList;
    }

    /**
     * 根据sheet名称获取数据
     * @param sheetName sheet名称
     * @param workbook 工作簿
     * */
    public static List<JSONObject> getSheetData(String sheetName, Workbook workbook){
        List<JSONObject> jsonObjectList = new LinkedList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            throw new RuntimeException("没有名字叫:" + sheetName + " 这个sheet");
        }
        for( int nowRow = 1 ; nowRow <= sheet.getLastRowNum(); nowRow++){
            JSONObject rowData = getRowData(nowRow,sheet,getRowTitle(sheet));
            if(rowData.size() != 0){
                jsonObjectList.add(rowData);
            }
        }
        return jsonObjectList;
    }

    /**
     * 获取行数据并进行封装
     * @param sheet 当前sheet
     * @param nowRow 当前行
     * @param title 标题
     * */
    public static JSONObject getRowData(Integer nowRow, Sheet sheet, List<String> title){
        JSONObject jsonObject = new JSONObject();
        Row row = sheet.getRow(nowRow);
        if(row == null){
            return jsonObject;
        }
        for( int nowCell = 0; nowCell < row.getLastCellNum() ; nowCell ++){
            Cell cell = row.getCell(nowCell);
            if(!"".equals(getCellValue(cell))) {
                jsonObject.put(title.get(nowCell), getCellValue(cell));
            }
        }
        return jsonObject;
    }

    /**
     * 设置标题 把中文替换成英文
     * @param titleName 要替换的内容
     * @param sheet 要替换的sheet
     * */
    public static void setTitleName(Map<String,String> titleName,Sheet sheet){
        if(sheet == null){
            return;
        }
        ExeclUtils.titleName.clear();
        List<String> rowTitle = getRowTitle(sheet);
        for(String name : rowTitle){
            ExeclUtils.titleName.add(titleName.get(name));
        }
    }

    /**
     * 获取标题 默认第一行
     * */
    public static List<String> getRowTitle(Sheet sheet){
        if(ExeclUtils.titleName.size() != 0){
            return ExeclUtils.titleName;
        }
        List<String> title = new LinkedList<>();
        Row row = sheet.getRow(0);
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
