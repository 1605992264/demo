//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//
//
///**
// * 流程定义表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:14
// */
//public class FlowProcessDO extends DataEntity<FlowProcessDO> {
//	private static final long serialVersionUID = 1L;
//
//	private String name;  //名称
//	private String state;  //状态 1发布、0停用
//	private String content;  //
//	private Integer version;  //
//	private String original;  //原始格式
//	private Integer sort;  //排序
//	private String flowImg;   //图片路径
//	private String flowGroup;  //分组
//	//高级设置
//	private String autoRepeat;  //审批人去重 (1 = 去重 0 = 不去重 )
//	private String myAuditAutoPass;  //发起人审批时自动通过 (1= 自动 0 = 不自动)
//	private String notVisibleForSponsor;  //审批意见是否必填 (1= 必填 0 = 不必填)
//	private String remarkRequired;  //审批意见对发起人是否可见(1 = 可见 0 = 不可见)
//	private String remarkTip;  //审批意见填写提示
//	private String processCode;  //流程编号
//
//	public String getProcessCode() {
//		return processCode;
//	}
//
//	public void setProcessCode(String processCode) {
//		this.processCode = processCode;
//	}
//
//	public String getAutoRepeat() {
//		return autoRepeat;
//	}
//
//	public void setAutoRepeat(String autoRepeat) {
//		this.autoRepeat = autoRepeat;
//	}
//
//	public String getMyAuditAutoPass() {
//		return myAuditAutoPass;
//	}
//
//	public void setMyAuditAutoPass(String myAuditAutoPass) {
//		this.myAuditAutoPass = myAuditAutoPass;
//	}
//
//	public String getNotVisibleForSponsor() {
//		return notVisibleForSponsor;
//	}
//
//	public void setNotVisibleForSponsor(String notVisibleForSponsor) {
//		this.notVisibleForSponsor = notVisibleForSponsor;
//	}
//
//	public String getRemarkRequired() {
//		return remarkRequired;
//	}
//
//	public void setRemarkRequired(String remarkRequired) {
//		this.remarkRequired = remarkRequired;
//	}
//
//	public String getRemarkTip() {
//		return remarkTip;
//	}
//
//	public void setRemarkTip(String remarkTip) {
//		this.remarkTip = remarkTip;
//	}
//
//	public String getFlowImg() {
//		return flowImg;
//	}
//
//	public void setFlowImg(String flowImg) {
//		this.flowImg = flowImg;
//	}
//
//	public String getFlowGroup() {
//		return flowGroup;
//	}
//
//	public void setFlowGroup(String flowGroup) {
//		this.flowGroup = flowGroup;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
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
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
//
//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setOriginal(String original) {
//		this.original = original;
//	}
//
//	public String getOriginal() {
//		return original;
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
