package com.yexiao.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.RoleService;
import com.yexiao.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
