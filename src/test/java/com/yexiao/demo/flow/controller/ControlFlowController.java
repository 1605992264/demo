//package com.yexiao.demo.flow.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.utils.Query;
//import com.txdata.common.utils.R;
//import com.txdata.flow.service.ControlFlowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/flowAudit")
//public class ControlFlowController {
//
//    @Autowired
//    ControlFlowService controlFlowService;
//
//    /**
//     * 查看已审批列表
//     * */
//    @PostMapping("/approvedList")
//    public R approvedList(@RequestParam Map<String,Object> param){
//        param.put("mark",param.remove("allmoney[mark]"));
//        param.put("allmoney",param.remove("allmoney[value]"));
//        Query query = new Query(param);
//        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
//        page = controlFlowService.approvedList(page,query);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//    }
//
//    /**
//     * 查看待审批列表
//     * */
//    @PostMapping("/notApprovedList")
//    public R notApprovedList(@RequestParam Map<String,Object> param){
//        param.put("mark",param.remove("allmoney[mark]"));
//        param.put("allmoney",param.remove("allmoney[value]"));
//        Query query = new Query(param);
//        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
//        page = controlFlowService.notApprovedList(page,query);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//    }
//
//    /**
//     * 抄送给我的
//     * */
//    @PostMapping("/copyToMe")
//    public R copyToMe(@RequestParam Map<String,Object> param){
//        param.put("mark",param.remove("allmoney[mark]"));
//        param.put("allmoney",param.remove("allmoney[value]"));
//        Query query = new Query(param);
//        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
//        page = controlFlowService.copyToMe(page,query);
//        JSONObject jsonMap = new JSONObject();
//        jsonMap.put("rows", page.getRecords());
//        jsonMap.put("pageSize", page.getSize());
//        jsonMap.put("pageNo", page.getCurrent());
//        jsonMap.put("count", page.getTotal());
//        return R.success(jsonMap);
//    }
//
//    /**
//     * 审批接口
//     * */
//    @PostMapping("audit")
//    public R audit(@RequestBody String json){
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        Map<String,Object> map = new HashMap<>();
//        map.put("formData",jsonObject);
//        map.put("orderId",jsonObject.getString("procInsId"));
//        map.put("flag",jsonObject.remove("flag"));
//        map.put("comment",jsonObject.remove("comment"));
//        map.put("filePath",jsonObject.getString("hisPath"));
//        if(controlFlowService.completeTask(map) == null) {
//            return R.success("审批流程已结束");
//        }
//        return R.success();
//    }
//
//    /**
//     * 查看当前任务
//     * */
//    @PostMapping("task")
//    public R task(@RequestParam String orderId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("formObject",controlFlowService.task(orderId));
//        return R.success(map);
//    }
//
//    /**
//     * 查看虚拟流程路线
//     * */
//    @PostMapping("falseProcessPath")
//    public R falseProcessPath(@RequestParam String orderId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("rows", controlFlowService.falseProcessPath(orderId));
//        return R.success(map);
//    }
//
//}
