//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.HiActorDao;
//import com.txdata.flow.dao.HiTaskDao;
//import com.txdata.flow.domain.HiTaskDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程实例历史记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:46
// */
// @Service
//public class HiTaskService extends CrudService<HiTaskDao, HiTaskDO> {
//
//    @Autowired
//    private HiTaskDao hiTaskDao;
//    @Autowired
//    private HiActorDao hiActorDao;
//
//    /**
//	 * 通过id查找数据
//	 */
//    public HiTaskDO get(String id){
//        return hiTaskDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<HiTaskDO> page(Page<HiTaskDO> page, Map<String, Object> map){
//        return hiTaskDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<HiTaskDO> list(Map<String, Object> map){
//        return hiTaskDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(HiTaskDO hiTask){
//        return super.save(hiTask);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return hiTaskDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return hiTaskDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         hiTaskDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return hiTaskDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<HiTaskDO> hiTasks){
//    	return hiTaskDao.batchInsert(hiTasks);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<HiTaskDO> hiTasks){
//		return hiTaskDao.batchUpdate(hiTasks);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	HiTaskDO hiTask = hiTaskDao.get(id);
//    	if (hiTask != null){
//    		hiTask.setId(null);
//    		hiTask.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiTask.getName())){
////    			hiTask.setName(hiTask.getName() + "-复制");
////    		}
//    		result = hiTaskDao.insert(hiTask);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param hiTask 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(HiTaskDO hiTask, Map<String,Object> whereMap){
//    	return hiTaskDao.updateByWhere(hiTask, whereMap);
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
//    	return hiTaskDao.removeByWhere(whereMap);
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
//		return hiTaskDao.deleteByWhere(whereMap);
//	}
//
//	public int count(Map<String,Object> whereMap){
//	    return hiTaskDao.count(whereMap);
//    }
//
//	/**
//     * 获取已审批人的id
//     * @param whereMap 条件
//     * */
//    public List<String> getUserIdByWhere(Map<String, Object> whereMap){
//	    return hiTaskDao.getUserIdByWhere(whereMap);
//    }
//
//    /**
//     * 获取上一节点(非自动通过的)
//     * */
//    public HiTaskDO getPrevHiTask(String orderId){
//        return hiTaskDao.getPrevHiTask(orderId);
//    }
//
//    /**
//     * 删除审批记录表和审批人
//     * */
//    @Transactional
//    public void deleteHiTask(String orderId,String sort){
//        Map<String,Object> map = new HashMap<>();
//        map.put("orderId",orderId);
//        map.put("sort",sort);
//        List<HiTaskDO> list = hiTaskDao.list(map);
//        for(HiTaskDO item : list){
//            // 删除从表
//            Map<String,Object> actorMap = new HashMap<>();
//            actorMap.put("taskId",item.getId());
//            hiActorDao.deleteByWhere(actorMap);
//            // 删除主表
//            hiTaskDao.delete(item.getId());
//        }
//    }
//}
