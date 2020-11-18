//package com.yexiao.demo.flow.controller;
//
//import cn.hutool.core.util.StrUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.txdata.common.utils.R;
//import com.txdata.flow.domain.BaseFlowDataDO;
//import com.txdata.flow.domain.HiCirculationDO;
//import com.txdata.flow.domain.ProcessNodeDO;
//import com.txdata.flow.service.ControlFlowService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author xuhf
// * */
//@RestController
//@RequestMapping("/flowAudit")
//public class ControlFlowController {
//
//    @Autowired
//    private ControlFlowService controlFlowService;
//
//    /**
//     * 待审批列表
//     *
//     * @param userId 调用接口的用户
//     * @param processCode 流程code
//     * @param systemCode 系统code
//     * */
//    @PostMapping("/notApprovedList")
//    public R notApprovedList(@RequestParam String userId,@RequestParam String systemCode,@RequestParam String processCode){
//        List<BaseFlowDataDO> baseFlowDataDOS = controlFlowService.notApprovedList(userId, systemCode, processCode);
//        return R.success(baseFlowDataDOS);
//    }
//
//    /**
//     * 已审批列表
//     *
//     * @param userId 调用接口的用户
//     * @param processCode 流程code
//     * @param systemCode 系统code
//     * */
//    @PostMapping("/approvedList")
//    public R approvedList(String userId,String systemCode,String processCode){
//        List<BaseFlowDataDO> baseFlowDataDOS = controlFlowService.approvedList(userId, systemCode, processCode);
//        return R.success(baseFlowDataDOS);
//    }
//
//    /**
//     * 我发起的列表
//     *
//     * @param userId 调用接口的用户
//     * @param processCode 流程code
//     * @param systemCode 系统code
//     * */
//    public JSONObject self(String userId, String processCode, String systemCode){
//        // todo: select a.id from flow_order a where a.create_by = userId
//        JSONObject json = new JSONObject();
//        json.put("实例id","123");
//        json.put("审批状态","审批中|结束|驳回");
//        json.put("处理人","张三,李四");
//        return json;
//    }
//
//    /**
//     * 启动/重启 流程
//     *
//     * @param userId 调用接口的用户
//     * @param processCode 流程code
//     * @param systemCode 系统code
//     * @param orderId 实例id  第一次启动为空, 驳回时重启传orderId
//     *
//     * @return orderId 实例id
//     * */
//    @PostMapping("startProcess")
//    public R newProcessFlow(String userId,String processCode,String systemCode,String orderId){
//        String s = controlFlowService.newProcessFlow(userId, processCode, systemCode, orderId);
//        if(StrUtil.isEmpty(s)){
//            return R.error("当前用户没有权限发起该流程");
//        }
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",s);
//        return R.success(map);
//    }
//
//    /**
//     * 流转历史
//     *
//     * @param systemCode 系统编码
//     * @param orderId 流程实例id
//     * */
//    @PostMapping("flowHistory")
//    public R flowHistory(String systemCode,String orderId){
//        // todo: 调用HiCirculationService.list() 即可获取流转历史
//        List<HiCirculationDO> hiCirculationDOS = controlFlowService.flowHistory(systemCode, orderId);
//        return R.success(hiCirculationDOS);
//    }
//
//    /**
//     * 查看某一节点的处理人
//     * @param systemCode 系统code
//     * @param nodeId 节点id
//     * @param initiator 发起人
//     * */
//    @PostMapping("nodeActor")
//    public JSONObject nodeActor(String nodeId, String systemCode, String initiator){
//        BaseFlowDataDO actorByNodeId = controlFlowService.getActorByNodeId(nodeId, systemCode);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("jsonObject",actorByNodeId);
//        return jsonObject;
//    }
//
//
//    /**
//     * 审批接口
//     *
//     * @param systemCode 系统code
//     * @param orderId 实例id
//     * @param userId 参与人id
//     * @param comment 审批意见
//     * @param fileUrl 附件
//     * @param flag 是否通过 0-驳回 1-通过
//     *
//     * */
//    @PostMapping("audit")
//    public R audit(String orderId,String systemCode,String userId,String comment,String fileUrl,String flag){
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        map.put("comment",comment);
//        map.put("fileUrl",fileUrl);
//        map.put("flag",flag);
//        ProcessNodeDO processNodeDO = controlFlowService.completeTask(map, systemCode, userId);
//        if(processNodeDO == null){
//            return R.success("流程结束");
//        }
//        return R.success("成功");
//    }
//
//
//
//
//
//
//
//
//
//
//
////    /**
////     * 查看已审批列表
////     * */
////    @PostMapping("/approvedList")
////    public R approvedList(@RequestParam Map<String,Object> param){
////        param.put("mark",param.remove("allmoney[mark]"));
////        param.put("allmoney",param.remove("allmoney[value]"));
////        Query query = new Query(param);
////        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
////        page = controlFlowService.approvedList(page,query);
////        JSONObject jsonMap = new JSONObject();
////        jsonMap.put("rows", page.getRecords());
////        jsonMap.put("pageSize", page.getSize());
////        jsonMap.put("pageNo", page.getCurrent());
////        jsonMap.put("count", page.getTotal());
////        return R.success(jsonMap);
////    }
////
////    /**
////     * 查看待审批列表
////     * */
////    @PostMapping("/notApprovedList")
////    public R notApprovedList(@RequestParam Map<String,Object> param){
////        param.put("mark",param.remove("allmoney[mark]"));
////        param.put("allmoney",param.remove("allmoney[value]"));
////        Query query = new Query(param);
////        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
////        page = controlFlowService.notApprovedList(page,query);
////        JSONObject jsonMap = new JSONObject();
////        jsonMap.put("rows", page.getRecords());
////        jsonMap.put("pageSize", page.getSize());
////        jsonMap.put("pageNo", page.getCurrent());
////        jsonMap.put("count", page.getTotal());
////        return R.success(jsonMap);
////    }
////
//////    /**
//////     * 抄送给我的
//////     * */
//////    @PostMapping("/copyToMe")
//////    public R copyToMe(@RequestParam Map<String,Object> param){
//////        param.put("mark",param.remove("allmoney[mark]"));
//////        param.put("allmoney",param.remove("allmoney[value]"));
//////        Query query = new Query(param);
//////        Page<JSONObject> page = new Page<>(query.getPageNo(), query.getPageSize());
//////        page = controlFlowService.copyToMe(page,query);
//////        JSONObject jsonMap = new JSONObject();
//////        jsonMap.put("rows", page.getRecords());
//////        jsonMap.put("pageSize", page.getSize());
//////        jsonMap.put("pageNo", page.getCurrent());
//////        jsonMap.put("count", page.getTotal());
//////        return R.success(jsonMap);
//////    }
////
//////    /**
//////     * 审批接口
//////     * */
//////    @PostMapping("audit")
//////    public R audit(@RequestBody String json){
//////        JSONObject jsonObject = JSONObject.parseObject(json);
//////        Map<String,Object> map = new HashMap<>();
//////        map.put("formData",jsonObject);
//////        map.put("orderId",jsonObject.getString("procInsId"));
//////        map.put("flag",jsonObject.remove("flag"));
//////        map.put("comment",jsonObject.remove("comment"));
//////        map.put("filePath",jsonObject.getString("hisPath"));
//////        if(controlFlowService.completeTask(map) == null)
//////            return R.success("审批流程已结束");
//////        return R.success();
//////    }
////
//    /**
//     * 查看当前任务
//     * */
//    @PostMapping("task")
//    public R task(@RequestParam String orderId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("formObject",controlFlowService.nowTask(orderId));
//        return R.success(map);
//    }
////
////    /**
////     * 查看虚拟流程路线
////     * */
////    @PostMapping("falseProcessPath")
////    public R falseProcessPath(@RequestParam String orderId){
////        Map<String,Object> map = new HashMap<>();
////        map.put("rows", controlFlowService.falseProcessPath(orderId));
////        return R.success(map);
////    }
//
//}
