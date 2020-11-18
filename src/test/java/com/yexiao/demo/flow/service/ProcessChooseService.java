//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.ProcessChooseDao;
//import com.txdata.flow.domain.ProcessChooseDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程发起人自选人员表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-02 15:13:15
// */
// @Service
//public class ProcessChooseService extends CrudService<ProcessChooseDao, ProcessChooseDO> {
//
//    @Autowired
//    private ProcessChooseDao processChooseDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public ProcessChooseDO get(String id){
//        return processChooseDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<ProcessChooseDO> page(Page<ProcessChooseDO> page, Map<String, Object> map){
//        return processChooseDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<ProcessChooseDO> list(Map<String, Object> map){
//        return processChooseDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(ProcessChooseDO processChoose){
//        return super.save(processChoose);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return processChooseDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return processChooseDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int delete(String id){
//        return processChooseDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return processChooseDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<ProcessChooseDO> processChooses){
//    	return processChooseDao.batchInsert(processChooses);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<ProcessChooseDO> processChooses){
//		return processChooseDao.batchUpdate(processChooses);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	ProcessChooseDO processChoose = processChooseDao.get(id);
//    	if (processChoose != null){
//    		processChoose.setId(null);
//    		processChoose.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(processChoose.getName())){
////    			processChoose.setName(processChoose.getName() + "-复制");
////    		}
//    		result = processChooseDao.insert(processChoose);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param processChoose 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(ProcessChooseDO processChoose, Map<String,Object> whereMap){
//    	return processChooseDao.updateByWhere(processChoose, whereMap);
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
//    	return processChooseDao.removeByWhere(whereMap);
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
//		return processChooseDao.deleteByWhere(whereMap);
//	}
//
//	public int count(Map<String,Object> whereMap){
//	    return processChooseDao.count(whereMap);
//    }
//
//     /**
//     * 查询未审批的人
//     * @param nodeId 节点id
//     * @param actorIdList 已审批了的id
//     * */
//    List<ProcessChooseDO> findOtherActor(List<String> actorIdList, String nodeId){
//        return processChooseDao.findOtherActor(actorIdList,nodeId);
//    }
//}
