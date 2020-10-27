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
// * @date 2020-06-29 14:10:46
// */
//public class HiTaskDO extends DataEntity<HiTaskDO> {
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
//    private Integer sort; // 排序号
//    private String flag; // 是否为自动通过 0-不是 1-是
//
//    public Integer getSort() {
//        return sort;
//    }
//
//    public void setSort(Integer sort) {
//        this.sort = sort;
//    }
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//    public void setOrderId(String orderId) {
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
