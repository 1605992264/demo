//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.HiCirculationDao;
//import com.txdata.flow.domain.HiCirculationDO;
//import com.txdata.flow.domain.HiCirculationDetailedDO;
//import com.txdata.flow.domain.ProcessNodeDO;
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
// * 流程实例历史记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:54:44
// */
// @Service
//public class HiCirculationService extends CrudService<HiCirculationDao, HiCirculationDO> {
//
//    @Autowired
//    private HiCirculationDao hiCirculationDao;
//    @Autowired
//	private HiCirculationDetailedService hiCirculationDetailedService;
//    @Autowired
//	private ProcessNodeService processNodeService;
//    @Autowired
//	private ControlFlowService controlFlowService;
//    @Autowired
//	private UserService userService;
//
//    /**
//	 * 通过id查找数据
//	 */
//    public HiCirculationDO get(String id){
//        return hiCirculationDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<HiCirculationDO> page(Page<HiCirculationDO> page, Map<String, Object> map){
//        return hiCirculationDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<HiCirculationDO> list(Map<String, Object> map){
//    	List<HiCirculationDO> circulationDOList = hiCirculationDao.list(map);
//    	List<HiCirculationDO> newCirculationDOList = new ArrayList<>();
//    	//获取发起人的id
//    	String createBy = circulationDOList.get(0).getUserIds();
//    	for (HiCirculationDO hiCirculationDO : circulationDOList) {
//			String[] types = hiCirculationDO.getTypes().split(",");
//			if (ConstantEnum.APPROVAL_NODE.equals(hiCirculationDO.getState())) {
//				String[] fileUrlsArray = solveString(hiCirculationDO.getFileUrls());
//				List<String> fileUrlsList = new ArrayList<>();
//				String[] commentsArray = solveString(hiCirculationDO.getComments());
//				List<String> commentsList = new ArrayList<>();
//				ProcessNodeDO processNodeDO = processNodeService.get(hiCirculationDO.getNodeId());
//				Set<String> strings = controlFlowService.getUserIdListByNode(processNodeDO, createBy);
//				List<String> allUsers = new ArrayList<>(strings);
//				String[] arrays = hiCirculationDO.getUserIds().split(",");
//				List<String> userNameList = new ArrayList<>();
//					if (allUsers.removeAll(Arrays.asList(arrays))){
//						for (int i = 0 ; i < arrays.length ; i++){
//							UserDO userDO = userService.get(arrays[i]);
//							userNameList.add(userDO.getName()+electType(types[i]));
//							fileUrlsList.add(fileUrlsArray.length == 0 ? "": (i == fileUrlsArray.length ? "" : fileUrlsArray[i]));
//							commentsList.add(commentsArray.length == 0 ? "": (i == commentsArray.length ? "" : commentsArray[i]));
//						}
//						if (!CollectionUtils.isEmpty(allUsers)){
//							for(int k = 0 ; k < allUsers.size() ; k++){
//								UserDO userDO = userService.get(allUsers.get(k));
//								if(userDO != null) {
//                                    userNameList.add(userDO.getName());
//                                }
//							}
//						}
//					}
//				String comments = StringUtils.join(commentsList.toArray(), ",");
//				hiCirculationDO.setTypeName(Arrays.asList(types).contains("5") ? "驳回" : "通过");
//				hiCirculationDO.setFileUrls(StringUtils.join(fileUrlsList.toArray(),","));
//				hiCirculationDO.setComments(comments);
//				hiCirculationDO.setUserNames(StringUtils.join(userNameList.toArray(), ","));
//				//对起始节点进行处理
//			}else if (ConstantEnum.START_NODE.equals(hiCirculationDO.getState())){
//				hiCirculationDO.setUserNames(userService.get(createBy).getName());
//				hiCirculationDO.setTypeName(electType(types[0]));
//			}else {
//				hiCirculationDO.setTypeName(electType(types[0]));
//			}
//			newCirculationDOList.add(hiCirculationDO);
//		}
//        return newCirculationDOList;
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(HiCirculationDO hiCirculation){
//        return super.save(hiCirculation);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return hiCirculationDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return hiCirculationDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         hiCirculationDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return hiCirculationDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<HiCirculationDO> hiCirculations){
//    	return hiCirculationDao.batchInsert(hiCirculations);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<HiCirculationDO> hiCirculations){
//		return hiCirculationDao.batchUpdate(hiCirculations);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	HiCirculationDO hiCirculation = hiCirculationDao.get(id);
//    	if (hiCirculation != null){
//    		hiCirculation.setId(null);
//    		hiCirculation.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiCirculation.getName())){
////    			hiCirculation.setName(hiCirculation.getName() + "-复制");
////    		}
//    		result = hiCirculationDao.insert(hiCirculation);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param hiCirculation 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(HiCirculationDO hiCirculation, Map<String,Object> whereMap){
//    	return hiCirculationDao.updateByWhere(hiCirculation, whereMap);
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
//    	return hiCirculationDao.removeByWhere(whereMap);
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
//		return hiCirculationDao.deleteByWhere(whereMap);
//	}
//
//	public int count(Map<String,Object> whereMap){
//		return hiCirculationDao.count(whereMap);
//	}
//
//	/**
//	 * 获取当前的流转历史
//	 * @param orderId 实例id
//	 * */
//	public HiCirculationDO getNowHiCirculationByOrder(String orderId){
//		return hiCirculationDao.getNowHiCirculationByOrder(orderId);
//	}
//
//	/**
//	 * 专门针对启动流程对流转历史表进行操作
//	 * @param processId
//	 * @param userId
//	 */
//	@Transactional(readOnly = false)
//	public void relateStartProcess(String processId, String userId, String orderId) {
//		//创建map用于筛选当前流程的开始节点
//		Map<String, Object> map = new HashMap<>();
//		map.put("processId", processId);
//		map.put("type", ConstantEnum.START_NODE);
//		String startNodeId = processNodeService.getStartNode(orderId).getId();
//		HiCirculationDO hiCirculationDO = new HiCirculationDO();
//		hiCirculationDO.setTaskName("发起人");
//		hiCirculationDO.setSort(0);
//		hiCirculationDO.setOrderId(orderId);
//		hiCirculationDO.setNodeId(startNodeId);
//		save(hiCirculationDO);
//		HiCirculationDetailedDO hiCirculationDetailedDO = new HiCirculationDetailedDO();
//		hiCirculationDetailedDO.setUserId(userId);
//		//操作类型 1= 开始
//		hiCirculationDetailedDO.setType(ConstantEnum.OPERATE_TYPE_START);
//		hiCirculationDetailedDO.setCirculationId(hiCirculationDO.getId());
//		hiCirculationDetailedService.save(hiCirculationDetailedDO);
//	}
//
//	/**
//	 * 1=开始、2=自动通过、3=抄送、4=通过、5=驳回、6=结束
//	 * @param type
//	 * @return
//	 */
//	public String electType(String type) {
//		String str = null;
//		switch (type) {
//			case "1":
//				str = "开始";
//				break;
//			case "2":
//				str = "自动通过";
//				break;
//			case "3":
//				str = "抄送";
//				break;
//			case "4":
//				str = "通过";
//				break;
//			case "5":
//				str = "驳回";
//				break;
//			case "6":
//				str = "结束";
//				break;
//		}
//		return str;
//	}
//
//	/**
//	 * 处理评论、上传附件、流程开始时间与流程结束时间
//	 * @param str
//	 * @return
//	 */
//	public String[] solveString(String str){
//		String[] pointArray;
//		if (StringUtils.isBlank(str)){
//			str = "";
//		}
//		if (str.contains(",")){
//			pointArray = StringUtils.split(str,",");
//		}else {
//			pointArray = new String[]{str};
//		}
//		return pointArray;
//	}
//
//
//}
