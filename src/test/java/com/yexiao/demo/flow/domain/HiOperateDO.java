//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流转历史操作表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:09:15
// */
//public class HiOperateDO extends DataEntity<HiOperateDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String nodeId;  //主键
//	@ValidByData(dataType="varchar",character="64")
//	private String userId;  //操作人id
//	@ValidByData(dataType="char",character="0")
//	private String operateType;  //操作类型(1=开始、2=提交、3=抄送、4=通过、5=驳回、6=结束)
//	@ValidByData(dataType="varchar",character="1024")
//	private String approvalAdvice;  //审批意见
//	private String hisFilePath;  //附件
//	@ValidByData(dataType="varchar",character="64")
//	private String caseId;  //流程实例id
//    private Integer sort; //排序
//
//	//辅助字段
//	private String userName;   //用户名称
//	private String operateName;  //操作类型名称
//	private String nodeName;   //节点名称
//	private String nextApproves; //下一个审核人(有可能有多个，以逗号分隔)
//	private String nodeType;   //节点类型
//
//	public String getNodeType() {
//		return nodeType;
//	}
//
//	public void setNodeType(String nodeType) {
//		this.nodeType = nodeType;
//	}
//
//	public String getNextApproves() {
//		return nextApproves;
//	}
//
//	public void setNextApproves(String nextApproves) {
//		this.nextApproves = nextApproves;
//	}
//
//	public Integer getSort() {
//        return sort;
//    }
//
//    public void setSort(Integer sort) {
//        this.sort = sort;
//    }
//
//    public String getNodeName() {
//		return nodeName;
//	}
//
//	public void setNodeName(String nodeName) {
//		this.nodeName = nodeName;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getOperateName() {
//		return operateName;
//	}
//
//	public void setOperateName(String operateName) {
//		this.operateName = operateName;
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
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setOperateType(String operateType) {
//		this.operateType = operateType;
//	}
//
//	public String getOperateType() {
//		return operateType;
//	}
//
//	public void setApprovalAdvice(String approvalAdvice) {
//		this.approvalAdvice = approvalAdvice;
//	}
//
//	public String getApprovalAdvice() {
//		return approvalAdvice;
//	}
//
//	public String getHisFilePath() {
//		return hisFilePath;
//	}
//
//	public void setHisFilePath(String hisFilePath) {
//		this.hisFilePath = hisFilePath;
//	}
//
//	public void setCaseId(String caseId) {
//		this.caseId = caseId;
//	}
//
//	public String getCaseId() {
//		return caseId;
//	}
//}
