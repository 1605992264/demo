//package com.yexiao.demo.flow.dao;
//
//
//import com.txdata.flow.domain.BaseFlowDataDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//
///**
// * @author xu hf
// * formdata数据
// * 方便修改
// * */
//@Mapper
//public interface ControlFlowDao {
//
//    /**
//     * 已审批列表
//     * */
//    String approvalList(@Param("userId") String userId, @Param("systemCode") String systemCode, @Param("processCode") String processCode);
//
//    /**
//     * 待审批列表
//     * */
//    String notApprovalList(@Param("userId") String userId, @Param("systemCode") String systemCode, @Param("processCode") String processCode);
//
//    /**
//     * 封装基本数据
//     * */
//    List<BaseFlowDataDO> getBaseFlowData(@Param("orderIds") String orderIds, @Param("systemCode") String systemCode);
//
//    /**
//     * 获取节点参与人
//     * */
//    BaseFlowDataDO getActorByNodeId(@Param("nodeId") String nodeId, @Param("systemCode") String systemCode);
//
//}
