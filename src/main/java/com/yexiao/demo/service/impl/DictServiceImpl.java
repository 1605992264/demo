package com.yexiao.demo.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yexiao.demo.domain.DictDO;
import com.yexiao.demo.mapper.DictMapper;
import com.yexiao.demo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author xuhf
* @date 2020-08-28 16:38:36.313
**/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper,DictDO> implements DictService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public IPage list(Page page, Class<? extends IService> dao){
        IService service = applicationContext.getBean(dao);
        return service.page(page, null);
    }





}
