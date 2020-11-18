//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.FlowProcessDao;
//import com.txdata.flow.dao.OrderDao;
//import com.txdata.flow.domain.OrderDO;
//import com.txdata.flow.utils.ConstantEnum;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:58
// */
// @Service
//public class OrderService extends CrudService<OrderDao, OrderDO> {
//
//    @Autowired
//    private OrderDao orderDao;
//    @Autowired
//	private FlowProcessDao processDao;
//
//    /**
//	 * 通过id查找数据
//	 */
//    public OrderDO get(String id){
//        return orderDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<OrderDO> page(Page<OrderDO> page, Map<String, Object> map){
//        return orderDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<OrderDO> list(Map<String, Object> map){
//        return orderDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(OrderDO order){
//        return super.save(order);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return orderDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return orderDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int delete(String id){
//        return orderDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return orderDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<OrderDO> orders){
//    	return orderDao.batchInsert(orders);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<OrderDO> orders){
//		return orderDao.batchUpdate(orders);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	OrderDO order = orderDao.get(id);
//    	if (order != null){
//    		order.setId(null);
//    		order.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(order.getName())){
////    			order.setName(order.getName() + "-复制");
////    		}
//    		result = orderDao.insert(order);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param order 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(OrderDO order, Map<String,Object> whereMap){
//    	return orderDao.updateByWhere(order, whereMap);
//    }
//
//    /**
//	 *
//	 * @Description: 逻辑删除（通过自定义的条件进行逻辑删除操作）
//	 * @param whereMap 逻辑删除条件
//	 * @return: 返回逻辑删除数量
//	 */
//	@Transactional(readOnly=false)
//    public int removeByWhere(Map<String,Object> whereMap){
//    	return orderDao.removeByWhere(whereMap);
//    }
//
//	/**
//	 *
//	 * @Description: 物理删除（通过自定义的条件进行物理删除操作）慎用
//	 * @param whereMap 物理删除条件
//	 * @return: 返回物理删除数量
//	 */
//	@Transactional(readOnly=false)
//	public int deleteByWhere(Map<String,Object> whereMap){
//		return orderDao.deleteByWhere(whereMap);
//	}
//
//	/**
//	 * 启动流程
//	 * @param processCode
//	 */
//	public Map<String,Object> startProcess(String processCode){
//		Map<String,Object> map = new HashMap<>();
//		String processId = null;
//		if (StringUtils.isNotBlank(processCode)){
//			processId  = processDao.queryNewIdByCode(processCode);
//		}
//		OrderDO orderDO = new OrderDO();
//		orderDO.setProcId(processId);
//		//启动流程状态默认(1 = 开始状态)
//		orderDO.setState(ConstantEnum.INSTANCE_START);
//		super.save(orderDO);
//		map.put("processId",processId);
//		map.put("orderId",orderDO.getId());
//		return map;
//	}
//}
