package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.OfficeDO;
import com.yexiao.demo.mapper.OfficeMapper;
import com.yexiao.demo.service.OfficeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author xuhf
* @date 2020-10-26 15:38:18.574
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class OfficeServiceImpl extends ServiceImpl<OfficeMapper,OfficeDO> implements OfficeService {


    /**
    * 查询列表
    * @return*/
    @Override
    public IPage<OfficeDO> page(IPage<OfficeDO> basePage, OfficeDO officeDO){
        QueryWrapper<OfficeDO> wrapper = new QueryWrapper(officeDO);
        IPage<OfficeDO> page = baseMapper.selectPage(basePage, wrapper);
        return page;
    }

     /**
     * 物理删除
     * */
     @Override
     public boolean deleteById(String id) {
        if(baseMapper.removeById(id) >= 1){
            return true;
        }
        return false;
     }

}
