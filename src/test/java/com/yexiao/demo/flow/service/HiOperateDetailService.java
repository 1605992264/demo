//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.HiOperateDetailDao;
//import com.txdata.flow.domain.HiOperateDetailDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程历史操作明细表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-07-07 11:10:28
// */
// @Service
//public class HiOperateDetailService extends CrudService<HiOperateDetailDao, HiOperateDetailDO> {
//
//    @Autowired
//    private HiOperateDetailDao hiOperateDetailDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public HiOperateDetailDO get(String id){
//        return hiOperateDetailDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<HiOperateDetailDO> page(Page<HiOperateDetailDO> page, Map<String, Object> map){
//        return hiOperateDetailDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<HiOperateDetailDO> list(Map<String, Object> map){
//        return hiOperateDetailDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(HiOperateDetailDO hiOperateDetail){
//        return super.save(hiOperateDetail);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return hiOperateDetailDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return hiOperateDetailDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public void delete(String id){
//        hiOperateDetailDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return hiOperateDetailDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<HiOperateDetailDO> hiOperateDetails){
//    	return hiOperateDetailDao.batchInsert(hiOperateDetails);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<HiOperateDetailDO> hiOperateDetails){
//		return hiOperateDetailDao.batchUpdate(hiOperateDetails);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	HiOperateDetailDO hiOperateDetail = hiOperateDetailDao.get(id);
//    	if (hiOperateDetail != null){
//    		hiOperateDetail.setId(null);
//    		hiOperateDetail.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(hiOperateDetail.getName())){
////    			hiOperateDetail.setName(hiOperateDetail.getName() + "-复制");
////    		}
//    		result = hiOperateDetailDao.insert(hiOperateDetail);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param hiOperateDetail 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(HiOperateDetailDO hiOperateDetail, Map<String,Object> whereMap){
//    	return hiOperateDetailDao.updateByWhere(hiOperateDetail, whereMap);
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
//    	return hiOperateDetailDao.removeByWhere(whereMap);
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
//		return hiOperateDetailDao.deleteByWhere(whereMap);
//	}
//}
