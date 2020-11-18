//package com.yexiao.demo.flow.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.txdata.common.persistence.CrudService;
//import com.txdata.common.utils.CacheUtils;
//import com.txdata.flow.dao.FofficeDao;
//import com.txdata.flow.dao.FuserDao;
//import com.txdata.flow.domain.FajaxOffice;
//import com.txdata.flow.domain.FofficeDO;
//import com.txdata.flow.domain.FuserDO;
//import com.txdata.system.utils.UserUtils;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 第三方机构表
// *
// * @author 3xdata
// * @email 3xdata@3xdata.cn
// * @date 2020-11-13 14:34:16
// */
// @Service
//public class FofficeService extends CrudService<FofficeDao, FofficeDO> {
//
//    @Autowired
//    private FofficeDao fofficeDao;
//    @Autowired
//    private FuserDao fuserDao;
//
//    public FofficeDO get(String id , String systemCode){
//        return fofficeDao.get(id,systemCode);
//    }
//
//    public Page<FofficeDO> page(Page<FofficeDO> page, Map<String, Object> map){
//        return fofficeDao.list(page, map);
//    }
//
//    public List<FofficeDO> list(Map<String, Object> map){
//        return fofficeDao.list(map);
//    }
//
//    @Transactional(readOnly=false)
//    public int save(FofficeDO foffice){
//        UserUtils.removeCache(UserUtils.CACHE_FOFFICE_LIST);
//        CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.CACHE_OFFICE_ALL_LIST);
//        UserUtils.removeCache("fofficeTree");
//        UserUtils.removeCache("fofficeUserTree");
//        return super.save(foffice);
//    }
//
//    @Transactional(readOnly=false)
//    public int update(FofficeDO foffice){
//        UserUtils.removeCache(UserUtils.CACHE_FOFFICE_LIST);
//        CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.CACHE_OFFICE_ALL_LIST);
//        UserUtils.removeCache("fofficeTree");
//        UserUtils.removeCache("fofficeUserTree");
//        return fofficeDao.update(foffice);
//    }
//
//    @Transactional(readOnly=false)
//    public int remove(String id , String systemCode){
//        return fofficeDao.remove(id,systemCode);
//    }
//
//    @Transactional(readOnly=false)
//    public int batchRemove(String[] ids , String systemCode){
//        return fofficeDao.batchRemove(ids,systemCode);
//    }
//
//
//    /**
//     * 根据组织机构id查询该机构及其下属机构下的机构列表
//     *
//     * @param office
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public List<FofficeDO> searchList(FofficeDO office) {
//        if (office != null) {
//            return dao.getListByOfficeId(office);
//        }
//       // return new ArrayList<Office>();
//        return null;
//    }
//
//    public List<FajaxOffice> getNewOfficeTree(List<FajaxOffice> rootOffice, Boolean isUser , String systemCode) {
//        // 最后的结果
//        List<FajaxOffice> officeList = new ArrayList<FajaxOffice>();
//        // 先找到所有的一级菜单
//        for (int i = 0; i < rootOffice.size(); i++) {
//            // 一级菜单parentId为0
//            if ("0".equals(rootOffice.get(i).getParentId())) {
//                FajaxOffice office = rootOffice.get(i);
//                officeList.add(office);
//            }
//        }
////		List<UserDO> users = userDao.findUserByNotInOfficeId();
////		boolean flag = true;
//        //单个不从根节点开始
//        if(CollectionUtils.isNotEmpty(rootOffice) && CollectionUtils.isEmpty(officeList)){
//            for (int i = 0; i < rootOffice.size(); i++) {
//                FajaxOffice office = rootOffice.get(i);
//                officeList.add(office);
//            }
//        }
//
//        // 为一级菜单设置子菜单，getChild是递归调用的
//        for (FajaxOffice office : officeList) {
//            office.setChildren(getNewOfficeChild(office.getId(), rootOffice, isUser , systemCode));
//            if (isUser != null && isUser) { // 机构数下加上相关人员
//                FuserDO user = new FuserDO();
//                user.setOfficeId(office.getId());
//                user.setSystemCode(systemCode);
//                List<FuserDO> userList = fuserDao.findUserByOfficeId(user);
//                List<FajaxOffice> uaList = new ArrayList<FajaxOffice>();
//                for (FuserDO u : userList) {
//                    FajaxOffice aa = new FajaxOffice(u);
//                    uaList.add(aa);
//                }
//
//                List<FajaxOffice> childList = office.getChildren();
//                if (childList != null) {
//                    childList.addAll(uaList);
//                    office.setChildren(childList);
//                }else {
//                    office.setChildren(uaList);
//                }
//
//            }
//        }
//
//        return officeList;
//    }
//
//    private List<FajaxOffice> getNewOfficeChild(String id, List<FajaxOffice> rootOffice, Boolean isUser , String systemCode) {
//        // 子菜单
//        List<FajaxOffice> ajaxChildList = new ArrayList<>();
//        List<FofficeDO> childList = findByparentId(new FofficeDO(id));
//        // 把子菜单的子菜单再循环一遍
//        for (FofficeDO office : childList) {// 没有url子菜单还有子菜单
//            // 递归
//            FajaxOffice ao = new FajaxOffice(office, true);
//            ao.setChildren(getNewOfficeChild(office.getOfficeId(), rootOffice, isUser , systemCode));
//            if (isUser != null && isUser) {
//                FuserDO user = new FuserDO();
//                user.setOfficeId(office.getOfficeId());
//                user.setSystemCode(systemCode);
//                List<FuserDO> userList = fuserDao.findUserByOfficeId(user);
//                List<FajaxOffice> uaList = new ArrayList<FajaxOffice>();
//                for (FuserDO u : userList) {
//                    FajaxOffice aa = new FajaxOffice(u);
//                    uaList.add(aa);
//                }
//                List<FajaxOffice> cList = ao.getChildren();
//                if (cList == null) {
//                    cList = new ArrayList<FajaxOffice>();
//                }
//                cList.addAll(uaList);
//                ao.setChildren(cList);
//            }
//            ajaxChildList.add(ao);
//        } // 递归退出条件
//        if (ajaxChildList.size() == 0) {
//            return null;
//        }
//        return ajaxChildList;
//    }
//
//    /**
//     * 根据父级id查找子部门
//     *
//     * @param fofficeDO
//     * @return
//     */
//    private List<FofficeDO> findByparentId(FofficeDO fofficeDO) {
//        List<FofficeDO> list = dao.findByparentId(fofficeDO);
//        return list;
//    }
//
//
//
//}
