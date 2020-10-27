//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程实例表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:58
// */
//public class OrderDO extends DataEntity<OrderDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String procId;  //
//	@ValidByData(dataType="char",character="0")
//	private String state;  //
//	private Integer sort;  //排序
//
//	public void setProcId(String procId) {
//		this.procId = procId;
//	}
//
//	public String getProcId() {
//		return procId;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public String getState() {
//		return state;
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
