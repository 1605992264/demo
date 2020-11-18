//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.OrderTaskDO;
//import com.txdata.flow.domain.ProcessNodeDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例执行表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:07
// */
//@Mapper
//public interface OrderTaskDao extends CrudDao<OrderTaskDO> {
//
//	OrderTaskDO get(String id);
//
//	Page<OrderTaskDO> list(Page<OrderTaskDO> page, @Param("entity") Map<String, Object> map);
//
//	List<OrderTaskDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(OrderTaskDO orderTask);
//
//	int update(OrderTaskDO orderTask);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<OrderTaskDO> orderTasks);
//
//	int batchUpdate(List<OrderTaskDO> orderTasks);
//
//	int updateByWhere(@Param("param") OrderTaskDO orderTask, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//    /**
//     * 1.用户 3.角色 4.All 5.部门 6.发起人自己
//     *  获取待审批的流程
//     * @param userId 用户id
//     * */
//	String getOrderIdByUser(String userId);
//
//    /**
//     * 部门领导（无法递归取上级）
//     *  获取待审批的流程
//     * @param userId 用户id和一个空
//     * */
//	List<ProcessNodeDO> findOrderLeaderByUserId(@Param("userList") List<String> userId);
//
//	OrderTaskDO getOrderTaskByOrderId(String orderId);
//
//}
