package com.yexiao.demo.controller;

import com.yexiao.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhf
 * @date 2020/8/20 11:03
 **/
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

}
