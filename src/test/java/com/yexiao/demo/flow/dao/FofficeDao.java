//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.FofficeDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方机构表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:16
// */
//@Mapper
//public interface FofficeDao extends CrudDao<FofficeDO> {
//
//	FofficeDO get(String id, String systemCode);
//
//	Page<FofficeDO> list(Page<FofficeDO> page, @Param("entity") Map<String, Object> map);
//
//	List<FofficeDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(FofficeDO foffice);
//
//	int update(FofficeDO foffice);
//
//	int remove(String id, String systemCode);
//
//	int batchRemove(@Param("array") String[] ids, String systemCode);
//
//    List<FofficeDO> getListByOfficeId(FofficeDO office);
//
//	List<FofficeDO> findByparentId(FofficeDO fofficeDO);
//
//	@Override
//	List<FofficeDO> findAllList(FofficeDO entity);
//}
