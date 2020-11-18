//package com.yexiao.demo.flow.domain;
//
//import com.google.common.collect.Lists;
//import com.txdata.common.domain.DataEntity;
//
//import java.util.List;
//
//
///**
// * 第三方用户表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:33:54
// */
//public class FuserDO extends DataEntity<FuserDO> {
//	private static final long serialVersionUID = 1L;
//
//            private String username;  //用户名（英文）
//            private String userId;  //用户id
//            private String name;  //用户名
//            private String officeId;  //归属部门
//            private String systemCode;  //系统编码
//
//			//角色id
//			private List<String> roleIdList;
//			private List<FroleDO> roleList = Lists.newArrayList(); // 拥有角色列表
//
//			//机构
//			private String officeName;
//
//	public String getOfficeName() {
//		return officeName;
//	}
//
//	public void setOfficeName(String officeName) {
//		this.officeName = officeName;
//	}
//
//	public List<FroleDO> getRoleList() {
//		return roleList;
//	}
//
//	public void setRoleList(List<FroleDO> roleList) {
//		this.roleList = roleList;
//	}
//
//	public List<String> getRoleIdList() {
//		return roleIdList;
//	}
//
//	public void setRoleIdList(List<String> roleIdList) {
//		this.roleIdList = roleIdList;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getUsername() {
//		return username;
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
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setOfficeId(String officeId) {
//		this.officeId = officeId;
//	}
//
//	public String getOfficeId() {
//		return officeId;
//	}
//
//	public void setSystemCode(String systemCode) {
//		this.systemCode = systemCode;
//	}
//
//	public String getSystemCode() {
//		return systemCode;
//	}
//	                    }
