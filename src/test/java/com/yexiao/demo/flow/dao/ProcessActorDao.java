//package com.yexiao.demo.flow.dao;
//
//import com.alibaba.fastjson.JSONArray;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.ProcessActorDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程参与人表
// * @author xiewh
// * @email xiewh@3xdata.cn
// * @date 2020-06-29 17:03:16
// */
//@Mapper
//public interface ProcessActorDao extends CrudDao<ProcessActorDO> {
//
//	ProcessActorDO get(String id);
//
//	Page<ProcessActorDO> list(Page<ProcessActorDO> page, @Param("entity") Map<String, Object> map);
//
//	List<ProcessActorDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(ProcessActorDO processActor);
//
//	int update(ProcessActorDO processActor);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<ProcessActorDO> processActors);
//
//	int batchUpdate(List<ProcessActorDO> processActors);
//
//	int updateByWhere(@Param("param") ProcessActorDO processActor, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//    int count(@Param("where") Map<String, Object> whereMap);
//
//	/**
//	 * 查询起始节点的条件
//	 * @param processInsId
//	 * @return
//	 */
//	JSONArray queryStartCondition(@Param("processInsId") String processInsId);
//}
