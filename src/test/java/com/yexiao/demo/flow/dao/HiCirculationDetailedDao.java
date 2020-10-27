//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiCirculationDetailedDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程流转历史操作人表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:57:03
// */
//@Mapper
//public interface HiCirculationDetailedDao extends CrudDao<HiCirculationDetailedDO> {
//
//	HiCirculationDetailedDO get(String id);
//
//	Page<HiCirculationDetailedDO> list(Page<HiCirculationDetailedDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiCirculationDetailedDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiCirculationDetailedDO hiCirculationDetailed);
//
//	int update(HiCirculationDetailedDO hiCirculationDetailed);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiCirculationDetailedDO> hiCirculationDetaileds);
//
//	int batchUpdate(List<HiCirculationDetailedDO> hiCirculationDetaileds);
//
//	int updateByWhere(@Param("param") HiCirculationDetailedDO hiCirculationDetailed, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
