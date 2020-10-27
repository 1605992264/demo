//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.HiOperateDao;
//import com.txdata.flow.domain.HiOperateDO;
//import com.txdata.flow.domain.ProcessNodeDO;
//import com.txdata.flow.utils.ConstantEnum;
//import com.txdata.system.domain.UserDO;
//import com.txdata.system.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
///**
// * 流转历史操作表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:09:15
// */
// @Service
//public class HiOperateService extends CrudService<HiOperateDao, HiOperateDO> {
//
//	@Autowired
//	private HiOperateDao hiOperateDao;
//	@Autowired
//	private OrderService orderService;
//	@Autowired
//	private ProcessNodeService processNodeService;
//	@Autowired
//	private ControlFlowService controlFlowService;
//	@Autowired
//	private UserService userService;
//
//
//	/**
//	 * 通过id查找数据
//	 */
//	public HiOperateDO get(String id) {
//		return hiOperateDao.get(id);
//	}
//
//	/**
//	 * 分页查询列表
//	 */
//	public Page<HiOperateDO> page(Page<HiOperateDO> page, Map<String, Object> map) {
//		return hiOperateDao.list(page, map);
//	}
//
//	/**
//	 * 查询列表
//	 */
//	public List<HiOperateDO> list(Map<String, Object> map) {
//		return hiOperateDao.list(map);
//	}
//
//	/**
//	 * 保存/修改
//	 */
//	@Transactional(readOnly = false)
//	public int save(HiOperateDO hiOperate) {
//		return super.save(hiOperate);
//	}
//
//	/**
//	 * 通过id逻辑删除
//	 */
//	@Transactional(readOnly = false)
//	public int remove(String id) {
//		return hiOperateDao.remove(id);
//	}
//
//	/**
//	 * 通过ids批量逻辑删除
//	 */
//	@Transactional(readOnly = false)
//	public int batchRemove(String[] ids) {
//		return hiOperateDao.batchRemove(ids);
//	}
//
//	/**
//	 * 通过id物理删除
//	 */
//	@Transactional(readOnly = false)
//	public void delete(String id) {
//		 hiOperateDao.delete(id);
//	}
//
//	/**
//	 * 通过ids物理删除
//	 */
//	@Transactional(readOnly = false)
//	public int batchDelete(String[] ids) {
//		return hiOperateDao.batchDelete(ids);
//	}
//
//	/**
//	 * 批量插入
//	 */
//	@Transactional(readOnly = false)
//	public int batchInsert(List<HiOperateDO> hiOperates) {
//		return hiOperateDao.batchInsert(hiOperates);
//	}
//
//	/**
//	 * 批量修改
//	 */
//	@Transactional(readOnly = false)
//	public int batchUpdate(List<HiOperateDO> hiOperates) {
//		return hiOperateDao.batchUpdate(hiOperates);
//	}
//
//	/**
//	 * 通过id复制一条相同的数据
//	 */
//	@Transactional(readOnly = false)
//	public int copy(String id) {
//		int result = 0;
//		HiOperateDO hiOperate = hiOperateDao.get(id);
//		if (hiOperate != null) {
//			hiOperate.setId(null);
//			hiOperate.preInsert();
//			//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiOperate.getName())){
////    			hiOperate.setName(hiOperate.getName() + "-复制");
////    		}
//			result = hiOperateDao.insert(hiOperate);
//		}
//		return result;
//	}
//
//	/**
//	 * @param hiOperate 要被修改的参数
//	 * @param whereMap  修改条件
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly = false)
//	public int updateByWhere(HiOperateDO hiOperate, Map<String, Object> whereMap) {
//		return hiOperateDao.updateByWhere(hiOperate, whereMap);
//	}
//
//	/**
//	 * @param whereMap 逻辑删除条件
//	 * @Description: 逻辑删除（通过自定义的条件进行逻辑删除操作）
//	 * @return: 返回逻辑删除数量
//	 */
//	@Transactional(readOnly = false)
//	public int removeByWhere(Map<String, Object> whereMap) {
//		return hiOperateDao.removeByWhere(whereMap);
//	}
//
//	/**
//	 * @param whereMap 物理删除条件
//	 * @Description: 物理删除（通过自定义的条件进行物理删除操作）慎用
//	 * @return: 返回物理删除数量
//	 */
//	@Transactional(readOnly = false)
//	public int deleteByWhere(Map<String, Object> whereMap) {
//		return hiOperateDao.deleteByWhere(whereMap);
//	}
//
//	/**
//	 * 专门针对启动流程对流转历史表进行操作
//	 * @param processId
//	 * @param userId
//	 */
//	public void relateStartProcess(String processId, String userId, String orderId) {
//		//创建map用于筛选当前流程的开始节点
//		Map<String, Object> map = new HashMap<>();
//		map.put("processId", processId);
//		map.put("type", ConstantEnum.START_NODE);
//		String startNodeId = processNodeService.list(map).get(0).getId();
//		HiOperateDO hiOperateDO = new HiOperateDO();
//		hiOperateDO.setUserId(userId);
//		//操作类型 1= 开始
//		hiOperateDO.setOperateType(ConstantEnum.OPERATE_TYPE_START);
//		hiOperateDO.setNodeId(startNodeId);
//		hiOperateDO.setCaseId(orderId);
//		save(hiOperateDO);
//	}
//
//	/**
//	 * 查询指定流程实例的流转历史操作列表
//	 * (分页)
//	 *
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	public Page<HiOperateDO> queryPointProcessHistory(Page<HiOperateDO> page, Map<String, Object> map) {
//		return hiOperateDao.queryPointProcessHistory(page, map);
//	}
//
//	/**
//	 * 查询指定流程实例的流转历史操作列表
//	 * (列表)
//	 * @param map
//	 * @return
//	 */
//	public List<HiOperateDO> queryPointProcessHistory(Map<String, Object> map) {
//		List<HiOperateDO> newHiOperateDOList = new ArrayList<>();
//		List<HiOperateDO> hiOperateDOList = hiOperateDao.queryPointProcessHistory(map);
//		//获取发起人id
//		String createBy = hiOperateDOList.get(0).getUserId();
//		for (HiOperateDO hiOperateDO : hiOperateDOList){
//			//判断当前节点是否为审批节点
//			if (ConstantEnum.APPROVAL_NODE.equals(hiOperateDO.getNodeType())){
//				 List<String> userNameList = new ArrayList<>();
//				 ProcessNodeDO processNodeDO = processNodeService.get(hiOperateDO.getNodeId());
//				 Set<String> userSet = controlFlowService.getUserIdListByNode(processNodeDO,createBy);
//				 for (String str : userSet){
//				 	 if (str.equals(hiOperateDO.getUserId())){
//				 	 	UserDO newUserDO =userService.get(hiOperateDO.getUserId());
//						 userNameList.add(newUserDO.getName()+"(审核)");
//					 }else {
//						 UserDO userDO = userService.get(str);
//						 userNameList.add(userDO.getName());
//					 }
//				 }
//				 String userName = StringUtils.join(userNameList.toArray(),",");
//				hiOperateDO.setUserName(userName);
//				newHiOperateDOList.add(hiOperateDO);
//			}else {
//				newHiOperateDOList.add(hiOperateDO);
//			}
//		}
//		return newHiOperateDOList;
//	}
//
//
//	/**
//     * 计数
//     * */
//	public int count(Map<String, Object> map){
//	    return hiOperateDao.count(map);
//    }
//}
