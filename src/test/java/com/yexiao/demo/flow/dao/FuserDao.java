//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.FuserDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方用户表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:33:54
// */
//@Mapper
//public interface FuserDao extends CrudDao<FuserDO> {
//
//	FuserDO get(String id, String systemCode);
//
//	Page<FuserDO> list(Page<FuserDO> page, @Param("entity") Map<String, Object> map);
//
//	List<FuserDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(FuserDO fuser);
//
//	int update(FuserDO fuser);
//
//	int remove(String id, String systemCode);
//
//	int batchRemove(@Param("array") String[] ids, String systemCode);
//
//    int deleteUserRole(FuserDO fuser);
//
//	int insertUserRole(FuserDO fuser);
//
//    List<FuserDO> findUserByOfficeId(FuserDO user);
//
//    List<String> findUserIdSetByRoleId(@Param("roleId") String roleId, @Param("systemCode") String systemCode);
//
//    List<String> findAllUserByOfficeId(@Param("officeId") String officeId, @Param("systemCode") String systemCode);
//}
