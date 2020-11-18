//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.FroleDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方角色表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:21
// */
//@Mapper
//public interface FroleDao extends CrudDao<FroleDO> {
//
//	FroleDO get(String id, String systemCode);
//
//	Page<FroleDO> list(Page<FroleDO> page, @Param("entity") Map<String, Object> map);
//
//	List<FroleDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(FroleDO frole);
//
//	int update(FroleDO frole);
//
//	int remove(String id, String systemCode);
//
//	int batchRemove(@Param("array") String[] ids, String systemCode);
//
//    List<FroleDO> selectRoleById(@Param("list") List<String> roleIdList, String systemCode);
//}
