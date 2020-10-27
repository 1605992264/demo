//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiCirculationDO;
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
// * @date 2020-08-04 09:54:44
// */
//@Mapper
//public interface HiCirculationDao extends CrudDao<HiCirculationDO> {
//
//	HiCirculationDO get(String id);
//
//	Page<HiCirculationDO> list(Page<HiCirculationDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiCirculationDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiCirculationDO hiCirculation);
//
//	int update(HiCirculationDO hiCirculation);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiCirculationDO> hiCirculations);
//
//	int batchUpdate(List<HiCirculationDO> hiCirculations);
//
//	int updateByWhere(@Param("param") HiCirculationDO hiCirculation, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int count(@Param("where") Map<String, Object> whereMap);
//
//	HiCirculationDO getNowHiCirculationByOrder(@Param("orderId") String orderId);
//}
