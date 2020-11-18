//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.ProcessNodeDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程节点表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:26
// */
//@Mapper
//public interface ProcessNodeDao extends CrudDao<ProcessNodeDO> {
//
//	ProcessNodeDO get(String id);
//
//	Page<ProcessNodeDO> list(Page<ProcessNodeDO> page, @Param("entity") Map<String, Object> map);
//
//	List<ProcessNodeDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(ProcessNodeDO processNode);
//
//	int update(ProcessNodeDO processNode);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<ProcessNodeDO> processNodes);
//
//	int batchUpdate(List<ProcessNodeDO> processNodes);
//
//	int updateByWhere(@Param("param") ProcessNodeDO processNode, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//
//	/**
//     * 获取下一节点（除条件节点）
//     * @param nodeId 当前节点id
//     * */
//    ProcessNodeDO getNextProcessNode(String nodeId);
//
//    /**
//     * 获取条件节点
//     * @param nodeId 当前节点id
//     * */
//    List<ProcessNodeDO> conditionNodeList(String nodeId);
//
//    /**
//     * 获取发起人节点
//     * @param orderId 流程实例id
//     * */
//    ProcessNodeDO getStartNode(String orderId);
//
//    /**
//     * 获取发起人节点
//     * @param processId 流程id
//     * */
//    ProcessNodeDO getStartNodeByProcessId(String processId);
//
//    /**
//     * 获取所有流程的当前任务
//     * */
//    List<ProcessNodeDO> findLeaderTaskNode();
//
//    /**
//     * 获取未审批的节点和formData
//     * @param orderIds 实例id 以 ， 分割
//     * */
//    Page<ProcessNodeDO> findNotApprovalList(Page<ProcessNodeDO> page, @Param("orderIds") String orderIds);
//
//    /**
//     * 获取已审批的节点和formData
//     * @param userId 用户id
//     * */
//    Page<ProcessNodeDO> findApprovedNodeByUserId(Page<ProcessNodeDO> page, @Param("userId") String userId);
//
//    /**
//     * 获取抄送给我的节点和formData
//     * @param userId 用户id
//     * */
//    Page<ProcessNodeDO> findCopyByUserId(Page<ProcessNodeDO> page, @Param("userId") String userId);
//
//
//
//
//}
