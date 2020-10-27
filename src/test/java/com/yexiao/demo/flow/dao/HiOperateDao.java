//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.HiOperateDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流转历史操作表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:09:15
// */
//@Mapper
//public interface HiOperateDao extends CrudDao<HiOperateDO> {
//
//	HiOperateDO get(String id);
//
//	Page<HiOperateDO> list(Page<HiOperateDO> page, @Param("entity") Map<String, Object> map);
//
//	List<HiOperateDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(HiOperateDO hiOperate);
//
//	int update(HiOperateDO hiOperate);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<HiOperateDO> hiOperates);
//
//	int batchUpdate(List<HiOperateDO> hiOperates);
//
//	int updateByWhere(@Param("param") HiOperateDO hiOperate, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//    /**
//     * 根据用户i查询已审批的节点
//     */
//	String findApprovedNodeByUserId(String userId);
//
//	/**
//	 * 查询指定流程实例的流转历史操作列表
//	 * (列表)
//	 * @param map
//	 * @return
//	 */
//	List<HiOperateDO> queryPointProcessHistory(@Param("entity") Map<String, Object> map);
//
//	/**
//	 * 查询指定流程实例的流转历史操作列表
//	 * (分页)
//	 * @param page
//	 * @param map
//	 * @return
//	 */
//	Page<HiOperateDO> queryPointProcessHistory(Page<HiOperateDO> page, @Param("entity") Map<String, Object> map);
//
//
//	/**
//     * 计数
//     * */
//	int count(@Param("where") Map<String, Object> whereMap);
//}
