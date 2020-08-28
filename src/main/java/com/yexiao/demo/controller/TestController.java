package com.yexiao.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.DictDO;
import com.yexiao.demo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    private DictService dictService;

    @RequestMapping("/list")
    public R list(Page<DictDO> page){
        IPage<DictDO> list = dictService.page(page, null);
        return R.success(list);
    }


}
