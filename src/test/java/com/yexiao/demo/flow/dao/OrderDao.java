//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.OrderDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:58
// */
//@Mapper
//public interface OrderDao extends CrudDao<OrderDO> {
//
//	OrderDO get(String id);
//
//	Page<OrderDO> list(Page<OrderDO> page, @Param("entity") Map<String, Object> map);
//
//	List<OrderDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(OrderDO order);
//
//	int update(OrderDO order);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<OrderDO> orders);
//
//	int batchUpdate(List<OrderDO> orders);
//
//	int updateByWhere(@Param("param") OrderDO order, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//}
