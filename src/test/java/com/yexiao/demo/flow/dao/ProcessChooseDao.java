//package com.yexiao.demo.flow.dao;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.proxy.CrudDao;
//import com.txdata.flow.domain.ProcessChooseDO;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程发起人自选人员表
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-02 15:13:15
// */
//@Mapper
//public interface ProcessChooseDao extends CrudDao<ProcessChooseDO> {
//
//	ProcessChooseDO get(String id);
//
//	Page<ProcessChooseDO> list(Page<ProcessChooseDO> page, @Param("entity") Map<String, Object> map);
//
//	List<ProcessChooseDO> list(@Param("entity") Map<String, Object> map);
//
//	int insert(ProcessChooseDO processChoose);
//
//	int update(ProcessChooseDO processChoose);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
//
//	int delete(String id);
//
//	int batchDelete(String[] ids);
//
//	int batchInsert(List<ProcessChooseDO> processChooses);
//
//	int batchUpdate(List<ProcessChooseDO> processChooses);
//
//	int updateByWhere(@Param("param") ProcessChooseDO processChoose, @Param("where") Map<String, Object> whereMap);
//
//	int removeByWhere(@Param("where") Map<String, Object> whereMap);
//
//	int deleteByWhere(@Param("where") Map<String, Object> whereMap);
//
//    int count(@Param("where") Map<String, Object> whereMap);
//
//    /**
//     * 查询未审批的人
//     * @param nodeId 节点id
//     * @param actorIdList 已审批了的id
//     * */
//    List<ProcessChooseDO> findOtherActor(@Param("actorIdList") List<String> actorIdList, @Param("nodeId") String nodeId);
//}
