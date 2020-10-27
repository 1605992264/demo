//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.TaskActorDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 流程审批记录表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:33
// */
//@Mapper
//public interface TaskActorDao extends CrudDao<TaskActorDO> {
//
//	TaskActorDO get(String id);
//
//	Page<TaskActorDO> list(Page<TaskActorDO> page, @Param("entity") Map<String, Object> map);
//
//	List<TaskActorDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(TaskActorDO taskActor);
//
//	int update(TaskActorDO taskActor);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<TaskActorDO> taskActors);
//
//	int batchUpdate(List<TaskActorDO> taskActors);
//
//	int updateByWhere(@Param("param") TaskActorDO taskActor, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//	/**
//     * 根据实例id获取当前任务的已审批人id
//     * */
//	Set<String> userIdListByOrderId(String orderId);
//
//	int count(@Param("where") Map<String, Object> whereMap);
//}
