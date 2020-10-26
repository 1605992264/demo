package com.yexiao.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yexiao.demo.domain.OfficeDO;

/**
* @author xuhf
* @date 2020-10-26 15:38:18.574
**/
public interface OfficeService extends IService<OfficeDO> {

    IPage<OfficeDO> page(IPage<OfficeDO> basePage, OfficeDO officeDO);

    /**
    * 物理删除
    * */
    boolean deleteById(String id);

}
