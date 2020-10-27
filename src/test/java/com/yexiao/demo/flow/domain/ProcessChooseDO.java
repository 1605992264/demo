//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程发起人自选人员表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-02 15:13:15
// */
//public class ProcessChooseDO extends DataEntity<ProcessChooseDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String nodeId;  //节点id
//	@ValidByData(dataType="varchar",character="500")
//	private String value;  //值 一般是id
//	@ValidByData(dataType="char",character="0")
//	private String type;  // 类型 1用户2部门领导3角色4ALL
//	private Integer sort;  //排序
//	@ValidByData(dataType="varchar",character="64")
//	private String orderId;  //流程实例id
//
//	public void setNodeId(String nodeId) {
//		this.nodeId = nodeId;
//	}
//
//	public String getNodeId() {
//		return nodeId;
//	}
//
//	public void setValue(String value) {
//		this.value = value;
//	}
//
//	public String getValue() {
//		return value;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setOrderId(String orderId) {
//		this.orderId = orderId;
//	}
//
//	public String getOrderId() {
//		return orderId;
//	}
//}
