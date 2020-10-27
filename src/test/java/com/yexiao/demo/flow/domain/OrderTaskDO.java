//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程实例执行表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:07
// */
//public class OrderTaskDO extends DataEntity<OrderTaskDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String orderId;  //
//	@ValidByData(dataType="varchar",character="124")
//	private String taskName;  //
//	@ValidByData(dataType="varchar",character="124")
//	private String displayName;  //
//	@ValidByData(dataType="varchar",character="64")
//	private String nodeId;  //
//	@ValidByData(dataType="char",character="0")
//	private String state;  //
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
//	public void setDisplayName(String displayName) {
//		this.displayName = displayName;
//	}
//
//	public String getDisplayName() {
//		return displayName;
//	}
//
//    public String getNodeId() {
//        return nodeId;
//    }
//
//    public void setNodeId(String nodeId) {
//        this.nodeId = nodeId;
//    }
//
//    public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getState() {
//		return state;
//	}
//}
