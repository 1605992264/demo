//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiActorDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程历史审批记录表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:01
// */
//@Mapper
//public interface HiActorDao extends CrudDao<HiActorDO> {
//
//	HiActorDO get(String id);
//
//	Page<HiActorDO> list(Page<HiActorDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiActorDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiActorDO hiActor);
//
//	int update(HiActorDO hiActor);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiActorDO> hiActors);
//
//	int batchUpdate(List<HiActorDO> hiActors);
//
//	int updateByWhere(@Param("param") HiActorDO hiActor, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
