//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.flow.dao.SystemDao;
//import com.txdata.flow.domain.SystemDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方系统表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:37
// */
// @Service("fsystemService")
//public class SystemService extends CrudService<SystemDao, SystemDO> {
//
//    @Autowired
//    private SystemDao systemDao;
//
//    public SystemDO get(String id){
//        return systemDao.get(id);
//    }
//
//    public Page<SystemDO> page(Page<SystemDO> page, Map<String, Object> map){
//        return systemDao.list(page, map);
//    }
//
//    public List<SystemDO> list(Map<String, Object> map){
//        return systemDao.list(map);
//    }
//
//    @Transactional(readOnly=false)
//    public int save(SystemDO system){
//        return super.save(system);
//    }
//
//    @Transactional(readOnly=false)
//    public int remove(String id){
//        return systemDao.remove(id);
//    }
//
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids){
//        return systemDao.batchRemove(ids);
//    }
//
//}
