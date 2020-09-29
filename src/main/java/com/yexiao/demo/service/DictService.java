package com.yexiao.demo.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.DictDO;

/**
* @author xuhf
* @date 2020-08-28 16:38:36.313
**/
public interface DictService extends IService<DictDO> {

    IPage list(Page page, Class<? extends IService> dao);
}
