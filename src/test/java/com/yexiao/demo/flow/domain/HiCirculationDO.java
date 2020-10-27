//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程实例历史记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:54:44
// */
//public class HiCirculationDO extends DataEntity<HiCirculationDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String orderId;  //实例id
//	@ValidByData(dataType="varchar",character="124")
//	private String taskName;  //任务名称
//	@ValidByData(dataType="varchar",character="64")
//	private String nodeId;  //节点id
//	private Integer sort;  //排序
//
//	//辅助字段
//	private String userIds;
//	private String userNames;
//	private String types;
//	private String comments;
//	private String fileUrls;
//	private String state;   //节点类型
//	private String typeName;
//	private String counterSign;  //(1 = 或签 , 2 = 会签)
//
//	public String getCounterSign() {
//		return counterSign;
//	}
//
//	public void setCounterSign(String counterSign) {
//		this.counterSign = counterSign;
//	}
//
//	public String getTypeName() {
//		return typeName;
//	}
//
//	public void setTypeName(String typeName) {
//		this.typeName = typeName;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getUserIds() {
//		return userIds;
//	}
//
//	public void setUserIds(String userIds) {
//		this.userIds = userIds;
//	}
//
//	public String getUserNames() {
//		return userNames;
//	}
//
//	public void setUserNames(String userNames) {
//		this.userNames = userNames;
//	}
//
//	public String getTypes() {
//		return types;
//	}
//
//	public void setTypes(String types) {
//		this.types = types;
//	}
//
//	public String getComments() {
//		return comments;
//	}
//
//	public void setComments(String comments) {
//		this.comments = comments;
//	}
//
//	public String getFileUrls() {
//		return fileUrls;
//	}
//
//	public void setFileUrls(String fileUrls) {
//		this.fileUrls = fileUrls;
//	}
//
//	public void setOrderId(String orderId) {
//		this.orderId = orderId;
//	}
//
//	public String getOrderId() {
//		return orderId;
//	}
//
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
//
//	public String getTaskName() {
//		return taskName;
//	}
//
//	public void setNodeId(String nodeId) {
//		this.nodeId = nodeId;
//	}
//
//	public String getNodeId() {
//		return nodeId;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//}
