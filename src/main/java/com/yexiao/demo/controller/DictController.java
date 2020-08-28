package com.yexiao.demo.controller;

import com.yexiao.demo.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xuhf
* @date 2020-08-28 16:38:36.313
**/
@RestController
@RequestMapping("Dict")
public class DictController {

    @Autowired
    private DictService dictService;




}
