//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.FormReloDao;
//import com.txdata.flow.domain.FormReloDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 流程节点表单权限表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-06-29 14:07:26
// */
// @Service
//public class FormReloService extends CrudService<FormReloDao, FormReloDO> {
//
//    @Autowired
//    private FormReloDao formReloDao;
//    /**
//	 * 通过id查找数据
//	 */
//    public FormReloDO get(String id){
//        return formReloDao.get(id);
//    }
//
//    /**
//	 * 分页查询列表
//	 */
//    public Page<FormReloDO> page(Page<FormReloDO> page, Map<String, Object> map){
//        return formReloDao.list(page, map);
//    }
//
//    /**
//	 * 查询列表
//	 */
//    public List<FormReloDO> list(Map<String, Object> map){
//        return formReloDao.list(map);
//    }
//
//    /**
//	 * 保存/修改
//	 */
//    @Transactional(readOnly=false)
//    public int save(FormReloDO formRelo){
//        return super.save(formRelo);
//    }
//
//    /**
//	 * 通过id逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return formReloDao.remove(id);
//    }
//
//    /**
//	 * 通过ids批量逻辑删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return formReloDao.batchRemove(ids);
//    }
//
//    /**
//	 * 通过id物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int delete(String id){
//        return formReloDao.delete(id);
//    }
//
//    /**
//	 * 通过ids物理删除
//	 */
//    @Transactional(readOnly=false)
//    public int batchDelete(String[] ids){
//        return formReloDao.batchDelete(ids);
//    }
//
//    /**
//	 * 批量插入
//	 */
//    @Transactional(readOnly=false)
//    public int batchInsert(List<FormReloDO> formRelos){
//    	return formReloDao.batchInsert(formRelos);
//    }
//
//    /**
//	 * 批量修改
//	 */
//	@Transactional(readOnly=false)
//	public int batchUpdate(List<FormReloDO> formRelos){
//		return formReloDao.batchUpdate(formRelos);
//	}
//
//    /**
//	 * 通过id复制一条相同的数据
//	 */
//    @Transactional(readOnly=false)
//    public int copy(String id){
//    	int result = 0;
//    	FormReloDO formRelo = formReloDao.get(id);
//    	if (formRelo != null){
//    		formRelo.setId(null);
//    		formRelo.preInsert();
//    		//表结构中name字段不一定存在，故此自动生成代码时注释下列代码，存在时取消注释即可
////    		if (StrUtil.isNotBlank(formRelo.getName())){
////    			formRelo.setName(formRelo.getName() + "-复制");
////    		}
//    		result = formReloDao.insert(formRelo);
//    	}
//        return result;
//    }
//
//    /**
//	 *
//	 * @Description: 修改（通过自定义的条件进行修改操作）
//	 * @param formRelo 要被修改的参数
//	 * @param whereMap 修改条件
//	 * @return: 返回修改数量
//	 */
//	@Transactional(readOnly=false)
//    public int updateByWhere(FormReloDO formRelo, Map<String,Object> whereMap){
//    	return formReloDao.updateByWhere(formRelo, whereMap);
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
//    	return formReloDao.removeByWhere(whereMap);
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
//		return formReloDao.deleteByWhere(whereMap);
//	}
//}
