//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//
//
///**
// * 流程节点表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:26
// */
//public class ProcessNodeDO extends DataEntity<ProcessNodeDO> {
//	private static final long serialVersionUID = 1L;
//
//
//	private String name;  //名称
//
//	private String displayName;  //
//
//	private String processId;  //
//
//	private String prevId;  //父级节点
//
//	private String isBranch;  //是否存在支线
//
//	private String type;  //类型：1开始节点、2审批节点、3条件节点、4抄送节点、9结束节点
//
//	private String auditType;  //审批类型:1指定用户、2角色、3部门
//
//	private String counterSign;  //审批方式1或签、2会签、3依次审批
//
//	private String rejectType;  //驳回类型 驳回至1 父节点、2发起人节点
//	private Integer priority;  //
//	private Integer sort;  //排序
//
//
//
//    private String orderId; // 实例id
//    private String initiator; // 发起人
//
//    // 表单权限
//    private String formRole;
//
//    public String getInitiator() {
//        return initiator;
//    }
//
//    public void setInitiator(String initiator) {
//        this.initiator = initiator;
//    }
//
//    public String getFormRole() {
//        return formRole;
//    }
//
//    public void setFormRole(String formRole) {
//        this.formRole = formRole;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
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
//	public void setProcessId(String processId) {
//		this.processId = processId;
//	}
//
//	public String getProcessId() {
//		return processId;
//	}
//
//	public void setPrevId(String prevId) {
//		this.prevId = prevId;
//	}
//
//	public String getPrevId() {
//		return prevId;
//	}
//
//	public void setIsBranch(String isBranch) {
//		this.isBranch = isBranch;
//	}
//
//	public String getIsBranch() {
//		return isBranch;
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
//	public void setAuditType(String auditType) {
//		this.auditType = auditType;
//	}
//
//	public String getAuditType() {
//		return auditType;
//	}
//
//	public void setCounterSign(String counterSign) {
//		this.counterSign = counterSign;
//	}
//
//	public String getCounterSign() {
//		return counterSign;
//	}
//
//	public void setRejectType(String rejectType) {
//		this.rejectType = rejectType;
//	}
//
//	public String getRejectType() {
//		return rejectType;
//	}
//
//	public void setPriority(Integer priority) {
//		this.priority = priority;
//	}
//
//	public Integer getPriority() {
//		return priority;
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
//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof ProcessNodeDO){
//            if(((ProcessNodeDO) obj).getId().equals(this.getId()))
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return id.length();
//    }
//}
