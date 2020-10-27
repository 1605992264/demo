//package com.yexiao.demo.flow.dao;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.Map;
//
//
///**
// * @author xu hf
// * formdata数据
// * 方便修改
// * */
//@Mapper
//public interface FormDataDao {
//
//    /**
//     * 报销已审批列表
//     * */
//    Page<JSONObject> approvalFormDataList(Page<JSONObject> page, @Param("entity") Map<String, Object> map, @Param("userId") String userId);
//
//    /**
//     * 报销待审批列表
//     * */
//    Page<JSONObject> notApprovalFormDataList(Page<JSONObject> page, @Param("entity") Map<String, Object> map, @Param("orderIds") String orderIds);
//
//    /**
//     * 抄送给我的
//     * */
//    Page<JSONObject> copyToMeFormDataList(Page<JSONObject> page, @Param("entity") Map<String, Object> map, @Param("userId") String userId);
//
//
//}
