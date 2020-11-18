//package com.yexiao.demo.flow.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.FlowProcessDao;
//import com.txdata.flow.domain.FlowProcessDO;
//import com.txdata.flow.domain.OrderDO;
//import com.txdata.flow.domain.OrderTaskDO;
//import com.txdata.flow.domain.ProcessNodeDO;
//import com.txdata.flow.utils.ConstantEnum;
//import com.txdata.flow.utils.ConstantMethod;
//import com.txdata.flow.utils.ProcessDefinitionType;
//import org.apache.shiro.util.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程定义表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:14
// */
// @Service
//public class FlowProcessService extends CrudService<FlowProcessDao, FlowProcessDO> {
//
//    @Autowired
//    private FlowProcessDao processDao;
//	@Autowired
//	private ProcessNodeService processNodeService;
//	@Autowired
//	private ProcessChooseService processChooseService;
//	@Autowired
//	private ProcessActorService processActorService;
//	@Autowired
//	private NodeVariableService nodeVariableService;
//	@Autowired
//	private FormReloService formReloService;
//	@Autowired
//	private OrderService orderService;
//	@Autowired
//	private OrderTaskService orderTaskService;
//	@Autowired
//	private ControlFlowService controlFlowService;
//
//
//    /**
//	 * 通过id查找数据
//	 */
//    public FlowProcessDO get(String id){
//        return processDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<FlowProcessDO> page(Page<FlowProcessDO> page, Map<String, Object> map){
//        return processDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<FlowProcessDO> list(Map<String, Object> map){
//        return processDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(FlowProcessDO process){
//        return super.save(process);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return processDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return processDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int delete(String id){
//        return processDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return processDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<FlowProcessDO> processs){
//    	return processDao.batchInsert(processs);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<FlowProcessDO> processs){
//		return processDao.batchUpdate(processs);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	FlowProcessDO process = processDao.get(id);
//    	if (process != null){
//    		process.setId(null);
//    		process.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(process.getName())){
////    			process.setName(process.getName() + "-复制");
////    		}
//    		result = processDao.insert(process);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param process 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(FlowProcessDO process, Map<String,Object> whereMap){
//    	return processDao.updateByWhere(process, whereMap);
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
//    	return processDao.removeByWhere(whereMap);
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
//		return processDao.deleteByWhere(whereMap);
//	}
//
//	/**
//	 * 解析并且保存基础设置以及高级设置到数据库
//	 * @param basicSetting
//	 * @param advancedSetting
//	 * @param structuralData
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public String resolvingBasicSetting(JSONObject basicSetting, JSONObject advancedSetting, String structuralData, String  processId){
//		if (StringUtils.isBlank(processId)){
//			FlowProcessDO flowProcessDO = new FlowProcessDO();
//			flowProcessDO.preInsert();
//			//调用重构流程定义保存方法
//			processRebuild(basicSetting,advancedSetting,flowProcessDO,structuralData);
//			//版本默认为发布状态(后期改为默认草稿状态，版本2)
//			flowProcessDO.setState(ConstantEnum.PUBLISH_STATE);
//			//版本默认为1
//			flowProcessDO.setVersion(1);
//			//随机生成的流程编号
//			flowProcessDO.setProcessCode(ConstantMethod.autoGenerateCode("P",4));
//			processDao.insert(flowProcessDO);
//			processId = flowProcessDO.getId();
//		}else {
//			FlowProcessDO flowProcessDO = processDao.get(processId);
//			//将原先的状态变为草稿
//			flowProcessDO.setState(ConstantEnum.DRAFT_STATE);
//			save(flowProcessDO);
//			FlowProcessDO newFlowProcessDO = new FlowProcessDO();
//			//调用重构流程定义保存方法
//			processRebuild(basicSetting,advancedSetting,newFlowProcessDO,structuralData);
//			newFlowProcessDO.preInsert();
//			int version = processDao.queryMaxVersion(flowProcessDO.getId());
//			//版本自增1
//			newFlowProcessDO.setVersion(version+1);
//			//版本默认为发布状态(后期改为默认草稿状态，版本-1)
//            newFlowProcessDO.setState(ConstantEnum.PUBLISH_STATE);
//			//随机生成的流程编号
//			newFlowProcessDO.setProcessCode(flowProcessDO.getProcessCode());
//			processDao.insert(newFlowProcessDO);
//			processId = newFlowProcessDO.getId();
//		}
//		return processId;
//	}
//
//	/**
//	 * //流程定义表重复代码合并
//	 * @param basicSetting
//	 * @param advancedSetting
//	 * @param flowProcessDO
//	 * @param structuralData
//	 */
//	public void processRebuild(JSONObject basicSetting, JSONObject advancedSetting, FlowProcessDO flowProcessDO, String structuralData){
//		//将basicSetting里面的字段解析存到流程定义对象中
//		flowProcessDO.setName(basicSetting.getString("flowName"));
//		flowProcessDO.setFlowImg(basicSetting.getString("flowImg"));
//		flowProcessDO.setFlowGroup(basicSetting.getString("flowGroup"));
//		flowProcessDO.setContent(basicSetting.getString("flowRemark"));
//		//将advancedSetting里面的字段解析存到流程定义对象中
//		//flowProcessDO.setAutoRepeat(advancedSetting.getBoolean("autoRepeat") == true ? ProcessDefinitionType.AUTO_REPEAT : ProcessDefinitionType.NON_AUTO_REPEAT);
//		flowProcessDO.setAutoRepeat(advancedSetting.getString("autoRepeat"));
//		flowProcessDO.setMyAuditAutoPass(advancedSetting.getBoolean("myAuditAutoPass") == true ? ProcessDefinitionType.MY_AUDIT_AUTO_PASS : ProcessDefinitionType.NON_MY_AUDIT_AUTO_PASS);
//		flowProcessDO.setNotVisibleForSponsor(advancedSetting.getBoolean("notVisibleForSponsor") == true ? ProcessDefinitionType.VISIBLE_FOR_SPONSOR : ProcessDefinitionType.NON_VISIBLE_FOR_SPONSOR);
//		flowProcessDO.setRemarkRequired(advancedSetting.getBoolean("remarkRequired") == true ? ProcessDefinitionType.REMARK_REQUIRED : ProcessDefinitionType.NON_REMARK_REQUIRED);
//		flowProcessDO.setRemarkTip(advancedSetting.getString("remarkTip"));
//		//存储原始格式
//		flowProcessDO.setOriginal(structuralData);
//	}
//
//	/**
//	 * 链式逻辑删除(流程定义、节点以及相应的条件)
//	 * @param processId
//	 * @return
//	 */
//	public int removeWithChain(String processId){
//		int result = 0;
//		Map<String,Object> processMap = new HashMap<>();
//		processMap.put("processId",processId);
//		//先查询当前流程下的所有的节点
//		List<ProcessNodeDO> processNodeDOList = processNodeService.list(processMap);
//		if (!CollectionUtils.isEmpty(processNodeDOList)) {
//			for (ProcessNodeDO processNodeDO : processNodeDOList) {
//				Map<String,Object> nodeMap = new HashMap<>();
//				String nodeId = processNodeDO.getId();
//				nodeMap.put("nodeId",nodeId);
//				//对四张条件(权限、人员)表进行逻辑删除
//				processActorService.removeByWhere(nodeMap);
//				processChooseService.removeByWhere(nodeMap);
//				nodeVariableService.removeByWhere(nodeMap);
//				formReloService.removeByWhere(nodeMap);
//			}
//		}
//		//删除所有与当前流程有关的节点
//		processNodeService.removeByWhere(processMap);
//		//最后删除流程
//		result = remove(processId);
//		return result;
//	}
//
//	/**
//	 * 通过流程编号查询最新版本的流程
//	 * @param processCode
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public FlowProcessDO queryMaxVersionInfo(String processCode){
//		return processDao.queryMaxVersionInfo(processCode);
//	}
//
//	/**
//	 * 按照指定的返回格式查询所有的流程信息
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public Page<JSONObject> queryAllProcess(Page<JSONObject> page, Map<String, Object> map){
//		return processDao.queryAllProcess(page, map);
//	}
//
//
//	/**
//	 * 当前报销单流程节点位置
//	 * @param processId 该processId代表流程实例id
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public JSONObject form(String processId){
//		Map<String,Object> map = new HashMap<>();
//		Map<String,Object> param = new HashMap<>();
//		//List<String> nodeList = new ArrayList<>();
//		JSONObject jsonMap = new JSONObject();
//		JSONObject jsonObject = new JSONObject();
//		String activeNodeId = null;
//		String originalData = null;
//		String nodePath = null;
//		map.put("orderId",processId);
//		param.put("procInsId",processId);
//		//查询对应的报销单
//		/*if (!CollectionUtils.isEmpty(reimbursementService.list(param))){
//			ReimbursementDO reimbursementDO = reimbursementService.list(param).get(0);
//			jsonObject = (JSONObject) JSONObject.toJSON(reimbursementDO);
//			JSONArray costDetailDOS = jsonObject.getJSONArray("costDetailDOS");
//			jsonObject.put("costDetail",costDetailDOS);
//			jsonObject.remove("costDetailDOS");
//		}*/
//		//查看指定的流程实例对象
//		OrderDO orderDO = orderService.get(processId);
//		if (orderDO != null) {
//			FlowProcessDO process = processDao.get(orderDO.getProcId());
//			originalData = process.getOriginal();
//		}
//		//根据流程实例id取得正在进行的节点id
//		//String nodePath = controlFlowService.nodePath(processId,null);
//		//对nodePath最后的逗号进行删除
////		int index = nodePath.lastIndexOf(",");
////		if(index!=-1){
////			nodePath = nodePath.substring(0,index)+nodePath.substring(index+1,nodePath.length());
////		}
//		List<OrderTaskDO> orderTaskDOList = orderTaskService.list(map);
//		if (!CollectionUtils.isEmpty(orderTaskDOList)){
//			activeNodeId = orderTaskDOList.get(0).getNodeId();
//		}
//		//nodePath = controlFlowService.nodePath(processId,activeNodeId,);
//		//查看流程图JSON
//		JSONObject structuralData = JSON.parseObject(originalData);
//		jsonMap.put("structuralData", structuralData);
//		jsonMap.put("nodePath",nodePath);
//		jsonMap.put("activeNodeId",activeNodeId);
//		//jsonMap.put("formObject",jsonObject);
//		return jsonMap;
//	}
//
//	/**
//	 * 查看历史版本(单个)
//	 * @param params
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public JSONObject queryHisProcess(Map<String,Object> params){
//		return processDao.queryHisProcess(params);
//	}
//
//	/**
//	 * 查看历史版本(分页)
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public Page<JSONObject> queryHisProcessList(Page<JSONObject> page, Map<String,Object> map){
//		return processDao.queryHisProcess(page, map);
//	}
//
//	@Transactional(readOnly = true)
//	public FlowProcessDO form(String processCode, int version){
//		return processDao.form(processCode,version);
//	}
//
//	/**
//	 * 根据流程id查询流程标识最大的版本号
//	 * @param processId
//	 * @return
//	 */
//	@Transactional(readOnly = true)
//	public int  queryMaxVersion(String processId){
//		return processDao.queryMaxVersion(processId);
//	}
//
//	/**
//	 * 修改流程状态为“发布”状态
//	 * @param processCode
//	 * @param version
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public int updateProcessState(String processCode,String version){
//		return processDao.updateProcessState(processCode, version);
//	}
//
//	/**
//	 * 修改其他流程状态为“草稿”状态
//	 * @param processCode
//	 * @return
//	 */
//	@Transactional(readOnly = false)
//	public int updateOtherProcessState(String processCode){
//		return processDao.updateOtherProcessState(processCode);
//	}
//
//    /**
//     * 获取流程id
//     * */
//    public String queryNewIdByCode(String processCode){
//        return processDao.queryNewIdByCode(processCode);
//    }
//}
