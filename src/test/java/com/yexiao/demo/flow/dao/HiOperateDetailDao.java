//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiOperateDetailDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程历史操作明细表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:10:28
// */
//@Mapper
//public interface HiOperateDetailDao extends CrudDao<HiOperateDetailDO> {
//
//	HiOperateDetailDO get(String id);
//
//	Page<HiOperateDetailDO> list(Page<HiOperateDetailDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiOperateDetailDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiOperateDetailDO hiOperateDetail);
//
//	int update(HiOperateDetailDO hiOperateDetail);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiOperateDetailDO> hiOperateDetails);
//
//	int batchUpdate(List<HiOperateDetailDO> hiOperateDetails);
//
//	int updateByWhere(@Param("param") HiOperateDetailDO hiOperateDetail, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
