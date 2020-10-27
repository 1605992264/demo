//package com.yexiao.demo.flow.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.ShiroUtils;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.ProcessNodeDao;
//import com.txdata.flow.domain.*;
//import com.txdata.flow.utils.ConstantEnum;
//import com.txdata.system.domain.UserDO;
//import com.txdata.system.service.UserService;
//import org.apache.shiro.util.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
///**
// * 流程节点表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:26
// */
// @Service
//public class ProcessNodeService extends CrudService<ProcessNodeDao, ProcessNodeDO> {
//
//    @Autowired
//    private ProcessNodeDao processNodeDao;
//    @Autowired
//	private ProcessActorService processActorService;
//    @Autowired
//	private FlowProcessService flowProcessService;
//    @Autowired
//	private FormReloService formReloService;
//    @Autowired
//	private NodeVariableService nodeVariableService;
//    @Autowired
//	private ProcessChooseService processChooseService;
//    @Autowired
//	private ControlFlowService controlFlowService;
//    @Autowired
//	private UserService userService;
//
//	/**
//	 * 作为全局变量，存放递归解析的jsonObject
//	 */
//	Set<JSONObject> jsonObjectSet = new HashSet<>();
//
//
//    /**
//	 * 通过id查找数据
//	 */
//    public ProcessNodeDO get(String id){
//        return processNodeDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<ProcessNodeDO> page(Page<ProcessNodeDO> page, Map<String, Object> map){
//        return processNodeDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<ProcessNodeDO> list(Map<String, Object> map){
//        return processNodeDao.list(map);
//    }
//
//	/**
//	 * 保存
//	 */
//	@Transactional(readOnly=false)
//	public int save(ProcessNodeDO processNodeDO){
//		return super.save(processNodeDO);
//	}
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return processNodeDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return processNodeDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         processNodeDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return processNodeDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<ProcessNodeDO> processNodes){
//    	return processNodeDao.batchInsert(processNodes);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<ProcessNodeDO> processNodes){
//		return processNodeDao.batchUpdate(processNodes);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	ProcessNodeDO processNode = processNodeDao.get(id);
//    	if (processNode != null){
//    		processNode.setId(null);
//    		processNode.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(processNode.getName())){
////    			processNode.setName(processNode.getName() + "-复制");
////    		}
//    		result = processNodeDao.insert(processNode);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param processNode 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(ProcessNodeDO processNode, Map<String,Object> whereMap){
//    	return processNodeDao.updateByWhere(processNode, whereMap);
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
//    	return processNodeDao.removeByWhere(whereMap);
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
//		return processNodeDao.deleteByWhere(whereMap);
//	}
//
//	/**
//	 * 流程保存
//	 * @param structuralData 结构数据
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public void ruleSave(String structuralData,String processId){
//		int result = 0;
//		//structuralData中的basicSetting、processData、formData、advancedSetting页签解析
//		JSONObject structuralDataJson = JSON.parseObject(structuralData);
//		JSONObject basicSetting = structuralDataJson.getJSONObject("basicSetting");
//		JSONObject processData = structuralDataJson.getJSONObject("processData");
//		JSONObject formData = structuralDataJson.getJSONObject("formData");
//		JSONObject advancedSetting = structuralDataJson.getJSONObject("advancedSetting");
//		//先对basicSetting——基础设置进行解析,再对然后advancedSetting——高级设置进行解析,得到processId流程定义Id
//		processId = flowProcessService.resolvingBasicSetting(basicSetting, advancedSetting, structuralData,processId);
//		//再对processData——流程数据进行解析
//		resolveProcessData(processData,processId);
//		//最后对formData——表单设置进行解析
//	}
//
//
//	/**
//	 * 解析processData并保存到数据库
//	 * @param processData
//	 * @param processId
//	 * @return
//	 */
//	@Transactional(readOnly=false)
//	public void resolveProcessData(JSONObject processData, String processId){
//		//使用非递归解析节点存入到jsonObjectSet
//		List<JSONObject> jsonObjectList = dealChildNode(processData);
//		//遍历除start节点的其他的节点
//		for(JSONObject jsonObject : jsonObjectList){
//			//排除某些节点的type为empty的情况
//			//if (!"empty".equals(jsonObject.getString("type"))){
//				ProcessNodeDO processNode = new ProcessNodeDO();
//				processNode.setIsBranch(CollectionUtils.isEmpty(jsonObject.getJSONArray("conditionNodes")) ? ConstantEnum.NON_EXIST_BRANCH : ConstantEnum.EXIST_BRANCH);
//				processNode.setId(jsonObject.getString("nodeId"));
//				processNode.setPrevId(jsonObject.getString("prevId"));
//				processNode.setProcessId(processId);
//				processNode.setCreateBy(ShiroUtils.getUserId());
//				processNode.setCreateDate(new Date());
//				processNode.setUpdateBy(ShiroUtils.getUserId());
//				processNode.setUpdateDate(new Date());
//				//insert(processNodeDO);
//				//对不同节点(start、approval、condition、copy)配置进行处理
//				choosePropertiesByType(jsonObject,processNode);
//			//}
//		}
//	}
//
//
//	public JSONObject childNodeRecursion(JSONObject childNode){
//		JSONObject jsonObject = null;
//		JSONArray conditionNodesArray = childNode.getJSONArray("conditionNodes");
//		if (childNode.getJSONObject("childNode") != null) {
//			jsonObject.put("childNode",childNode.getJSONObject("childNode"));
//			childNodeRecursion(jsonObject);
//		}else if (conditionNodesArray != null && conditionNodesArray.size() > 0){
//			for (int i = 0; i < conditionNodesArray.size() ; i++){
//				JSONArray childConditionNodes = conditionNodesArray.getJSONArray(i);
//				JSONObject newChildNode = conditionNodesArray.getJSONObject(i);
//				if (childConditionNodes != null && childConditionNodes.size() > 0){
//					jsonObject.put("conditionNodes",childConditionNodes);
//					childNodeRecursion(jsonObject);
//				}else if (childNode.getJSONObject("childNode") != null){
//					jsonObject.put("childNode",newChildNode);
//					childNodeRecursion(jsonObject);
//				}
//			}
//		}
//		return childNode;
//	}
//
//
//
//	/**
//	 * 起始节点配置解析
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void resolveStartNode(JSONObject properties, ProcessNodeDO processNodeDO){
//		//保存properties的title作为流程节点名称
//		processNodeDO.setName(properties.getString("title"));
//		processNodeDO.setType(ConstantEnum.START_NODE);
//		insert(processNodeDO);
//		//解析properties的initiator
//		//判断是否是ALL——所有人
//		/**
//		 * 可能报错点
//		 */
//		if ("all".equals(properties.getString("initiator"))){
//			ProcessActorDO processActorDO = new ProcessActorDO();
//			processActorDO.preInsert();
//			processActorDO.setNodeId(processNodeDO.getId());
//			processActorDO.setType(ConstantEnum.ACTOR_ALL_CLIENT);
//			processActorService.insert(processActorDO);
//		}else {
//			processActorService.isExitInitiator(properties,processNodeDO);
//		}
//		//表单权限保存(存在级联操作问题)
//		FormReloDO formReloDO = new FormReloDO();
//		formReloDO.preInsert();
//		formReloDO.setNodeId(processNodeDO.getId());
//		formReloDO.setForm(properties.getString("formOperates"));
//		formReloService.insert(formReloDO);
//	}
//
//
//	/**
//	 * 条件节点配置解析
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void resolveConditionalNode(JSONObject properties, ProcessNodeDO processNodeDO){
//		//保存properties的title作为流程节点名称,priority作为优先级
//		processNodeDO.setName(properties.getString("title"));
//		processNodeDO.setPriority(Integer.parseInt(properties.getString("priority")));
//		processNodeDO.setType(ConstantEnum.CONDITIONAL_NODE);
//		insert(processNodeDO);
//		//判断是否有发起人条件
//		processActorService.isExitInitiator(properties,processNodeDO);
//		//对非发起人条件进行解析
//		JSONArray conditionArray = properties.getJSONArray("conditions");
//		if (!CollectionUtils.isEmpty(conditionArray)){
//			//对条件中介于两个数相同的特殊情况进行处理
//			if (conditionArray.size() == 2 && conditionArray.getJSONObject(0).getString("conditionValue").equals(conditionArray.getJSONObject(1).getString("conditionValue"))) {
//				    //删除介于两数之间后面的条件
//					conditionArray.remove(1);
//					//将前一个条件的大于或者大于等于覆盖为等于
//					conditionArray.getJSONObject(0).put("cmpType", ConstantEnum.COMPARE_EQ);
//			   }
//				for (int i = 0, sort = 0; i < conditionArray.size(); i++, sort++) {
//					NodeVariableDO nodeVariableDO = new NodeVariableDO();
//					nodeVariableDO.setNodeId(processNodeDO.getId());
//					nodeVariableDO.setFormId(conditionArray.getJSONObject(i).getString("formId"));
//					nodeVariableDO.setName(conditionArray.getJSONObject(i).getString("key"));
//					nodeVariableDO.setCmpType(conditionArray.getJSONObject(i).getString("cmpType"));
//					nodeVariableDO.setVariableType(conditionArray.getJSONObject(i).getString("variableType"));
//					//根据不同变量类型(int、String、float)进行不同处理
//					if (ConstantEnum.VARIABLE_TYPE_INT.equals(nodeVariableDO.getVariableType())) {
//						nodeVariableDO.setConditionvalue(String.valueOf(conditionArray.getJSONObject(i).getIntValue("conditionValue")));
//					} else if (ConstantEnum.VARIABLE_TYPE_FLOAT.equals(nodeVariableDO.getVariableType())) {
//						nodeVariableDO.setConditionvalue(String.valueOf(conditionArray.getJSONObject(i).getFloatValue("conditionValue")));
//					} else if (ConstantEnum.VARIABLE_TYPE_STRING.equals(nodeVariableDO.getVariableType())) {
//						nodeVariableDO.setConditionvalue(conditionArray.getJSONObject(i).getString("conditionValue"));
//					}
//					nodeVariableDO.setSort(sort);
//					nodeVariableService.insert(nodeVariableDO);
//				}
//		}
//	}
//
//	/**
//	 * 审批节点配置解析
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void resolveApprovalNode(JSONObject properties, ProcessNodeDO processNodeDO){
//		String assigneeType = properties.getString("assigneeType");
//		String optionalRange = properties.getString("optionalRange");
//		JSONArray approveArray = properties.getJSONArray("approvers");
//		//根据AssigneeType进行解析
//		resolveAssigneeType(assigneeType,processNodeDO);
//		processNodeDO.setCounterSign(properties.getString("counterSign"));
//		processNodeDO.setName(properties.getString("title"));
//		processNodeDO.setRejectType(properties.getString("rejectionMod"));
//		processNodeDO.setType(ConstantEnum.APPROVAL_NODE);
//		insert(processNodeDO);
//		//判断是否是发起人自选
//		if ("optional".equals(assigneeType)){
//			if (!"all".equals(optionalRange)) {
//				if (!CollectionUtils.isEmpty(approveArray)) {
//					for (int i = 0; i < approveArray.size(); i++) {
//						ProcessChooseDO processChooseDO = new ProcessChooseDO();
//						processChooseDO.preInsert();
//						processChooseDO.setNodeId(processNodeDO.getId());
//						resolveOptionalRange(optionalRange,processChooseDO);
//						processChooseDO.setValue(approveArray.getJSONObject(i).getString("id"));
//						processChooseService.insert(processChooseDO);
//					}
//				}
//			}else {
//				ProcessChooseDO processChooseDO = new ProcessChooseDO();
//				processNodeDO.preInsert();
//				processChooseDO.setNodeId(processNodeDO.getId());
//				resolveOptionalRange(optionalRange,processChooseDO);
//				processChooseService.insert(processChooseDO);
//			}
//		}else if ("myself".equals(assigneeType)){
//			ProcessActorDO processActorDO = new ProcessActorDO();
//			processActorDO.preInsert();
//			processActorDO.setNodeId(processNodeDO.getId());
//			auditTypeToActor(assigneeType,processActorDO);
//			processActorService.insert(processActorDO);
//		}else {
//			//如果有多个审批人，则遍历进行插入，如果approveArray为空，则是类型为"部门领导"
//			if (!CollectionUtils.isEmpty(approveArray)){
//				for (int i = 0 ; i < approveArray.size() ; i++){
//					ProcessActorDO processActorDO = new ProcessActorDO();
//					processActorDO.preInsert();
//					processActorDO.setNodeId(processNodeDO.getId());
//					auditTypeToActor(assigneeType,processActorDO);
//					processActorDO.setValue(approveArray.getJSONObject(i).getString("id"));
//					processActorService.insert(processActorDO);
//				}
//			}else {
//				ProcessActorDO processActorDO = new ProcessActorDO();
//				processActorDO.preInsert();
//				processActorDO.setNodeId(processNodeDO.getId());
//				auditTypeToActor(assigneeType,processActorDO);
//				processActorDO.setValue(properties.getString("directorLevel"));
//				processActorService.insert(processActorDO);
//			}
//		}
//		//对表单权限的处理(存在级联操作的错误)
//		FormReloDO formReloDO = new FormReloDO();
//		formReloDO.preInsert();
//		formReloDO.setNodeId(processNodeDO.getId());
//		formReloDO.setForm(properties.getString("formOperates"));
//		formReloService.insert(formReloDO);
//	}
//
//	/**
//	 * 抄送节点配置解析
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void resolveCopyNode(JSONObject properties, ProcessNodeDO processNodeDO){
//		processNodeDO.setName(properties.getString("title"));
//		processNodeDO.setType(ConstantEnum.COPY_NODE);
//		//判断是否发起人自选抄送人(true = 是 false = 否)
//		boolean userOptional = properties.getBoolean("userOptional");
//		JSONArray membersArray = properties.getJSONArray("menbers");
//		if (userOptional){
//			processNodeDO.setAuditType(ConstantEnum.AUDIT_OPTIONAL);
//		}else {
//			//如果为否,默认修改为指定用户
//			processNodeDO.setAuditType(ConstantEnum.AUDIT_APPOINT_CLIENT);
//		}
//		insert(processNodeDO);
//		if (!CollectionUtils.isEmpty(membersArray)){
//			for (int i = 0 ; i < membersArray.size() ; i++){
//				ProcessActorDO processActorDO = new ProcessActorDO();
//				processActorDO.preInsert();
//				processActorDO.setNodeId(processNodeDO.getId());
//				processActorDO.setValue(membersArray.getJSONObject(i).getString("id"));
//				processActorDO.setType(ConstantEnum.AUDIT_APPOINT_CLIENT);
//				processActorService.insert(processActorDO);
//			}
//		}
//	}
//
//	/**
//	 * 处理空节点
//	 * @param properties
//	 * @param processNodeDO
//	 */
//	public void resolveEmptyNode(JSONObject properties, ProcessNodeDO processNodeDO){
//		//添加节点类型（8 = 空节点）
//		processNodeDO.setType(ConstantEnum.EMPTY_NODE);
//		if (processNodeDO != null){
//			processNodeDao.insert(processNodeDO);
//		}
//	}
//
//	/**
//	 * 根据AssigneeType进行解析
//	 * @param assigneeType
//	 * @param processNodeDO
//	 */
//	public void resolveAssigneeType(String assigneeType, ProcessNodeDO processNodeDO){
//		switch (assigneeType){
//			case "user":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_APPOINT_CLIENT);
//				break;
//			case "director":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_DEPARTMENT_LEADER);
//				break;
//			case "office":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_DEPARTMENT);
//				break;
//			case "role":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_ROLE);
//				break;
//			case "myself":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_ONESELF);
//				break;
//			case "optional":
//				processNodeDO.setAuditType(ConstantEnum.AUDIT_OPTIONAL);
//				break;
//		}
//	}
//
//	/**
//	 * 解析自选范围
//	 * @param optionalRange
//	 * @param processChooseDO
//	 */
//	public void resolveOptionalRange(String optionalRange,ProcessChooseDO processChooseDO){
//		switch (optionalRange){
//			case "user":
//				processChooseDO.setType(ConstantEnum.ACTOR_CLIENT);
//				break;
//			case "role":
//				processChooseDO.setType(ConstantEnum.ACTOR_ROLE);
//				break;
//			case "office":
//				processChooseDO.setType(ConstantEnum.ACTOR_DEPT_CLIENT);
//				break;
//			case "all":
//				processChooseDO.setType(ConstantEnum.ACTOR_ALL_CLIENT);
//				break;
//		}
//	}
//
//	/**
//	 * auditType与参与人类型的转换
//	 * @param assigneeType
//	 * @param processActorDO
//	 */
//	public void auditTypeToActor(String assigneeType, ProcessActorDO processActorDO){
//		switch (assigneeType){
//			case ConstantEnum.AUDIT_NEW_APPOINT_CLIENT:
//				processActorDO.setType(ConstantEnum.ACTOR_CLIENT);
//				break;
//			case ConstantEnum.AUDIT_NEW_ROLE:
//				processActorDO.setType(ConstantEnum.ACTOR_ROLE);
//				break;
//			case ConstantEnum.AUDIT_NEW_DEPARTMENT:
//				processActorDO.setType(ConstantEnum.ACTOR_DEPT_CLIENT);
//				break;
//			case ConstantEnum.AUDIT_NEW_DEPARTMENT_LEADER:
//				processActorDO.setType(ConstantEnum.ACTOR_DEPT_LEADER);
//				break;
//			case ConstantEnum.AUDIT_NEW_ONESELF:
//				processActorDO.setType(ConstantEnum.ACTOR_MYSELF);
//				break;
//		}
//	}
//
//	/**
//	 * 针对前端传递的Type(start、approval、copy、condition)进行选择对应的
//	 * @param processNodeJson
//	 * @param processNodeDO
//	 */
//	public void choosePropertiesByType(JSONObject processNodeJson, ProcessNodeDO processNodeDO){
//		String processNodeType = processNodeJson.getString("type");
//		JSONObject propertiesJson = processNodeJson.getJSONObject("properties");
//		switch (processNodeType){
//			case "start":
//				resolveStartNode(propertiesJson,processNodeDO);
//				break;
//			case "approver":
//				resolveApprovalNode(propertiesJson,processNodeDO);
//				break;
//			case "condition":
//				resolveConditionalNode(propertiesJson,processNodeDO);
//				break;
//			case "copy":
//				resolveCopyNode(propertiesJson,processNodeDO);
//				break;
//			case "empty":
//				resolveEmptyNode(propertiesJson,processNodeDO);
//				break;
//		}
//	}
//
//	/**
//	 * 非递归方式
//	 * @param processDate
//	 * @return
//	 */
//	public List<JSONObject> dealChildNode(JSONObject processDate){
//		List<JSONObject> jsonObjectList = new ArrayList<>();
//		if (processDate == null){
//			return jsonObjectList;
//		}
//		Stack<JSONObject> jsonObjectStack = new Stack<>();
//		jsonObjectStack.push(processDate);
//		while (!jsonObjectStack.isEmpty()){
//			JSONObject object = jsonObjectStack.pop();
//			jsonObjectList.add(object);
//			if (object.getJSONObject("childNode") != null){
//				jsonObjectStack.push(object.getJSONObject("childNode"));
//			}
//			if (!CollectionUtils.isEmpty(object.getJSONArray("conditionNodes"))){
//				for (int i = 0; i < object.getJSONArray("conditionNodes").size() ; i++){
//					jsonObjectStack.push(object.getJSONArray("conditionNodes").getJSONObject(i));
//				}
//			}
//		}
//		return jsonObjectList;
//	}
//
//	/**
//	 * 根据发起人id与节点id查询具体用户
//	 * @param user
//	 * @param nodeId
//	 * @return
//	 */
//	public String queryParticularUsers(String user,String nodeId){
//		//查询当前的nodeId
//		ProcessNodeDO processNodeDO = processNodeDao.get(nodeId);
//		Set<String> userSet = controlFlowService.getUserIdListByNode(processNodeDO,user);
//		List<String> userNameArrays = new ArrayList<>();
//		String userNameChain = null;
//		if (!CollectionUtils.isEmpty(userSet)){
//			for (String str : userSet){
//				UserDO userDO = userService.get(str);
//				if(userDO != null) {
//                    userNameArrays.add(userDO.getName());
//                }
//			}
//			userNameChain = StringUtils.join(userNameArrays.toArray(),",");
//		}
//		return userNameChain;
//	}
//
//
//	/**递归存在局限性(已启用)
//	 * 针对子节点为jsonObject类型childNode进行处理
//	 * @param jsonObject
//	 * @return
//	 */
//	/*public Set<JSONObject> dealChildNode(JSONObject jsonObject) {
//		if (jsonObject.getJSONObject("childNode") != null) {
//			if (!CollectionUtils.isEmpty(jsonObject.getJSONArray("conditionNodes"))){
//				dealConditionNode(jsonObject);
//			}
//			jsonObjectSet.add(jsonObject.getJSONObject("childNode"));
//			dealChildNode(jsonObject.getJSONObject("childNode"));
//		}
//		return jsonObjectSet;
//	}*/
//
//	/**递归存在局限性(已启用)
//	 * 针对子节点为jsonArray类型conditionNodes进行处理
//	 * @param jsonObject
//	 * @return
//	 */
//	/*public Set<JSONObject> dealConditionNode(JSONObject jsonObject) {
//		JSONArray conditionNodesArray = jsonObject.getJSONArray("conditionNodes");
//		if (!CollectionUtils.isEmpty(conditionNodesArray)) {
//			for (int i = 0; i < conditionNodesArray.size(); i++) {
//				if (conditionNodesArray.getJSONObject(i).getJSONObject("childNode") != null) {
//					if (!CollectionUtils.isEmpty(conditionNodesArray.getJSONObject(i).getJSONArray("conditionNodes"))) {
//						dealConditionNode(conditionNodesArray.getJSONObject(i));
//					}
//					jsonObjectSet.add(conditionNodesArray.getJSONObject(i));
//					dealChildNode(conditionNodesArray.getJSONObject(i));
//				}else {
//					if (!CollectionUtils.isEmpty(conditionNodesArray.getJSONObject(i).getJSONArray("conditionNodes"))) {
//						dealConditionNode(conditionNodesArray.getJSONObject(i));
//					}
//					jsonObjectSet.add(conditionNodesArray.getJSONObject(i));
//				}
//			}
//		}
//		return jsonObjectSet;
//	}*/
//
//	/**
//	 * 全局变量jsonObjectSet中元素进行清空
//	 */
//	/*public void setClear(){
//		jsonObjectSet.clear();
//	}*/
//
//	/**
//     * 獲取開始节点
//     * */
//	public ProcessNodeDO getStartNode(String orderId){
//	    return processNodeDao.getStartNode(orderId);
//    }
//}
