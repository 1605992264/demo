//package com.yexiao.demo.flow.domain;
//
//import java.io.Serializable;
//import java.util.List;
//
///**
// * @Author xuzj
// * @Description
// * @Date 2020/11/13 15:47
// */
//public class FajaxOffice implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private String id;
//    private String parentId;
//    private String parentName;
//    private String areaId;
//    private String areaName;
//    // 机构编码
//    private String code;
//    // 机构名称
//    private String name;
//    // 是否可用
//    private String useable;
//    // 统一社会信用代码 18位
//    private String uscreditCode;
//    // 用户还是机构，用户为0，机构为1
//    private String type;
//    // 公司印章图片地址
//    private String sealPath;
//    // 水政监察印章图片地址
//    private String szjcSealPath;
//    private List<FajaxOffice> children;
//    private String leader; // 分管领导
//    private String principal; // 部门负责人
//    private String leaderName; // 分管领导名称
//    private String principalName; // 机构负责人名称
//
//    /**
//     * 用于机构用户树中保存用户信息
//     */
//    private String officeId;
//    private String officeName;
//    private String position;
//    private String mobile;
//    private String email;
//    private String userStatus;
//
//    public FajaxOffice() {
//    }
//
//    public FajaxOffice(FuserDO user) {
//        this.id = user.getUserId();
//        this.name = user.getName();
//        this.type = "0";
//        this.officeName = user.getOfficeName();
//        this.officeId = user.getOfficeId();
//    }
//
//    public FajaxOffice(FofficeDO o, boolean isAll) {
//        this.id = o.getOfficeId();
//        this.name = o.getName();
//        this.type = "1";
//        if (isAll) {
//            this.parentId = o.getParentId();
//        }
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(String parentId) {
//        this.parentId = parentId;
//    }
//
//    public String getParentName() {
//        return parentName;
//    }
//
//    public void setParentName(String parentName) {
//        this.parentName = parentName;
//    }
//
//    public String getAreaId() {
//        return areaId;
//    }
//
//    public void setAreaId(String areaId) {
//        this.areaId = areaId;
//    }
//
//    public String getAreaName() {
//        return areaName;
//    }
//
//    public void setAreaName(String areaName) {
//        this.areaName = areaName;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getUseable() {
//        return useable;
//    }
//
//    public void setUseable(String useable) {
//        this.useable = useable;
//    }
//
//    public String getUscreditCode() {
//        return uscreditCode;
//    }
//
//    public void setUscreditCode(String uscreditCode) {
//        this.uscreditCode = uscreditCode;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public List<FajaxOffice> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<FajaxOffice> children) {
//        this.children = children;
//    }
//
//    public String getSealPath() {
//        return sealPath;
//    }
//
//    public void setSealPath(String sealPath) {
//        this.sealPath = sealPath;
//    }
//
//    public String getSzjcSealPath() {
//        return szjcSealPath;
//    }
//
//    public void setSzjcSealPath(String szjcSealPath) {
//        this.szjcSealPath = szjcSealPath;
//    }
//
//    public String getLeader() {
//        return leader;
//    }
//
//    public void setLeader(String leader) {
//        this.leader = leader;
//    }
//
//    public String getPrincipal() {
//        return principal;
//    }
//
//    public void setPrincipal(String principal) {
//        this.principal = principal;
//    }
//
//    public String getLeaderName() {
//        return leaderName;
//    }
//
//    public void setLeaderName(String leaderName) {
//        this.leaderName = leaderName;
//    }
//
//    public String getPrincipalName() {
//        return principalName;
//    }
//
//    public void setPrincipalName(String principalName) {
//        this.principalName = principalName;
//    }
//
//    public String getOfficeName() {
//        return officeName;
//    }
//
//    public void setOfficeName(String officeName) {
//        this.officeName = officeName;
//    }
//
//    public String getPosition() {
//        return position;
//    }
//
//    public void setPosition(String position) {
//        this.position = position;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getUserStatus() {
//        return userStatus;
//    }
//
//    public void setUserStatus(String userStatus) {
//        this.userStatus = userStatus;
//    }
//
//    public String getOfficeId() {
//        return officeId;
//    }
//
//    public void setOfficeId(String officeId) {
//        this.officeId = officeId;
//    }
//}
//
