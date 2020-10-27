//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.OrderTaskDao;
//import com.txdata.flow.dao.TaskActorDao;
//import com.txdata.flow.domain.OrderTaskDO;
//import com.txdata.flow.domain.ProcessNodeDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例执行表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:07
// */
// @Service
//public class OrderTaskService extends CrudService<OrderTaskDao, OrderTaskDO> {
//
//    @Autowired
//    private OrderTaskDao orderTaskDao;
//    @Autowired
//    private TaskActorDao taskActorDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public OrderTaskDO get(String id){
//        return orderTaskDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<OrderTaskDO> page(Page<OrderTaskDO> page, Map<String, Object> map){
//        return orderTaskDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<OrderTaskDO> list(Map<String, Object> map){
//        return orderTaskDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(OrderTaskDO orderTask){
//        return super.save(orderTask);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return orderTaskDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return orderTaskDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//        orderTaskDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return orderTaskDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<OrderTaskDO> orderTasks){
//    	return orderTaskDao.batchInsert(orderTasks);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<OrderTaskDO> orderTasks){
//		return orderTaskDao.batchUpdate(orderTasks);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	OrderTaskDO orderTask = orderTaskDao.get(id);
//    	if (orderTask != null){
//    		orderTask.setId(null);
//    		orderTask.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(orderTask.getName())){
////    			orderTask.setName(orderTask.getName() + "-复制");
////    		}
//    		result = orderTaskDao.insert(orderTask);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param orderTask 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(OrderTaskDO orderTask, Map<String,Object> whereMap){
//    	return orderTaskDao.updateByWhere(orderTask, whereMap);
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
//    	return orderTaskDao.removeByWhere(whereMap);
//    }
//
//	/**
//	 *
//	 * @Description: 物理删除（通过自定义的条件进行物理删除操作）慎用
//	 * @param whereMap 物理删除条件
//	 * @return: 返回物理删除数量
//	 */
//	@Transactional
//	public int deleteByWhere(Map<String,Object> whereMap){
//        List<OrderTaskDO> list = orderTaskDao.list(whereMap);
//        for(OrderTaskDO orderTaskDO : list){
//            Map<String,Object> deleteMap = new HashMap();
//            deleteMap.put("taskId",orderTaskDO.getId());
//            taskActorDao.deleteByWhere(deleteMap);
//        }
//        return orderTaskDao.deleteByWhere(whereMap);
//	}
//
//	/**
//	 * 在启动流程时，要保存在流程实例执行表中
//	 * @param orderId
//	 * @param nodeId
//	 */
//	public void relateStartProcess(String orderId,String nodeId){
//		OrderTaskDO orderTaskDO = new OrderTaskDO();
//		orderTaskDO.setNodeId(nodeId);
//		orderTaskDO.setOrderId(orderId);
//		save(orderTaskDO);
//	}
//
//	/**
//     * 1.用户 3.角色 4.All 5.部门 6.发起人自己
//     *  获取待审批的流程
//     * @param userId 用户id
//     * */
//	public String getOrderIdByUser(String userId){
//        return orderTaskDao.getOrderIdByUser(userId);
//    }
//
//    /**
//     * 部门领导（无法递归取上级）
//     *  获取待审批的流程
//     * @param userId 用户id和一个空
//     * */
//    public List<ProcessNodeDO> findNodeLeaderByUserId(String userId){
//        List<String> userList = new ArrayList<>();
//        userList.add("");
//        userList.add(userId);
//        return orderTaskDao.findOrderLeaderByUserId(userList);
//    }
//
//
//}
