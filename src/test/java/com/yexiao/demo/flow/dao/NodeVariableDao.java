//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.NodeVariableDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程节点条件表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:21:04
// */
//@Mapper
//public interface NodeVariableDao extends CrudDao<NodeVariableDO> {
//
//	NodeVariableDO get(String id);
//
//	Page<NodeVariableDO> list(Page<NodeVariableDO> page, @Param("entity") Map<String, Object> map);
//
//	List<NodeVariableDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(NodeVariableDO nodeVariable);
//
//	int update(NodeVariableDO nodeVariable);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<NodeVariableDO> nodeVariables);
//
//	int batchUpdate(List<NodeVariableDO> nodeVariables);
//
//	int updateByWhere(@Param("param") NodeVariableDO nodeVariable, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
