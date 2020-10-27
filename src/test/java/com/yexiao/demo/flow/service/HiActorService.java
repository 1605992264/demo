//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.HiActorDao;
//import com.txdata.flow.domain.HiActorDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程历史审批记录表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:10:01
// */
// @Service
//public class HiActorService extends CrudService<HiActorDao, HiActorDO> {
//
//    @Autowired
//    private HiActorDao hiActorDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public HiActorDO get(String id){
//        return hiActorDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<HiActorDO> page(Page<HiActorDO> page, Map<String, Object> map){
//        return hiActorDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<HiActorDO> list(Map<String, Object> map){
//        return hiActorDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(HiActorDO hiActor){
//        return super.save(hiActor);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return hiActorDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return hiActorDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//         hiActorDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return hiActorDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<HiActorDO> hiActors){
//    	return hiActorDao.batchInsert(hiActors);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<HiActorDO> hiActors){
//		return hiActorDao.batchUpdate(hiActors);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	HiActorDO hiActor = hiActorDao.get(id);
//    	if (hiActor != null){
//    		hiActor.setId(null);
//    		hiActor.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiActor.getName())){
////    			hiActor.setName(hiActor.getName() + "-复制");
////    		}
//    		result = hiActorDao.insert(hiActor);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param hiActor 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(HiActorDO hiActor, Map<String,Object> whereMap){
//    	return hiActorDao.updateByWhere(hiActor, whereMap);
//    }
//
//    /**
//	 *
//	 * @Description: 逻辑删除（通过自定义的条件进行逻辑删除操作）
//	 * @param whereMap 逻辑删除条件
//	 * @return: 返回逻辑删除数量
//	 */
//	@Transactional(readOnly=false)
//    public int removeByWhere(Map<String,Object> whereMap){
//    	return hiActorDao.removeByWhere(whereMap);
//    }
//
//	/**
//	 *
//	 * @Description: 物理删除（通过自定义的条件进行物理删除操作）慎用
//	 * @param whereMap 物理删除条件
//	 * @return: 返回物理删除数量
//	 */
//	@Transactional(readOnly=false)
//	public int deleteByWhere(Map<String,Object> whereMap){
//		return hiActorDao.deleteByWhere(whereMap);
//	}
//}
