//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.NodeVariableDao;
//import com.txdata.flow.domain.NodeVariableDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程节点条件表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:21:04
// */
// @Service
//public class NodeVariableService extends CrudService<NodeVariableDao, NodeVariableDO> {
//
//    @Autowired
//    private NodeVariableDao nodeVariableDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public NodeVariableDO get(String id){
//        return nodeVariableDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<NodeVariableDO> page(Page<NodeVariableDO> page, Map<String, Object> map){
//        return nodeVariableDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<NodeVariableDO> list(Map<String, Object> map){
//        return nodeVariableDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(NodeVariableDO nodeVariable){
//        return super.save(nodeVariable);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return nodeVariableDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return nodeVariableDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         nodeVariableDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return nodeVariableDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<NodeVariableDO> nodeVariables){
//    	return nodeVariableDao.batchInsert(nodeVariables);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<NodeVariableDO> nodeVariables){
//		return nodeVariableDao.batchUpdate(nodeVariables);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	NodeVariableDO nodeVariable = nodeVariableDao.get(id);
//    	if (nodeVariable != null){
//    		nodeVariable.setId(null);
//    		nodeVariable.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(nodeVariable.getName())){
////    			nodeVariable.setName(nodeVariable.getName() + "-复制");
////    		}
//    		result = nodeVariableDao.insert(nodeVariable);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param nodeVariable 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(NodeVariableDO nodeVariable, Map<String,Object> whereMap){
//    	return nodeVariableDao.updateByWhere(nodeVariable, whereMap);
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
//    	return nodeVariableDao.removeByWhere(whereMap);
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
//		return nodeVariableDao.deleteByWhere(whereMap);
//	}
//}
