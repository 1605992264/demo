//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiTaskDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例历史记录表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:46
// */
//@Mapper
//public interface HiTaskDao extends CrudDao<HiTaskDO> {
//
//	HiTaskDO get(String id);
//
//	Page<HiTaskDO> list(Page<HiTaskDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiTaskDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiTaskDO hiTask);
//
//	int update(HiTaskDO hiTask);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiTaskDO> hiTasks);
//
//	int batchUpdate(List<HiTaskDO> hiTasks);
//
//	int updateByWhere(@Param("param") HiTaskDO hiTask, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int count(@Param("where") Map<String, Object> whereMap);
//
//	/**
//     * 查询已审批了的人(忽略自动通过的人)
//     * @param whereMap 条件
//     * */
//	List<String> getUserIdByWhere(@Param("where") Map<String, Object> whereMap);
//
//	/**
//     * 获取上一节点(非自动通过的)
//     * */
//	HiTaskDO getPrevHiTask(String orderId);
//}
