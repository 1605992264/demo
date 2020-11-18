//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.HiCirculationDetailedDao;
//import com.txdata.flow.domain.HiCirculationDetailedDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程流转历史操作人表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-08-04 09:57:03
// */
// @Service
//public class HiCirculationDetailedService extends CrudService<HiCirculationDetailedDao, HiCirculationDetailedDO> {
//
//    @Autowired
//    private HiCirculationDetailedDao hiCirculationDetailedDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public HiCirculationDetailedDO get(String id){
//        return hiCirculationDetailedDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<HiCirculationDetailedDO> page(Page<HiCirculationDetailedDO> page, Map<String, Object> map){
//        return hiCirculationDetailedDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<HiCirculationDetailedDO> list(Map<String, Object> map){
//        return hiCirculationDetailedDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(HiCirculationDetailedDO hiCirculationDetailed){
//        return super.save(hiCirculationDetailed);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return hiCirculationDetailedDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return hiCirculationDetailedDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int delete(String id){
//        return hiCirculationDetailedDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return hiCirculationDetailedDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<HiCirculationDetailedDO> hiCirculationDetaileds){
//    	return hiCirculationDetailedDao.batchInsert(hiCirculationDetaileds);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<HiCirculationDetailedDO> hiCirculationDetaileds){
//		return hiCirculationDetailedDao.batchUpdate(hiCirculationDetaileds);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	HiCirculationDetailedDO hiCirculationDetailed = hiCirculationDetailedDao.get(id);
//    	if (hiCirculationDetailed != null){
//    		hiCirculationDetailed.setId(null);
//    		hiCirculationDetailed.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiCirculationDetailed.getName())){
////    			hiCirculationDetailed.setName(hiCirculationDetailed.getName() + "-复制");
////    		}
//    		result = hiCirculationDetailedDao.insert(hiCirculationDetailed);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param hiCirculationDetailed 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(HiCirculationDetailedDO hiCirculationDetailed, Map<String,Object> whereMap){
//    	return hiCirculationDetailedDao.updateByWhere(hiCirculationDetailed, whereMap);
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
//    	return hiCirculationDetailedDao.removeByWhere(whereMap);
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
//		return hiCirculationDetailedDao.deleteByWhere(whereMap);
//	}
//}
