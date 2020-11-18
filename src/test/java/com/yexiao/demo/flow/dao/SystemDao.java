//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.SystemDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方系统表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:37
// */
//@Mapper
//public interface SystemDao extends CrudDao<SystemDO> {
//
//	SystemDO get(String id);
//
//	Page<SystemDO> list(Page<SystemDO> page, @Param("entity") Map<String, Object> map);
//
//	List<SystemDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(SystemDO system);
//
//	int update(SystemDO system);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//}
