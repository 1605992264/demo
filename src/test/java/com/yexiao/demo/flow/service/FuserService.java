//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.google.common.collect.Lists;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.IdGen;
//import com.txdata.common.utils.StringUtils;
//import com.txdata.flow.dao.FroleDao;
//import com.txdata.flow.dao.FuserDao;
//import com.txdata.flow.domain.FroleDO;
//import com.txdata.flow.domain.FuserDO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方用户表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:33:54
// */
// @Service
//public class FuserService extends CrudService<FuserDao, FuserDO> {
//
//    @Autowired
//    private FuserDao fuserDao;
//    @Autowired
//    private FroleDao froleDao;
//
//    public FuserDO get(String id , String systemCode){
//        return fuserDao.get(id,systemCode);
//    }
//
//    public Page<FuserDO> page(Page<FuserDO> page, Map<String, Object> map){
//        return fuserDao.list(page, map);
//    }
//
//    @Override
//    public List<FuserDO> list(Map<String, Object> map){
//        return fuserDao.list(map);
//    }
//
//    @Override
//    @Transactional(readOnly=false)
//    public int save(FuserDO fuser) {
//        List<FroleDO> roleList = Lists.newArrayList();
//        List<String> roleIdList = fuser.getRoleIdList();
//        if (roleIdList != null) {
//            roleList = froleDao.selectRoleById(roleIdList,fuser.getSystemCode());
//        }
//        // 保存用户信息
//        fuser.setRoleList(roleList);
//
//        //主键新增
//        fuser.setId(IdGen.uuid());
//        int result = fuserDao.insert(fuser);
//
//        //判断是否有用户id
//        if (StringUtils.isNotBlank(fuser.getId())) {
//            // 更新用户与角色关联
//            fuserDao.deleteUserRole(fuser);
//            if (fuser.getRoleList() != null && fuser.getRoleList().size() > 0) {
//                for (FroleDO role : fuser.getRoleList()) {
//                    role.setUserRoleId(IdGen.uuid());
//                }
//                fuserDao.insertUserRole(fuser);
//            }
//        }
//        return result;
//    }
//
//    @Transactional(readOnly=false)
//    public int remove(String id , String systemCode){
//        return fuserDao.remove(id,systemCode);
//    }
//
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids , String systemCode){
//        return fuserDao.batchRemove(ids,systemCode);
//    }
//
//
//
//    @Override
//    @Transactional(readOnly=false)
//    public int update(FuserDO fuser) {
//        List<FroleDO> roleList = Lists.newArrayList();
//        List<String> roleIdList = fuser.getRoleIdList();
//        if (roleIdList != null) {
//            roleList = froleDao.selectRoleById(roleIdList,fuser.getSystemCode());
//        }
//        // 保存用户信息
//        fuser.setRoleList(roleList);
//
//        //主键新增
//        fuser.setId(IdGen.uuid());
//        int result = fuserDao.update(fuser);
//
//        //判断是否有用户id
//        if (StringUtils.isNotBlank(fuser.getId())) {
//            // 更新用户与角色关联
//            fuserDao.deleteUserRole(fuser);
//            if (fuser.getRoleList() != null && fuser.getRoleList().size() > 0) {
//                for (FroleDO role : fuser.getRoleList()) {
//                    role.setUserRoleId(IdGen.uuid());
//                }
//                fuserDao.insertUserRole(fuser);
//            }
//        }
//        return result;
//    }
//
//    public List<String> findUserIdSetByRoleId(String roleId, String systemCode){
//        return fuserDao.findUserIdSetByRoleId(roleId,systemCode);
//    }
//
//    public List<String> findAllUserByOfficeId(String roleId, String systemCode){
//        return fuserDao.findAllUserByOfficeId(roleId,systemCode);
//    }
//}
