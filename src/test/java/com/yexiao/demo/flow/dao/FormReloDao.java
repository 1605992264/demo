//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.FormReloDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程节点表单权限表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:07:26
// */
//@Mapper
//public interface FormReloDao extends CrudDao<FormReloDO> {
//
//	FormReloDO get(String id);
//
//	Page<FormReloDO> list(Page<FormReloDO> page, @Param("entity") Map<String, Object> map);
//
//	List<FormReloDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(FormReloDO formRelo);
//
//	int update(FormReloDO formRelo);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<FormReloDO> formRelos);
//
//	int batchUpdate(List<FormReloDO> formRelos);
//
//	int updateByWhere(@Param("param") FormReloDO formRelo, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
