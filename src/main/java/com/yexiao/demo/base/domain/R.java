package com.yexiao.demo.base.domain;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/27 9:29
 * 统一返回格式
 **/
public class R<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public R(){
        this.code = 200;
        this.message = "success";
    }

    public static R success(){
        R r = new R();
        return r;
    }

    public static R success(String message){
        R r = new R();
        r.message = message;
        return r;
    }

    public static R success(IPage page){
        R r = new R();
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("pageSize",page.getSize());
        jsonMap.put("pageNo",page.getCurrent());
        jsonMap.put("rows",page.getRecords());
        jsonMap.put("count",page.getTotal());
        r.data = jsonMap;
        return r;
    }

    public static R success(Serializable serializable){
        R r = new R();
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("JSONObject",serializable);
        r.data = jsonMap;
        return r;
    }

    public static R success(Collection collection){
        R r = new R();
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows",collection);
        r.data = jsonMap;
        return r;
    }

    public static R success(BasePage basePage,Collection collection){
        R r = new R();
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("pageSize",basePage.getPageSize());
        jsonMap.put("pageNo",basePage.getPageNo());
        jsonMap.put("count",collection.size());
        jsonMap.put("rows",collection);
        r.data = jsonMap;
        return r;
    }

    public static R success(Map<String,Object> map){
        R r = new R();
        r.data = map;
        return r;
    }

    public static R success(JSONObject jsonObject){
        R r = new R();
        r.data = jsonObject;
        return r;
    }

    public static R success(String message, Map<String,Object> map){
        R r = new R();
        r.message = message;
        r.data = map;
        return r;
    }

    public static R error(){
        R r = new R();
        r.code = 500;
        r.message = "error";
        return r;
    }

    public static R error(String message){
        R r = new R();
        r.code = 500;
        r.message = message;
        return r;
    }


    public static R custom(Integer code,String message,Serializable o){
        R r = new R();
        r.code = code;
        r.message = message;
        r.data = o;
        return r;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
