//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程历史操作明细表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:10:28
// */
//public class HiOperateDetailDO extends DataEntity<HiOperateDetailDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String operateId;  //流转历史主表id
//	@ValidByData(dataType="varchar",character="64")
//	private String userId;  //抄送人id
//
//	public void setOperateId(String operateId) {
//		this.operateId = operateId;
//	}
//
//	public String getOperateId() {
//		return operateId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//}
