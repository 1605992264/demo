//package com.yexiao.demo.flow.dao;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.FlowProcessDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程定义表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:11:14
// */
//@Mapper
//public interface FlowProcessDao extends CrudDao<FlowProcessDO> {
//
//	FlowProcessDO form(@Param("processCode") String processCode, @Param("version") int version);
//
//	Page<FlowProcessDO> list(Page<FlowProcessDO> page, @Param("entity") Map<String, Object> map);
//
//	List<FlowProcessDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(FlowProcessDO process);
//
//	int update(FlowProcessDO process);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<FlowProcessDO> processs);
//
//	int batchUpdate(List<FlowProcessDO> processs);
//
//	int updateByWhere(@Param("param") FlowProcessDO process, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//	/**
//	 * 通过流程编号查询最新版本的流程
//	 * @param processCode
//	 * @return
//	 */
//	FlowProcessDO queryMaxVersionInfo(@Param("processCode") String processCode);
//
//	/**
//	 * 按照指定的返回格式查询所有的流程信息
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	Page<JSONObject> queryAllProcess(Page<JSONObject> page, @Param("entity") Map<String, Object> map);
//
//	/**
//	 * 查看历史版本流程(单个)
//	 * @param map
//	 * @return
//	 */
//	JSONObject queryHisProcess(@Param("entity") Map<String, Object> map);
//
//	/**
//	 * 查看历史版本流程(分页)
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	Page<JSONObject> queryHisProcess(Page<JSONObject> page, @Param("entity") Map<String, Object> map);
//
//	/**
//	 * 根据流程标识查询最新的流程id
//	 * @param processCode
//	 * @return
//	 */
//	String queryNewIdByCode(@Param("processCode") String processCode);
//
//
//	FlowProcessDO get(String id);
//
//	/**
//	 * 根据流程id查询流程标识最大的版本号
//	 * @param processId
//	 * @return
//	 */
//	int queryMaxVersion(@Param("processId") String processId);
//
//	/**
//	 * 修改流程状态为“发布”状态
//	 * @param processCode
//	 * @param version
//	 * @return
//	 */
//	int updateProcessState(@Param("processCode") String processCode, @Param("version") String version);
//
//	/**
//	 * 修改其他流程状态为“草稿”状态
//	 * @param processCode
//	 * @return
//	 */
//	int updateOtherProcessState(@Param("processCode") String processCode);
//}
