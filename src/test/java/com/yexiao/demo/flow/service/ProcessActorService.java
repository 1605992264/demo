//package com.yexiao.demo.flow.service;
//
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.shiro.util.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程参与人表
// *
// * @author xiewh
// * @email xiewh@3xdata.cn
// * @date 2020-06-29 17:03:16
// */
// @Service
//public class ProcessActorService extends CrudService<ProcessActorDao, ProcessActorDO> {
//
//    @Autowired
//    private ProcessActorDao processActorDao;
//    @Autowired
//	private ProcessActorService processActorService;
//    @Autowired
//	private UserDao userDao;
//
//    /**
//	 * 通过id查找数据
//	 */
//    public ProcessActorDO get(String id){
//        return processActorDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<ProcessActorDO> page(Page<ProcessActorDO> page, Map<String, Object> map){
//        return processActorDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<ProcessActorDO> list(Map<String, Object> map){
//        return processActorDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(ProcessActorDO processActor){
//        return super.save(processActor);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return processActorDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return processActorDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         processActorDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return processActorDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<ProcessActorDO> processActors){
//    	return processActorDao.batchInsert(processActors);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<ProcessActorDO> processActors){
//		return processActorDao.batchUpdate(processActors);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	ProcessActorDO processActor = processActorDao.get(id);
//    	if (processActor != null){
//    		processActor.setId(null);
//    		processActor.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(processActor.getName())){
////    			processActor.setName(processActor.getName() + "-复制");
////    		}
//    		result = processActorDao.insert(processActor);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param processActor 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(ProcessActorDO processActor, Map<String,Object> whereMap){
//    	return processActorDao.updateByWhere(processActor, whereMap);
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
//    	return processActorDao.removeByWhere(whereMap);
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
//		return processActorDao.deleteByWhere(whereMap);
//	}
//
//    public int count( Map<String, Object> whereMap){
//        return processActorDao.count(whereMap);
//    }
//
//
//	/**
//	 * 是否含有发起人节点(仅供起始节点、条件节点)
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void isExitInitiator(JSONObject properties, ProcessNodeDO processNodeDO){
//		JSONArray initiatorArray = properties.getJSONArray("initiator");
//		if (initiatorArray != null && initiatorArray.size() > 0){
//			for (int i = 0 ; i < initiatorArray.size() ; i++){
//				if (StringUtils.isNotBlank(initiatorArray.getJSONObject(i).getString("id"))) {
//					processActorService.remove(initiatorArray.getJSONObject(i).getString("id"));
//				}
//				ProcessActorDO processActorDO = new ProcessActorDO();
//				processActorDO.preInsert();
//				processActorDO.setNodeId(processNodeDO.getId());
//				processActorDO.setValue(initiatorArray.getJSONObject(i).getString("id"));
//				String type = initiatorArray.getJSONObject(i).getString("type");
//				//根据用户类型进行区分
//				if ("user".equals(type)) {
//					processActorDO.setType(ConstantEnum.ACTOR_CLIENT);
//				} else if ("office".equals(type)) {
//					processActorDO.setType(ConstantEnum.ACTOR_DEPT_CLIENT);
//				} else if ("role".equals(type)) {
//					processActorDO.setType(ConstantEnum.ACTOR_ROLE);
//				}
//				processActorService.insert(processActorDO);
//			}
//		}
//	}
//
//	/**
//	 * 查询起始节点的条件
//	 * @param processCode 流程实例id
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public JSONArray queryStartCondition(String processCode){
//		return processActorDao.queryStartCondition(processCode);
//	}
//
//	/**
//	 * 根据流程实例id查询所有部门或者指定人员或者all的用户集合
//	 * @param processInsId
//	 */
//	public List<String> explainUserAndOffice(String processInsId) {
//		JSONArray startActors = queryStartCondition(processInsId);
//		List<String> totalUserIds = new ArrayList<>();
//		String type = null;
//		String value = null;
//		if (!CollectionUtils.isEmpty(startActors)) {
//			//判断起始节点的条件的类型为“所有人”
//			if (startActors.size() == 1 && ConstantEnum.ACTOR_ALL_CLIENT.equals(startActors.getJSONObject(0).getString("type"))) {
//				totalUserIds =  userDao.findUserId(null);
//			}else {
//				for (int i = 0 ; i < startActors.size() ; i++){
//					type = startActors.getJSONObject(i).getString("type");
//					value = startActors.getJSONObject(i).getString("value");
//					//如果type是“指定员工”
//					if (ConstantEnum.ACTOR_CLIENT.equals(type)){
//						totalUserIds.add(value);
//					}else if (ConstantEnum.ACTOR_DEPT_CLIENT.equals(type)){
//						totalUserIds.addAll(userDao.findAllUserByOfficeId(value));
//					}
//				}
//			}
//		} else {
//			//throw new RuntimeException("该流程起始节点没有条件");
//		}
//		return totalUserIds;
//	}
//}
