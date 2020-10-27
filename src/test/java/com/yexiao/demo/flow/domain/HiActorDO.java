//package com.yexiao.demo.flow.domain;
//
//import com.txdata.common.domain.DataEntity;
//import com.txdata.system.valid.ValidByData;
//
///**
// * 流程历史审批记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:01
// */
//public class HiActorDO extends DataEntity<HiActorDO> {
//	private static final long serialVersionUID = 1L;
//
//	@ValidByData(dataType="varchar",character="64")
//	private String taskId;  //
//	@ValidByData(dataType="varchar",character="500")
//	private String varable;  //
//	@ValidByData(dataType="varchar",character="500")
//	private String content;  //描述
//	@ValidByData(dataType="varchar",character="250")
//	private String fileUrl;  //
//
//    // 是否自动通过 0-不是 1-是
//    private String flag;
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }
//
//	public void setTaskId(String taskId) {
//		this.taskId = taskId;
//	}
//
//	public String getTaskId() {
//		return taskId;
//	}
//
//	public void setVarable(String varable) {
//		this.varable = varable;
//	}
//
//	public String getVarable() {
//		return varable;
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
//	public void setFileUrl(String fileUrl) {
//		this.fileUrl = fileUrl;
//	}
//
//	public String getFileUrl() {
//		return fileUrl;
//	}
//}
