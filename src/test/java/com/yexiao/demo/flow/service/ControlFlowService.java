//package com.yexiao.demo.flow.service;
//
//import cn.hutool.core.util.StrUtil;
//import com.txdata.flow.dao.ControlFlowDao;
//import com.txdata.flow.dao.ProcessNodeDao;
//import com.txdata.flow.domain.*;
//import com.txdata.flow.utils.ConstantEnum;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * @author xuhf
// * 完成各个节点任务
// * 控制流转方向
// * */
//@Service
//public class ControlFlowService {
//
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private ControlFlowDao controlFlowDao;
//    @Autowired
//    private FlowProcessService flowProcessService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private ProcessNodeDao processNodeDao;
//    @Autowired
//    private ProcessActorService processActorService;
//    @Autowired
//    private NodeVariableService nodeVariableService;
//    @Autowired
//    private OrderTaskService orderTaskService;
//    @Autowired
//    private HiTaskService hiTaskService;
//    @Autowired
//    private TaskActorService taskActorService;
//    @Autowired
//    private HiActorService hiActorService;
//    @Autowired
//    private HiCirculationService hiCirculationService;
//    @Autowired
//    private HiCirculationDetailedService hiCirculationDetailedService;
//    @Autowired
//    private FuserService userService;
////    @Autowired
////    private NotifyService notifyService;
//
//
//    /**
//     * 获取当前实例任务
//     * */
//    public OrderTaskDO nowTask(String orderId){
//        return orderTaskService.getOrderTaskByOrderId(orderId);
//    }
//
//    /**
//     * 获取虚拟流程路径
//     *  @param orderId 实例id
//     *  @return 节点路径
//     * */
//    public List<ProcessNodeDO> falseProcessPath(String orderId,String systemCode){
//        // 虚拟节点流程
//        List<ProcessNodeDO> fictitiousNodePath = new ArrayList<>();
//        ProcessNodeDO startNode = processNodeDao.getStartNode(orderId);
//        ProcessNodeDO node = startNode;
//        do {
//            fictitiousNodePath.add(node);
//            node = fictitiousNextNode(node,orderId,systemCode);
//        }while (node != null);
//        return fictitiousNodePath;
//    }
//
//    /**
//     *获取虚拟流程节点路径
//     * 用， 分割 nodeId,nodeId...
//     * */
//    public String nodePath(String orderId,String nowNodeId,String userId){
//        StringBuilder builder = new StringBuilder();
//        for(ProcessNodeDO nodeDO : falseProcessPath(orderId,userId)){
//            if(nodeDO.getId().equals(nowNodeId)){
//                if(ConstantEnum.START_NODE.equals(nodeDO.getType())){
//                    builder.append(",");
//                }
//                break;
//            }
//            builder.append(nodeDO.getId()).append(",");
//        }
//        return builder.substring(0,builder.length()-1);
//    }
//
//    /**
//     * 查看待审核列表接口
//     * */
//    public List<BaseFlowDataDO> notApprovedList(String userId,String systemCode,String processCode){
//        // 当前用户可以审批的流程节点
//        String orderIds = controlFlowDao.notApprovalList(userId, systemCode,processCode);
//
//        List<BaseFlowDataDO> baseFlowData = controlFlowDao.getBaseFlowData(orderIds, systemCode);
//        return baseFlowData;
//    }
//
//    /**
//     * 查看已审核接口
//     * */
//    public List<BaseFlowDataDO> approvedList(String userId,String systemCode,String processCode){
//
//        //todo: 根据实例id 返回封装的数据 orderId,auditStatus,auditActor;
//        String orderIds = controlFlowDao.approvalList(userId, systemCode, processCode);
//        List<BaseFlowDataDO> baseFlowData = controlFlowDao.getBaseFlowData(orderIds, systemCode);
//        return baseFlowData;
//    }
//
//    /**
//     * 流转历史
//     * */
//    public List<HiCirculationDO> flowHistory(String systemCode,String orderId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        List<HiCirculationDO> list = hiCirculationService.list(map,systemCode);
//        return list;
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
//    @Transactional
//    public String newProcessFlow(String userId,String processCode,String systemCode,String orderId){
//        String processId = flowProcessService.queryNewIdByCode(processCode);
//        ProcessNodeDO startNode = processNodeDao.getStartNodeByProcessId(processId);
//        BaseFlowDataDO actorByNodeId = controlFlowDao.getActorByNodeId(startNode.getId(), systemCode);
//        String actorIds = actorByNodeId.getActorIds();
//        List<String> list = Arrays.asList(actorIds.split(","));
//        if(!list.contains(userId)){
//            return null;
//        }else {
//            if(StrUtil.isEmpty(orderId)){
//                // 新增流程
//                OrderDO orderDO = new OrderDO();
//                orderDO.setProcId(processId);
//                orderDO.setCreateBy(userId);
//                orderService.save(orderDO);
//                OrderTaskDO orderTaskDO = new OrderTaskDO();
//                orderTaskDO.setOrderId(orderDO.getId());
//                orderTaskDO.setNodeId(startNode.getId());
//                orderTaskDO.setTaskName(startNode.getName());
//                orderTaskService.save(orderTaskDO);
//                orderId = orderDO.getId();
//                HiCirculationDO hiCirculationDO = getHiCirculationDO(orderId, startNode.getId(), startNode.getName());
//                hiCirculationService.save(hiCirculationDO);
//                hiCirculationDetailedService.save(getHiCirculationUser(userId, ConstantEnum.OPERATE_TYPE_START,
//                        hiCirculationDO.getId(),null));
//            }else {
//                //重启流程
//                OrderTaskDO orderTask= orderTaskService.getOrderTaskByOrderId(orderId);
//                String createBy = orderService.get(orderId).getCreateBy();
//                if(!orderTask.getNodeId().equals(startNode.getId()) || !createBy.equals(userId)){
//                    // 不是在驳回状态(开始节点)重启流程 或者 重启流程者不是发起人
//                    return null;
//                }
//            }
//            Map<String,Object> map = new HashMap<>();
//            map.put("orderId",orderId);
//            completeTask(map, systemCode, userId);
//            return orderId;
//        }
//        // todo: 1.判断当前用户是否可以发起该流程
//        // todo: 获取流程的发起节点 查询当前用户是否符合
//        // todo: 2.通过processCode发起流程 即保存order 和 order_task
//        // todo: 3.保存发起人到流转历史中
//    }
//
//    /**
//     * 查询该节点的参与人
//     * */
//    public BaseFlowDataDO  getActorByNodeId(String nodeId,String systemCode){
//        return  controlFlowDao.getActorByNodeId(nodeId,systemCode);
//    }
//
////    /**
////     * 查看抄送给我的
////     * */
////    public Page<JSONObject> copyToMe(Page<JSONObject> page,Map<String, Object> map){
////        Page<JSONObject> jsonObjectPage = reimbursementService.copyToMeFormDataList(page,map, UserUtils.getUser().getId());
////        // 为每个列表添加下个审批人
////        List<JSONObject> records = jsonObjectPage.getRecords();
////        for(JSONObject formData : records){
////            formData.put("auditName",getNowApprover(formData.getString("procInsId")));
////        }
////        return jsonObjectPage;
////    }
//
////    /**
////     * 获取当前流程的审批人
////     * @param orderId 实例id
////     * @return 返回已 ，分割的人名
////     * */
////    public String getNowApprover(String orderId){
////        OrderTaskDO task = task(orderId);
////        // 当前任务已结束
////        if(task == null){
////            return "";
////        }
////        // 获取当前审批节点
////        ProcessNodeDO nowNode = processNodeDao.get(task.getNodeId());
////        if(nowNode != null && !ConstantEnum.START_NODE.equals(nowNode.getType())){
////            // 获取审批人
////            Set<String> userIdListByNode = getUserIdListByNode(nowNode, orderService.get(orderId).getCreateBy());
////            if(userIdListByNode != null && userIdListByNode.size() > 0) {
////                // 返回
////               return userDao.findUserNamesByIDS(userIdListByNode);
////            }
////        }
////        return "";
////    }
//
//    /**
//     * 完成节点任务 （审批节点或发起人节点）
//     * @param param 执行参数
//     * 返回下一个执行节点
//     */
//    @Transactional
//    public ProcessNodeDO completeTask(Map<String,Object> param,String systemCode,String userId){
//        Map<String,Object> map = new HashMap<>();
//        Object orderId = param.remove("orderId");
//        if(orderId == null || "".equals(orderId)){
//            throw new RuntimeException("没有实例id");
//        }
//        map.put("orderId",orderId);
//        List<OrderTaskDO> list = orderTaskService.list(map);
//        if(list == null || list.size() == 0) {
//            throw new RuntimeException("当前流程已结束");
//        }
//        OrderTaskDO taskDO = list.get(0);
//        // 获取当前任务节点
//        ProcessNodeDO processNodeDO = processNodeDao.get(taskDO.getNodeId());
//        // 根据当前节点类型 执行不同的处理
//        if (ConstantEnum.APPROVAL_NODE.equals(processNodeDO.getType())){
//            if( !getApproverList(processNodeDO,taskDO.getOrderId(),systemCode).contains(userId)){
//                throw new RuntimeException("当前用户没有审批权限");
//            }
//
//            //完成审批节点并返回下一个审批节点
//            ProcessNodeDO nextNode = processNodeHandler(processNodeDO,orderId.toString(),taskDO, param,systemCode,userId);
//            if(!processNodeDO.equals(nextNode)){
//                // 会签只执行一遍
//                nextNode = advancedSettingHandler(nextNode, taskDO.getOrderId(),systemCode,userId);
//            }
//            // 如果驳回到开始节点
//            if(nextNode == null){
//                // 处理流程结束
//                processFinishHandler(orderId.toString());
//            }
//            //notifyHandler(nextNode, orderId.toString(), param.get("flag") == null ? "1" : param.get("flag").toString());
//            return nextNode;
//        }else if(ConstantEnum.START_NODE.equals(processNodeDO.getType())){
//            //验证流程是否正确
//            falseProcessPath(orderId.toString(),userId);
//            // 重新提交
//            HiCirculationDO nowHiCirculationByOrder = hiCirculationService.getNowHiCirculationByOrder(orderId.toString());
//            if(nowHiCirculationByOrder != null && nowHiCirculationByOrder.getSort() != 0){
//                hiCirculationDetailedService.save(getHiCirculationUser(userId,
//                        ConstantEnum.OPERATE_TYPE_START,nowHiCirculationByOrder.getId(),null));
//            }
//            //完成发起人节点并返回下一个审批节点
//            ProcessNodeDO auditNode = processNodeHandler(processNodeDO, taskDO.getOrderId(),
//                    null,null,systemCode,userId);
//            auditNode = advancedSettingHandler(auditNode, String.valueOf(orderId),systemCode,userId);
//            if(auditNode == null){
//                // 处理流程结束
//                processFinishHandler(orderId.toString());
//            }
//            //notifyHandler(auditNode,orderId.toString(),"1");
//            return auditNode;
//        }
//        return null;
//    }
//
////    /**
////     * 需要通知时调用
////     * @param nextNode 当前节点（发起节点或审批节点）
////     * @param orderId 当前实例id
////     * @param flag 是否为通过 1 是   0 不是
////     * */
////    private void notifyHandler(ProcessNodeDO nextNode, String orderId, String flag){
////        if(nextNode == null){
////            // 流程结束通知
////            String createBy = orderService.get(orderId).getCreateBy();
////            // 发送给发起人
////            sendUser(createBy,orderId,ProcessNotifyType.FINISH);
////        }else if(ConstantEnum.START_NODE.equals(nextNode.getType())){
////            // 如果驳回到了发起节点
////            // 获取发起人
////            String createBy = orderService.get(orderId).getCreateBy();
////            // 发送给发起人
////            sendUser(createBy,orderId,ProcessNotifyType.START);
////        }else {
////            // 审批通知
////            if("0".equals(flag)) {
////                // 驳回通知
////                sendNotifyByAuditNode(nextNode, orderId, ProcessNotifyType.REJECT);
////            } else {
////                sendNotifyByAuditNode(nextNode, orderId, ProcessNotifyType.PASS);
////            }
////        }
////    }
//
//
//
//    /**
//     * 高级设置处理
//     * @param processNodeDO 当前审批节点
//     * @param orderId 当前流程
//     * */
//    private ProcessNodeDO advancedSettingHandler(ProcessNodeDO processNodeDO, String orderId,String systemCode,String userId){
//        if(processNodeDO == null || ConstantEnum.START_NODE.equals(processNodeDO.getType())){
//            // 流程结束 或者 驳回到了开始节点
//            return processNodeDO;
//        }
//        // 获取流程设置
//        FlowProcessDO flowProcessDO = flowProcessService.get(processNodeDO.getProcessId());
//
//        // 审批人去重
//        if("1".equals(flowProcessDO.getAutoRepeat())){
//            /**
//             * 同一个审批人在流程中出现多次，仅保留第一个
//             * */
//            ProcessNodeDO nextNode = processInstanceDeduplicationHandler(processNodeDO,orderId,systemCode,userId);
//            //已自动通过到下一审批节点
//            if(!processNodeDO.equals(nextNode)){
//                processNodeDO = advancedSettingHandler(nextNode,orderId,systemCode,userId);
//            }
//        }else if("2".equals(flowProcessDO.getAutoRepeat())){
//            /**
//             * 同一个审批人仅在连续出现时，自动去重
//             * */
//            ProcessNodeDO nextNode = continuousDeduplicationHandler(processNodeDO,orderId,systemCode,userId);
//            //已自动通过到下一审批节点
//            if(!processNodeDO.equals(nextNode)){
//                processNodeDO = advancedSettingHandler(nextNode,orderId,systemCode,userId);
//            }
//        }
//        if(processNodeDO == null){
//            // 流程结束
//            return null;
//        }
//        //发起人审批时自动通过。设置为发起人自己的审批节点不会自动通过
//        if("1".equals(flowProcessDO.getMyAuditAutoPass()) && !ConstantEnum.AUDIT_ONESELF.equals(processNodeDO.getAuditType())){
//            // 发起人
//            ProcessNodeDO nextNode = createByAuditHandler(processNodeDO,orderId,systemCode,userId);
//            //已自动通过到下一审批节点
//            if(!processNodeDO.equals(nextNode)){
//                processNodeDO = advancedSettingHandler(nextNode,orderId,systemCode,userId);
//            }
//        }
//        return processNodeDO;
//    }
//
//    /**
//     * 自动通过时调用
//     * @param nodeDO 节点
//     * @param orderId 实例id
//     * @return 下一审批节点
//     * */
//    private ProcessNodeDO getAuditByAutoPass(ProcessNodeDO nodeDO, String orderId,String systemCode,String userId){
//        HiTaskDO hiTaskDO = getHiTaskDO(nodeDO.getId(), orderId, "1");
//        hiTaskService.save(hiTaskDO);
//        //获取下一节点
//        ProcessNodeDO processNodeDO = nextNode(nodeDO, orderId,systemCode);
//        if(processNodeDO == null){
//            // 结束
//            return null;
//        }
//        // 如果下一节点是审批节点 直接返回
//        if(ConstantEnum.APPROVAL_NODE.equals(processNodeDO.getType())){
//            updateTaskNode(processNodeDO,orderId);
//            return processNodeDO;
//        }
//        return processNodeHandler(processNodeDO,orderId,null,null,systemCode,userId);
//
//    }
//
//    /**
//     * 处理是否自动通过
//     * @param nodeDO 当前节点
//     * @param orderId 实例id
//     * @param userIdList 有审批该节点权限的人
//     * @param deduplicationUserIdList 已审批过该流程的人
//     * @return 返回审批节点
//     * */
//    private ProcessNodeDO autoPassHandler(ProcessNodeDO nodeDO, String orderId, Set<String> userIdList,
//                                          Set<String> deduplicationUserIdList,String systemCode,String userId){
//        String hiId = hiCirculationService.getNowHiCirculationByOrder(orderId).getId();
//        // 需要流转记录   把每个审批人存入历史流转表中
//        for(String item : deduplicationUserIdList) {
//                /*
//                HiOperateDO hiOperateDO = getHiOperateDO(nodeDO.getId(), orderId);
//                hiOperateDO.setUserId(userId);
//                hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_AUTO_COMMIT);
//                hiOperateDO.setApprovalAdvice(userDao.get(userId).getName() + " 自动通过");
//                hiOperateService.save(hiOperateDO);
//                 */
//            HiCirculationDetailedDO hiCirculationUser = getHiCirculationUser(item, ConstantEnum.OPERATE_TYPE_AUTO_COMMIT, hiId, null);
//            hiCirculationDetailedService.save(hiCirculationUser);
//        }
//
//        if(ConstantEnum.AND_SIGN_METHOD.equals(nodeDO.getCounterSign())){
//            // 会签
//            // 获取当前流转历史id
//            // 如果审批人以前已审批过 则自动通过
//            if(userIdList.size() == deduplicationUserIdList.size()){
//                return getAuditByAutoPass(nodeDO,orderId,systemCode,userId);
//            }else {
//                // 过滤掉要去重的人
//                for(String item : deduplicationUserIdList) {
//                    TaskActorDO taskActorDO = getTaskActorDo(item,orderTaskService.getOrderTaskByOrderId(orderId).getId(),"1");
//                    taskActorService.save(taskActorDO);
//                }
//            }
//        }else {   // 或签
//            // 如果审批人以前已审批过 则自动通过
//            if(userIdList.size() == deduplicationUserIdList.size()){
//                /*
//                // 存入历史流转表中
//                HiOperateDO hiOperateDO = getHiOperateDO(nodeDO.getId(), orderId);
//                hiOperateDO.setUserId(null);
//                hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_AUTO_COMMIT);
//                hiOperateDO.setApprovalAdvice(nodeDO.getName()+" 节点自动通过");
//                hiOperateService.save(hiOperateDO);
//                 */
//                return getAuditByAutoPass(nodeDO,orderId,systemCode,userId);
//            }else {
//                // 过滤掉要去重的人
//                for(String item : deduplicationUserIdList) {
//                    TaskActorDO taskActorDO = getTaskActorDo(item,orderTaskService.getOrderTaskByOrderId(orderId).getId(),"1");
//                    taskActorService.save(taskActorDO);
//                }
//            }
//        }
//        return nodeDO;
//    }
//
//    /**
//     * 发起人审批时自动通过
//     * @param node 当前审批节点
//     * @param orderId 实例id
//     * @return 审批节点
//     * */
//    private ProcessNodeDO createByAuditHandler(ProcessNodeDO node, String orderId,String systemCode,String userId){
//        // 获取发起人
//        String createBy = orderService.get(orderId).getCreateBy();
//        // 获取当前节点有审批权限的人
//        Set<String> userIdList = getUserIdListByNode(node, createBy,systemCode);
//        if(userIdList == null ||userIdList.size() == 0){
//            return node;
//        }
//        if(userIdList.contains(createBy)) { // 如果包含发起人审批
//
//            // 获取当前流程节点已审批了的人
//            Set<String> approvedUserIdList = taskActorService.userIdListByOrderId(orderId);
//            if(approvedUserIdList.size() <= 0 || !approvedUserIdList.contains(createBy)){
//                // 保存到已审批审批人中
//                taskActorService.save(getTaskActorDo(createBy,orderTaskService.getOrderTaskByOrderId(orderId).getId(),"1"));
//                // 会签
//                String hiId = hiCirculationService.getNowHiCirculationByOrder(orderId).getId();
//                HiCirculationDetailedDO hiCirculationUser = getHiCirculationUser(createBy, ConstantEnum.OPERATE_TYPE_AUTO_COMMIT, hiId, null);
//                hiCirculationDetailedService.save(hiCirculationUser);
//
//            }
//            approvedUserIdList = taskActorService.userIdListByOrderId(orderId);
//            if(userIdList.size() == approvedUserIdList.size()){
//                // 自动通过
//                return getAuditByAutoPass(node,orderId,systemCode,userId);
//            }
//        }
//        /**
//         * 找出当前节点没审批的人
//         * 把发起人加入其中
//         * 判断当前节点是否审批完成
//         * 返回审批节点
//         * */
//        return node;
//    }
//
//    /**
//     * 获取已审批了的人的id
//     * @param userIdList 有审批该节点权限的人
//     * @param whereMap 查询条件
//     * @return 当前节点应该去重的人
//     * */
//    private Set<String> getDeduplicationUserIdList(Set<String> userIdList,Map<String,Object> whereMap){
//        // 当前审批人要去重的人
//        Set<String> deduplicationUserIdList = new HashSet<>();
//        // 获取历史中已经审批了的人
//        List<String> hiActorIdList = hiTaskService.getUserIdByWhere(whereMap);
//        // 找出当前节点以前审批了的人
//        for(String userId : userIdList){
//            // 如果已审批过
//            if(hiActorIdList.contains(userId)){
//                // 放入approvedUserIdList中
//                deduplicationUserIdList.add(userId);
//            }
//        }
//        return deduplicationUserIdList;
//    }
//
//
//
//    /**
//     *
//     * 同一个审批人在流程中出现多次，仅保留第一个
//     * @param nodeDO 节点
//     * @param orderId 实例id
//     * @return 审批节点
//     * */
//    private ProcessNodeDO processInstanceDeduplicationHandler(ProcessNodeDO nodeDO, String orderId,String systemCode,String userId){
//        // 获取发起人
//        String createBy = orderService.get(orderId).getCreateBy();
//        // 获取当前节点有审批权限的人
//        Set<String> userIdList = getUserIdListByNode(nodeDO, createBy,systemCode);
//        if(userIdList == null ||userIdList.size() == 0){
//            return nodeDO;
//        }
//        // 当前流程中已审批了的人
//        Map<String,Object> whereMap = new HashMap<>();
//        whereMap.put("orderId",orderId);
//        // 当前节点要去重的人
//        Set<String> deduplicationUserIdList = getDeduplicationUserIdList(userIdList,whereMap);
//        return autoPassHandler(nodeDO,orderId,userIdList,deduplicationUserIdList,systemCode,userId);
//    }
//
//    /**
//     * 连续去重处理
//     * 同一个审批人仅在连续出现时，自动去重
//     * @param nodeDO 节点
//     * @param orderId 实例id
//     * @return 审批节点
//     * */
//    private ProcessNodeDO continuousDeduplicationHandler(ProcessNodeDO nodeDO, String orderId,String systemCode,String userId){
//        // 获取发起人
//        String createBy = orderService.get(orderId).getCreateBy();
//        // 获取当前节点有审批权限的人
//        Set<String> userIdList = getUserIdListByNode(nodeDO, createBy,systemCode);
//        if(userIdList == null ||userIdList.size() == 0){
//            return nodeDO;
//        }
//        // 当前流程上一节点id
//        HiTaskDO prevHiTask = hiTaskService.getPrevHiTask(orderId);
//        if(prevHiTask == null){
//            return nodeDO;
//        }
//        String prevNodeId = prevHiTask.getNodeId();
//        // 当前流程上一节点中已审批了的人
//        Map<String,Object> whereMap = new HashMap<>();
//        whereMap.put("orderId",orderId);
//        whereMap.put("nodeId",prevNodeId);
//        // 当前节点要去重的人
//        Set<String> deduplicationUserIdList = getDeduplicationUserIdList(userIdList,whereMap);
//        return autoPassHandler(nodeDO,orderId,userIdList,deduplicationUserIdList,systemCode,userId);
//    }
//
//    /**
//     * 节点处理
//     * @param nodeDO 当前要处理节点
//     * @param orderId 当前实例id
//     * @param taskDO 当前实例任务
//     * @param params 审批参数
//     * @return 返回下一审批节点
//     * */
//    private ProcessNodeDO processNodeHandler(ProcessNodeDO nodeDO, String orderId, OrderTaskDO taskDO, Map<String,Object> params,String systemCode,String userId){
//        ProcessNodeDO returnNode = null;
//        //完成节点操作
//        switch (nodeDO.getType()){
//            case ConstantEnum.COPY_NODE:
//                // 完成抄送节点
//                returnNode = completeCopyNode(nodeDO,orderId,systemCode);
//                break;
//            case ConstantEnum.APPROVAL_NODE:
//                // 完成审批节点
//                /**
//                 * returnNode
//                 * 返回当前节点 节点未完成
//                 * 返回null 完成当前审批节点
//                 * 返回其他审批节点 即驳回了
//                 * */
//                returnNode = completeApproveNode(taskDO, params,systemCode,userId);
//                break;
//            case "8": // 空节点
//                break;
//            case ConstantEnum.CONDITIONAL_NODE:
//                // 条件节点
//                break;
//        }
//        ProcessNodeDO nextNode;
//        if(returnNode == null){ // 审批结束
//            // 找到下一节点
//            nextNode = nextNode(nodeDO, orderId,systemCode);
//            // 如果是审批节点或是开始节点 存入审批记录中
//            if(ConstantEnum.START_NODE.equals(nodeDO.getType())
//                    || ConstantEnum.APPROVAL_NODE.equals(nodeDO.getType())){
//                HiTaskDO hiTaskDO = getHiTaskDO(nodeDO.getId(),orderId,"0");
//                hiTaskService.save(hiTaskDO);
//                saveHiActor(orderTaskService.getOrderTaskByOrderId(orderId).getId(),hiTaskDO.getId());
//            }
//        }else if(ConstantEnum.APPROVAL_NODE.equals(nodeDO.getType())){
//            if(!returnNode.equals(nodeDO)) {
//                // 驳回
//                /**
//                 * 驳回删除返回节点之后的审批记录表和审批人
//                 * 把以前的审批记录 放入历史中后 删除掉
//                 * */
//                if (ConstantEnum.START_NODE.equals(returnNode.getType())) {
//                    /**
//                     * 删除当前流程审批记录表
//                     * */
//                    hiTaskService.deleteHiTask(orderId, null);
//                } else {
//                    // 获取上一审批节点
//                    HiTaskDO prevHiTask = hiTaskService.getPrevHiTask(orderId);
//                    // 删除驳回节点之后的审批记录
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("orderId", orderId);
//                    Integer MaxSort = hiTaskService.count(map);
//                    Integer sort = prevHiTask.getSort();
//                    for (Integer i = sort; i < MaxSort; i++) {
//                        hiTaskService.deleteHiTask(orderId, i.toString());
//                    }
//                }
//                // 更新当前节点任务
//                updateTaskNode(returnNode, orderId);
//                return returnNode;
//            }else {
//                // 会签未结束
//                return returnNode;
//            }
//        } else {
//            // 其他
//            nextNode = nextNode(returnNode, orderId,systemCode);
//        }
//
//        if(nextNode == null){
//            // 流程结束
//            return null;
//        }if(ConstantEnum.APPROVAL_NODE.equals(nextNode.getType())){
//            Map<String,Object> map = new HashMap<>();
//            map.put("orderId",orderId);
//            /**
//             * 放入审批记录中
//             * */
//            // 更新当前节点任务
//            updateTaskNode(nextNode,orderId);
//            return nextNode;
//        }else {
//            return processNodeHandler(nextNode,orderId,taskDO,params,systemCode,userId);
//        }
//    }
//
//    /**
//     * 处理流程结束
//     * @param orderId 实例id
//     * */
//    private void processFinishHandler(String orderId){
//        // 流程已结束
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        orderTaskService.deleteByWhere(map);
//            /*
//            // 流程已结束 放入流转历史中
//            HiOperateDO hiOperateDO = getHiOperateDO(null,orderId);
//            // 结束
//            hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_END);
//            hiOperateService.save(hiOperateDO);
//            */
//        HiCirculationDO hiCirculationDO = getHiCirculationDO(orderId, null, "流程结束");
//        hiCirculationService.save(hiCirculationDO);
//        hiCirculationDetailedService.save(getHiCirculationUser(null,ConstantEnum.OPERATE_TYPE_END,hiCirculationDO.getId(),null));
//    }
//
//    /**
//     * 根据当前节点获取所有有权限审批的人员
//     * @param node 节点
//     * @param createBy 发起人
//     * */
//    public Set<String> getUserIdListByNode(ProcessNodeDO node, String createBy,String systemCode){
//        Set<String> userIdList = new HashSet<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("nodeId", node.getId());
//        List<ProcessActorDO> processActorDOList = processActorService.list(map);
//        for(ProcessActorDO item : processActorDOList){
//            switch (item.getType()) {
//                case ConstantEnum.ACTOR_CLIENT:
//                    // 指定的人
//                    userIdList.add(item.getValue());
//                    break;
//                case ConstantEnum.ACTOR_ROLE:
//                    // 3角色
//                    userIdList.addAll(userService.findUserIdSetByRoleId(item.getValue(),systemCode));
//                    break;
//                case ConstantEnum.ACTOR_ALL_CLIENT:
//                    // 所有人
//                    userIdList.addAll(userService.findAllUserByOfficeId(null,systemCode));
//                    return userIdList;
//                case ConstantEnum.ACTOR_DEPT_CLIENT:
//                    // 5部门
//                    userIdList.addAll(userService.findAllUserByOfficeId(item.getValue(),systemCode));
//                    break;
//                case ConstantEnum.ACTOR_MYSELF:
//                    // 发起人自己审批
//                    userIdList.add(createBy);
//                    break;
//            }
//        }
//        return userIdList;
//    }
//
//    /**
//     * 替换成新的任务
//     * @param nextNode 新的节点
//     * @param orderId 流程实例id
//     * */
//    private void updateTaskNode(ProcessNodeDO nextNode, String orderId){
//        // 删除当前实例的任务
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        if( orderTaskService.deleteByWhere(map) <=  0 ){
//            throw new RuntimeException("删除当前执行失败");
//        }
//        // 保存新的执行任务
//        OrderTaskDO orderTaskDo = getOrderTaskDo(nextNode, orderId);
//        if ( orderTaskService.save(orderTaskDo) <= 0 ){
//            throw new RuntimeException("执行任务保存失败");
//        }
//        // 存入历史流转表中
//        hiCirculationService.save(getHiCirculationDO(orderId, orderTaskDo.getNodeId(), orderTaskDo.getTaskName()));
//    }
//
//    /**
//     * 获取下一节点
//     * @param orderId 实例id
//     * @param node 当前节点
//     * @return 返回下一节点
//     * */
//    private ProcessNodeDO nextNode(ProcessNodeDO node, String orderId,String systemCode){
//        ProcessNodeDO nextNode = null;
//        if(ConstantEnum.EXIST_BRANCH.equals(node.getIsBranch())) {
//            //如果存在分支 调用条件节点的解析
//            return nextNode(completeConditionNode(node, orderId,systemCode),orderId,systemCode);
//        }else{
//            // 如果不存在分支 直接找下一个节点
//            nextNode = processNodeDao.getNextProcessNode(node.getId());
//        }
//        if(nextNode == null){
//            // 跳出当前分支 找出下一节点
//            return branchFinish(node);
//        }
//        return nextNode;
//    }
//
//    /**
//     * 下一节点为null时调用
//     * 跳出当前分支 找出下一节点
//     * @param node 当前节点
//     * @return 下一节点
//     * */
//    private ProcessNodeDO branchFinish(ProcessNodeDO node){
//        // 如果为空 可能是分支走完  或者是流程走完
//        List<ProcessNodeDO> processNodeDOList = getBranchNode(node, new ArrayList<>(),0);
//        if(processNodeDOList != null && processNodeDOList.size() > 0) {
//            // 跳出当前分支 返回下一节点
//            return getNextNodeByBranchNode(processNodeDOList);
//        }else {
//            // 流程已结束
//            return null;
//        }
//    }
//
//    /**
//     * 获取下一节点(仅供虚拟路径用)
//     * @param orderId 实例id
//     * @param node 当前节点
//     * @return 返回下一节点
//     * */
//    private ProcessNodeDO fictitiousNextNode(ProcessNodeDO node, String orderId,String systemCode){
//        ProcessNodeDO nextNode = null;
//        if(ConstantEnum.EXIST_BRANCH.equals(node.getIsBranch())) {
//            //如果存在分支 调用条件节点的解析
//            return completeConditionNode(node,orderId,systemCode);
//        }else {
//            nextNode = processNodeDao.getNextProcessNode(node.getId());
//        }
//        if(nextNode == null){
//            // 跳出当前分支 找出下一节点
//            return branchFinish(node);
//        }
//        return nextNode;
//    }
//
//    /**
//     * 根据节点和实例获取当前能审批的人
//     * @param node 审批节点
//     * @param orderId 流程实例id
//     * @return 审批人id列表
//     * */
//    private Set<String> getApproverList(ProcessNodeDO node, String orderId,String systemCode){
//        // 获取发起人
//        String createBy = orderService.get(orderId).getCreateBy();
//        if(ConstantEnum.AUDIT_OPTIONAL.equals(node.getAuditType())){
//            /**
//             * 如果是发起人自选
//             * */
//        }else {
//            // 不是自选 则找参与人表
//            // 获取当前节点能审批的所有人
//            Set<String> userIdList = getUserIdListByNode(node,createBy,systemCode);
//
//            Set<String> approvedUserId = taskActorService.userIdListByOrderId(orderId);
//            //去掉已审批的人
//            userIdList.removeAll(approvedUserId);
//            // 返回待审批的人
//            return userIdList;
//        }
//        return null;
//    }
//
//    /**
//     * 获取虚拟流程路径(只含审批节点)
//     * @param orderId 实例id
//     * @return 历史审批节点路径
//     * */
//    private List<ProcessNodeDO> getFictitiousNodePath(String orderId,String systemCode){
//        // 虚拟节点流程
//        List<ProcessNodeDO> fictitiousNodePath = new ArrayList<>();
//        ProcessNodeDO startNode = processNodeDao.getStartNode(orderId);
//        ProcessNodeDO node = startNode;
//        // 获取有序的审批节点
//        do {
//            fictitiousNodePath.add(node);
//            node = getFictitiousNode(node,orderId,systemCode);
//        }while (node != null);
//        return fictitiousNodePath;
//    }
//
//    /**
//     * 获取虚拟流程审批节点
//     * @param node 当前节点
//     * @param orderId 实例id
//     * */
//    private ProcessNodeDO getFictitiousNode(ProcessNodeDO node, String orderId,String systemCode){
//        // 获取下一节点
//        ProcessNodeDO nextNode = fictitiousNextNode(node, orderId,systemCode);
//        if(nextNode == null) {
//            return null;
//        }
//        // 只存审批节点才存入虚拟路径中
//        if(ConstantEnum.APPROVAL_NODE.equals(nextNode.getType())){
//            return nextNode;
//        }else {
//            return getFictitiousNode(nextNode,orderId,systemCode);
//        }
//    }
//
//    /**
//     * 根据分支节点查询下一节点(不走分支)
//     * @param processNodeDOList 分支线
//     * @return 下一节点
//     * */
//    private ProcessNodeDO getNextNodeByBranchNode(List<ProcessNodeDO> processNodeDOList){
//        // 分支节点
//        if(processNodeDOList == null){
//            // 流程结束
//            return null;
//        }
//        ProcessNodeDO branchNode = processNodeDOList.get(processNodeDOList.size() - 1);
//        ProcessNodeDO nextProcessNode = processNodeDao.getNextProcessNode(branchNode.getId());
//        if(processNodeDOList.size() == 1 && ConstantEnum.START_NODE.equals(branchNode.getType())){
//            // 结束
//            return null;
//        }
//        if(nextProcessNode == null){
//            // 继续找上一节点
//            return getNextNodeByBranchNode(getBranchNode(branchNode,new ArrayList<>(),1));
//        }else {
//            // 判断是否已经执行过了该节点
//            ProcessNodeDO prevNode = processNodeDOList.get(processNodeDOList.size() - 2);
//            if(prevNode.equals(nextProcessNode)){
//                if(branchNode.getPrevId() == null || "".equals(branchNode.getPrevId())){
//                    return null;
//                }
//                // 如何分支前一节点和下一节点一样 则继续递归获取下一节点
//                return getNextNodeByBranchNode(getBranchNode(processNodeDao.get(branchNode.getPrevId()),new ArrayList<>(),0));
//            }
//        }
//        // 返回下一节点
//        return nextProcessNode;
//    }
//
//    /**
//     * 获取递归获取上级分支节点
//     * @param processNodeDO 用于判断当前是否是分支节点
//     * @param processNodeDOList 分支中的节点列表
//     * @return 返回分支线节点 最后一个是分支节点
//     * */
//    private List<ProcessNodeDO> getBranchNode(ProcessNodeDO processNodeDO, List<ProcessNodeDO> processNodeDOList, int flag){
//        // 1 存在分支 0不存在分支
//        processNodeDOList.add(processNodeDO);
//        if( flag == 1 || ConstantEnum.NON_EXIST_BRANCH.equals(processNodeDO.getIsBranch())){
//            // 递归找出分支节点
//            if(ConstantEnum.START_NODE.equals(processNodeDO.getType())){
//                //流程结束
//                return null;
//            }
//            return getBranchNode(processNodeDao.get(processNodeDO.getPrevId()),processNodeDOList,0);
//        }else if( ConstantEnum.EXIST_BRANCH.equals(processNodeDO.getIsBranch()) && flag == 0){
//            //返回分支节点
//            return processNodeDOList;
//        }
//        return null;
//    }
//
////    /**
////     * 生成流转历史
////     * @param orderId 实例id
////     * @param nodeId 节点id
////     * */
////    private HiOperateDO getHiOperateDO(String nodeId,String orderId){
////        HiOperateDO hiOperateDO = new HiOperateDO();
////        hiOperateDO.setCaseId(orderId);
////        hiOperateDO.setSort(getHiOperateSortByOrderId(orderId));
////        hiOperateDO.setNodeId(nodeId);
////        return hiOperateDO;
////
////    }
//
//    /**
//     * 获取流转历史操作人
//     * @param userId 操作人
//     * @param hiId 流转历史id
//     * @param Type 操作类型
//     * @param params 审批意见+附件
//     * */
//    private HiCirculationDetailedDO getHiCirculationUser(String userId, String Type, String hiId, Map<String,Object> params){
//        HiCirculationDetailedDO hiCirculationDetailedDO = new HiCirculationDetailedDO();
//        hiCirculationDetailedDO.setType(Type);
//        hiCirculationDetailedDO.setUserId(userId);
//        hiCirculationDetailedDO.setCirculationId(hiId);
//        // 或签自动通过 不需要意见
//        if(ConstantEnum.OPERATE_TYPE_AUTO_COMMIT.equals(Type)) {
//            hiCirculationDetailedDO.setCommnet("[自动通过]");
//        }
//        if(params != null) {
//            if (params.get("comment") != null) {
//                hiCirculationDetailedDO.setCommnet(params.get("comment").toString());
//            }
//            if (params.get("filePath") != null) {
//                hiCirculationDetailedDO.setFileUrl(params.get("filePath").toString());
//            }
//        }
//        return hiCirculationDetailedDO;
//    }
//
//    /**
//     * 获取流转历史
//     * @param orderId 实例id
//     * @param nodeId 节点id
//     * @param taskName 任务名称
//     * */
//    private HiCirculationDO getHiCirculationDO(String orderId, String nodeId, String taskName){
//        HiCirculationDO hiCirculationDO = new HiCirculationDO();
//        hiCirculationDO.setNodeId(nodeId);
//        hiCirculationDO.setOrderId(orderId);
//        hiCirculationDO.setTaskName(taskName);
//        // 设置排序号
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        int count = hiCirculationService.count(map);
//        hiCirculationDO.setSort(count);
//        return hiCirculationDO;
//    }
//
//    /**
//     * 生成实例历史审批
//     * @param nodeId 节点id
//     * @param orderId 实例id
//     * @param flag 是否自动通过 1-自动通过 0-不是
//     * */
//    private HiTaskDO getHiTaskDO(String nodeId,String orderId,String flag){
//        HiTaskDO hiTaskDO = new HiTaskDO();
//        hiTaskDO.setOrderId(orderId);
//        hiTaskDO.setNodeId(nodeId);
//        hiTaskDO.setSort(getHiTaskSortByOrderId(orderId));
//        hiTaskDO.setFlag(flag);
//        return hiTaskDO;
//    }
//
//    /**
//     * 把当前审批人放入历史审批人记录中
//     * @param hiId 审批历史记录表id
//     * @param orderTaskId 执行审批记录表id
//     * */
//    private void saveHiActor(String orderTaskId,String hiId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("taskId",orderTaskId);
//        for(TaskActorDO taskActorDO : taskActorService.list(map)){
//            if( hiActorService.save(getHiActorDO(taskActorDO,hiId)) <= 0){
//                throw new RuntimeException("保存历史数据失败");
//            }
//        }
//        taskActorService.deleteByWhere(map);
//    }
//
//    /**
//     * 生成历史审批记录
//     * @param taskActorDO 节点对象
//     * @param hiId 历史记录id
//     * */
//    private HiActorDO getHiActorDO(TaskActorDO taskActorDO, String hiId){
//        HiActorDO hiActorDO = new HiActorDO();
//        hiActorDO.setTaskId(hiId);
//        hiActorDO.setVarable(taskActorDO.getVarable());
//        hiActorDO.setFlag(taskActorDO.getFlag());
//        // 审批人保持一致
//        hiActorDO.setCreateBy(taskActorDO.getCreateBy());
//        hiActorDO.setCreateDate(taskActorDO.getCreateDate());
//        return hiActorDO;
//    }
//
//    /**
//     * 生成当前执行任务
//     * @param processNodeDO 节点对象
//     * @param orderId 流程实例id
//     * */
//    private OrderTaskDO getOrderTaskDo(ProcessNodeDO processNodeDO, String orderId){
//        OrderTaskDO taskDO = new OrderTaskDO();
//        taskDO.setOrderId(orderId);
//        taskDO.setTaskName(processNodeDO.getName());
//        taskDO.setNodeId(processNodeDO.getId());
//        return taskDO;
//    }
//
//    /**
//     * 生成审批人记录
//     * @param userId 审批参数
//     * @param taskId 当前任务id
//     * @param flag 是否自动通过 0-不是 1-是
//     * */
//    private TaskActorDO getTaskActorDo(String userId,String taskId,String flag){
//        TaskActorDO taskActorDO = new TaskActorDO();
//        taskActorDO.setTaskId(taskId);
//        taskActorDO.setVarable("1");
//        taskActorDO.setCreateBy(userId);
//        taskActorDO.setFlag(flag);
//        return taskActorDO;
//    }
//
//    /**
//     * 完成审核节点
//     * @param taskDO 正在实行的审批节点
//     * @param param 审批参数
//     * @return 返回当前节点 节点未完成
//     * 返回null 完成当前审批节点
//     * 返回其他节点 即驳回了
//     * */
//    private ProcessNodeDO completeApproveNode(OrderTaskDO taskDO, Map<String,Object> param,String systemCode,String userId){
//        // 找到正在执行的节点
//        ProcessNodeDO processNodeDO = processNodeDao.get(taskDO.getNodeId());
//        // 保存至审批记录中
//        taskActorService.save(getTaskActorDo(userId, taskDO.getId(),"0"));
//
//        String hiId = hiCirculationService.getNowHiCirculationByOrder(taskDO.getOrderId()).getId();
//        HiCirculationDetailedDO hiCirculationUser = getHiCirculationUser(userId,"",hiId , param);
//
//        Boolean flag = true;  // true 为通过 false 为驳回
//        if("0".equals(param.get("flag"))) {
//            flag = false;
//        }
//        // 保存至流转历史中
//        /* 因流转历史改造 弃用
//        HiOperateDO hiOperateDO = getHiOperateDO(taskDO.getNodeId(),taskDO.getOrderId());
//        hiOperateDO.setUserId(UserUtils.getUser().getId());
//        Object comment = param.get("comment");
//        // 添加审批意见
//        if(comment != null) {
//            hiOperateDO.setApprovalAdvice(param.get("comment").toString());
//        }
//        // 添加审批附件
//        Object filePath = param.get("filePath");
//        if( filePath != null) {
//            hiOperateDO.setHisFilePath(param.get("filePath").toString());
//        }
//        // 设置审批是否通过
//        if(flag){   //通过为4
//            hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_PASS);
//        }else {  // 驳回为5
//            hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_REJECT);
//        }
//        hiOperateService.save(hiOperateDO);
//        */
//        if(flag){   //通过为4
//            hiCirculationUser.setType(ConstantEnum.OPERATE_TYPE_PASS);
//        }else {  // 驳回为5
//            hiCirculationUser.setType(ConstantEnum.OPERATE_TYPE_REJECT);
//        }
//        hiCirculationDetailedService.save(hiCirculationUser);
//
//        if(false == flag){ // 驳回
//            // 调用驳回处理
//            return rejectHandler(processNodeDO,taskDO.getOrderId());
//        }
//        // 会签 所有审批人都要审批
//        if(ConstantEnum.AND_SIGN_METHOD.equals(processNodeDO.getCounterSign())){
//            // 调用会签处理
//            ProcessNodeDO nodeDO = countersignHandler(processNodeDO, taskDO,systemCode);
//            // 不为null 则会签未完成
//            return nodeDO;
//        }
//        return null;
//    }
//
//    /**
//     * 会签处理
//     * 节点是会签时调用
//     * @param processNodeDO 当前节点
//     * @param taskDO 当前实例
//     * */
//    private ProcessNodeDO countersignHandler(ProcessNodeDO processNodeDO, OrderTaskDO taskDO,String systemCode){
//        Map<String, Object> map = new HashMap<>();
//        map.put("taskId", taskDO.getId());
//        Map<String,Object> nodeMap = new HashMap<>();
//        nodeMap.put("nodeId",taskDO.getNodeId());
//        boolean flag = false;
//        if(ConstantEnum.AUDIT_OPTIONAL.equals(processNodeDO.getAuditType())) {
//            /**
//             * 发起人自选
//             * */
//
//        }else {
//            String createBy = orderService.get(taskDO.getOrderId()).getCreateBy();
//            // 获取所有要参与的人
//            Set<String> userIdList= getUserIdListByNode(processNodeDO, createBy,systemCode);
//            // 遍历每个人
//            Map<String,Object> taskMap = new HashMap<>();
//            taskMap.put("taskId",taskDO.getId());
//            taskMap.put("ids",userIdList);
//            if(taskActorService.count(taskMap) != userIdList.size()){
//                flag = true;
//            }
//            // 有角色没审批就返回当前节点 继续审批
//            if(true == flag){
//                return processNodeDO;
//            }
//        }
//        return null;
//    }
//
//
//
//    /**
//     * 根据驳回类型返回节点处理
//     * 驳回时调用
//     * @param processNodeDO 当前节点
//     * @param orderId 当前实例id
//     * */
//    private ProcessNodeDO rejectHandler(ProcessNodeDO processNodeDO, String orderId){
//        // 根据驳回方式的不同来返回到不同的节点上去
//        // 驳回类型 驳回至1 父节点、2发起人节点
//
//        //判断驳回类型
//        if("1".equals(processNodeDO.getRejectType())){
//
//            /**
//             * 可以根据审批记录来驳回
//             * */
//            // 获取驳回的上一审批节点
//            HiTaskDO prevHiTask = hiTaskService.getPrevHiTask(orderId);
//            ProcessNodeDO nextNode = processNodeDao.get(prevHiTask.getNodeId());
//
//            return nextNode;
//        }else {
//            // 默认驳回到开始节点
//            // 返回至发起人节点
//            ProcessNodeDO startNode = processNodeDao.getStartNode(orderId);
//            return startNode;
//        }
//    }
//
////    /**
////     * 获取当前流转历史排序号
////     * @param orderId 流程实例id
////     * */
////    private int getHiOperateSortByOrderId(String orderId){
////        Map<String,Object> map = new HashMap<>();
////        map.put("caseId",orderId);
////        return hiOperateService.count(map)+1;
////    }
//
//    /**
//     * 获取当前审批流程排序号
//     * @param orderId 流程实例id
//     * */
//    private int getHiTaskSortByOrderId(String orderId){
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        return hiTaskService.count(map);
//    }
//
////    /**
////     * 判断是否是当前用户是否可以审批
////     * @param auditorType 审批人类型  1用户 2部门领导 3角色 4ALL 5部门 6发起人自己'
////     * @param value 审批值（根据审批人类型区分）
////     * @param createBy 发起人id
////     * */
////    private Boolean currentAuditor(String auditorType,String value,String createBy){
////        switch (auditorType) {
////            case ConstantEnum.ACTOR_CLIENT:
////                // 指定的人
////                if(UserUtils.getUser().getId().equals(value)) {
////                    return true;
////                }
////                break;
////            case ConstantEnum.ACTOR_ROLE:
////                // 3角色
////                Map<String,Object> map = new HashMap<>();
////                map.put("userId", UserUtils.getUser().getId());
////                map.put("roleId",value);
////                if(userRoleDao.count(map) > 0){
////                    return true;
////                }
////                break;
////            case ConstantEnum.ACTOR_ALL_CLIENT:
////                // 所有人
////                return true;
////            case ConstantEnum.ACTOR_DEPT_CLIENT:
////                // 5部门
////                if(value.equals(UserUtils.getUser().getOfficeId())){
////                    return true;
////                }
////                break;
////            case ConstantEnum.ACTOR_MYSELF:
////                // 6发起人自己
////                if(createBy.equals(UserUtils.getUser().getId())){
////                    return true;
////                }
////                break;
////        }
////        return false;
////    }
//
//    /**
//     * 变量条件处理
//     * @param orderId 实例id
//     * @param nodeDO 条件节点
//     * */
//    private boolean variableHandler(Object formData, ProcessNodeDO nodeDO, String orderId){
//        Map<String, Object> map = new HashMap<>();
//        map.put("nodeId", nodeDO.getId());
//        // 获取条件节点的所有条件
//        List<NodeVariableDO> nodeVariableDOList = nodeVariableService.list(map);
//        if (nodeVariableDOList != null && nodeVariableDOList.size() > 0) {
//            for (NodeVariableDO item : nodeVariableDOList) {
//                // 判断是否符合条件
//                // 获取值
//                Object propertyValue = getPropertyValue(formData, item.getFormId());
//                // 比较
//                if (!computeCondition(item.getCmpType(), propertyValue, item.getConditionvalue())) {
//                    //只要有一条不符合条件 则直接进入下一条条件节点
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 人员条件处理
//     * @param orderId 实例id
//     * @param nodeDO 条件节点
//     * */
//    private boolean actorHandler(ProcessNodeDO nodeDO, String orderId,String systemCode){
//        Map<String, Object> map = new HashMap<>();
//        map.put("nodeId", nodeDO.getId());
//        //获取发起人id
//        String createBy = orderService.get(orderId).getCreateBy();
//        // 获取条件节点的所有参与人
//        List<ProcessActorDO> processActorDOList = processActorService.list(map);
//        if (processActorDOList != null && processActorDOList.size() > 0) {
//            for (ProcessActorDO item : processActorDOList) {
//                // 判断是否符合条件
//                /**
//                 * 条件参与人类型 1用户  5部门
//                 * */
//                switch (item.getType()) {
//                    case ConstantEnum.ACTOR_CLIENT :
//                        // 判断是否是指定用户
//                        if(item.getValue().equals(createBy)){
//                            return true;
//                        }
//                        break;
//                    case ConstantEnum.ACTOR_DEPT_CLIENT :
//                        // 判断是否是该部门下的人
//                        if(userService.findAllUserByOfficeId(item.getValue(),systemCode).contains(createBy)){
//                            return true;
//                        }
//                        break;
//                }
//            }
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    /**
//     * 完成条件节点（找出符合的条件节点）
//     * @param nodeDO 拥有分支的节点
//     * @param orderId 流程实例id
//     * @return 返回条件节点
//     * */
//    private ProcessNodeDO completeConditionNode(ProcessNodeDO nodeDO, String orderId,String systemCode){
//        List<ProcessNodeDO> conditionNodeList = processNodeDao.conditionNodeList(nodeDO.getId());
//        for(ProcessNodeDO processNodeDO : conditionNodeList) {
//            boolean flag = true; // flag =true 为条件符合 false为不符合
////            flag = variableHandler(reimbursementService.getByProcInsId(orderId),processNodeDO,orderId);
////            if(flag == false){
////                continue;
////            }
//            flag = actorHandler(processNodeDO,orderId,systemCode);
//            if(flag == false){
//                // 如果参与人不符合 则进行下个条件节点比较
//                continue;
//            }
//            //返回条件节点
//            return processNodeDO;
//        }
//        throw new RuntimeException("所有条件都不符合，流程无法进行");
//    }
//
//    /**
//     * 完成抄送节点
//     * @param processNodeDO 抄送节点
//     * @param orderId 实例id
//     * @return 返回抄送节点
//     * */
//    private ProcessNodeDO completeCopyNode(ProcessNodeDO processNodeDO, String orderId,String systemCode){
//
//
//        // 存入流转历史中
//        /* 因流转历史改造 弃用
//        HiOperateDO hiOperateDO = getHiOperateDO(processNodeDO.getId(),orderId);
//        hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_COPY);
//        hiOperateService.save(hiOperateDO);
//        */
//        HiCirculationDO hiCirculationDO = getHiCirculationDO(orderId, processNodeDO.getId(), processNodeDO.getName());
//        hiCirculationService.save(hiCirculationDO);
//
//        if(ConstantEnum.ACTOR_MYSELF.equals(processNodeDO.getAuditType())){
//            // 如果是发起人自选
//        }
//        Set<String> userIdList = getUserIdListByNode(processNodeDO, orderService.get(orderId).getCreateBy(),systemCode);
//        for(String userId : userIdList){
//            // 保存进历史明细表中
//                /* 因流转历史改造 弃用
//                HiOperateDetailDO hiOperateDetailDO = new HiOperateDetailDO();
//                hiOperateDetailDO.setOperateId(hiOperateDO.getId());
//                hiOperateDetailDO.setUserId(userId);
//                hiOperateDetailService.save(hiOperateDetailDO);
//                */
//            HiCirculationDetailedDO hiCirculationUser = getHiCirculationUser(userId, ConstantEnum.OPERATE_TYPE_COPY, hiCirculationDO.getId(), null);
//            hiCirculationDetailedService.save(hiCirculationUser);
//            //sendUser(userId,orderId,ProcessNotifyType.COPY);
//        }
//        // 返回抄送节点
//        return processNodeDO;
//    }
//
////    /**
////     * 根据节点发送通知
////     * @param orderId 实例流程id
////     * @param node 节点
////     * @param processNotifyType 通知类型
////     * */
////    private void sendNotifyByAuditNode(ProcessNodeDO node, String orderId, String processNotifyType){
////        // 获取所有需要抄送的人
////        Map<String, Object> map = new HashMap<>();
////        map.put("nodeId", node.getId());
////        // 要审批的用户
////        Set<String> approverList = getApproverList(node, orderId);
////        // 发送通知
////        for(String userId : approverList){
////            sendUser(userId,orderId,processNotifyType);
////        }
////    }
////
////
////
////    /**
////     * 发送通知给相应的人
////     * @param userId 用户id
////     * @param orderId 实例id
////     * @param processNotifyType 通知类型 0开始 1抄送 2审批 3驳回 4通过
////     * */
////    private void sendUser(String userId,String orderId,String processNotifyType){
////        // 获取发起人的姓名
////        String username = userDao.get(orderService.get(orderId).getCreateBy()).getName();
////        // 设置通知信息
////        NotifyDO notifyDO = new NotifyDO();
////        notifyDO.setType("4"); // 其他通告
////        notifyDO.setStatus(NotifyDO.STATUS_PUBLISH);
////        // 根据不同得通知类型 改变通知内容
////        LocalDateTime localDateTime = LocalDateTime.now();
////        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////        switch (processNotifyType){
////            case ProcessNotifyType.START :
////                notifyDO.setTitle("驳回提醒");
////                notifyDO.setContent(username + ",你报销单于" + localDateTime.format(dateTimeFormatter) + "被驳回，请重新编辑！");
////                break;
////            case ProcessNotifyType.COPY :
////                // 设置抄送内容
////                notifyDO.setTitle("抄送提醒");
////                notifyDO.setContent(username + "的报销单于" + localDateTime.format(dateTimeFormatter) + "以抄送给您，请注意查收！");
////                break;
////            case ProcessNotifyType.PASS :
////                notifyDO.setTitle("审批提醒");
////                notifyDO.setContent(username + "的报销单于" + localDateTime.format(dateTimeFormatter) + "以抄送给您，请审批！");
////                break;
////            case ProcessNotifyType.REJECT :
////                notifyDO.setTitle("驳回提醒");
////                notifyDO.setContent(username + "的报销单于" + localDateTime.format(dateTimeFormatter) + "被"+ UserUtils.getUser().getName()+"驳回，请重新审批！");
////                break;
////            case ProcessNotifyType.FINISH :
////                notifyDO.setTitle("通过提醒");
////                notifyDO.setContent(username + "，你报销单于" + localDateTime.format(dateTimeFormatter) + "已通过！");
////                break;
////        }
////        //设置通知人
////        //notifyDO.setUserIds(userId);
////        notifyDO.setOaNotifyRecordIds(new String[]{userId});
////        // 发送通知
////        notifyService.save(notifyDO);
////    }
//
//    /**
//     * 通过反射获取值
//     * @param object 对象
//     * @param fieldName 需要获取的值
//     * */
//    private Object getPropertyValue(Object object,String fieldName) {
//        Class c = object.getClass();
//        Field[] declaredFields = c.getDeclaredFields();
//        for(Field field : declaredFields){
//            if(field.getName().equals(fieldName)){
//                try {
//                    field.setAccessible(true);
//                    return field.get(object);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 计算条件
//     * @param value 条件的值
//     * @param param 表中的参数值
//     * @param Symbol 计算符号
//     * @return 是否符合条件
//     * */
//    private Boolean computeCondition(String Symbol,Object param,Object value){
//        // Symbol   1: <", 2: "≤", 3:"=",4:">",5:"≥",6:"包含" ,7:"不包含"
//        // type:   1:int;2:string;3:float
//        switch (Symbol){
//            case ConstantEnum.COMPARE_LT:  // 小于
//                if( param instanceof Integer && value instanceof Integer ){
//                    return ((Integer) param) < ((Integer)value);
//                }else if( param instanceof BigDecimal){
//                    if(((BigDecimal) param).compareTo(new BigDecimal(value.toString())) == -1){
//                        return true;
//                    }
//                }
//                break;
//            case ConstantEnum.COMPARE_LT_EQ : //小于等于
//                if( param instanceof Double && value instanceof Double ){
//                    return ((Integer) param) <= ((Integer)value);
//                }else if( param instanceof BigDecimal){
//                    if(((BigDecimal) param).compareTo(new BigDecimal(value.toString())) != 1){
//                        return true;
//                    }
//                }
//                break;
//            case ConstantEnum.COMPARE_EQ :  // 等于
//                if( param instanceof Integer && value instanceof Integer ){
//                    return ((Integer) param).equals((Integer)value);
//                }else if( param instanceof BigDecimal){
//                    if(((BigDecimal) param).compareTo(new BigDecimal(value.toString())) == 0){
//                        return true;
//                    }
//                }else if ( param instanceof String && value instanceof String ){
//                    return ((String) param).equals((String)value);
//                }
//                break;
//            case ConstantEnum.COMPARE_NEW_GT:// 大于
//            case ConstantEnum.COMPARE_GT : // 大于
//                if( param instanceof Integer && value instanceof Integer ){
//                    return ((Integer) param) > ((Integer)value);
//                }else if( param instanceof BigDecimal){
//                    if(((BigDecimal) param).compareTo(new BigDecimal(value.toString())) == 1){
//                        return true;
//                    }
//                }
//                break;
//            case ConstantEnum.COMPARE_NEW_GT_EQ:// 大于等于
//            case ConstantEnum.COMPARE_GT_EQ :  // 大于等于
//                if( param instanceof Integer && value instanceof Integer ){
//                    return ((Integer) param) >= ((Integer)value);
//                }else if( param instanceof BigDecimal){
//                    if(((BigDecimal) param).compareTo(new BigDecimal(value.toString())) != -1){
//                        return true;
//                    }
//                }
//                break;
//            case ConstantEnum.COMPARE_INCLUDE : //包含于
//                if( param instanceof String && value instanceof String ){
//                    return ((String) param).contains((String)value);
//                }
//                break;
//            case ConstantEnum.COMPARE_DIS_INCLUDE : //不包含于
//                if( param instanceof String && value instanceof String ){
//                    return !((String) param).contains((String)value);
//                }
//                break;
//        }
//        return false;
//    }
//
//}
