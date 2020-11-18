//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.FroleDao;
//import com.txdata.flow.domain.FroleDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方角色表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:21
// */
// @Service
//public class FroleService extends CrudService<FroleDao, FroleDO> {
//
//    @Autowired
//    private FroleDao froleDao;
//
//    public FroleDO get(String id , String systemCode){
//        return froleDao.get(id,systemCode);
//    }
//
//    public Page<FroleDO> page(Page<FroleDO> page, Map<String, Object> map){
//        return froleDao.list(page, map);
//    }
//
//    public List<FroleDO> list(Map<String, Object> map){
//        return froleDao.list(map);
//    }
//
//    @Override
//    @Transactional(readOnly=false)
//    public int save(FroleDO frole){
//        return super.save(frole);
//    }
//
//    @Override
//    @Transactional(readOnly=false)
//    public int update(FroleDO frole){
//        return froleDao.update(frole);
//    }
//
//    @Transactional(readOnly=false)
//    public int remove(String id , String systemCode){
//        return froleDao.remove(id,systemCode);
//    }
//
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids , String systemCode){
//        return froleDao.batchRemove(ids,systemCode);
//    }
//
//}
