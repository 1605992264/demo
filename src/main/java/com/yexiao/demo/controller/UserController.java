package com.yexiao.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import com.yexiao.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhf
 * @date 2020/8/20 11:03
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @MyAspect(message = "列表")
    @RequestMapping("/list")
    public String list(Page<UserDO> page){
        IPage<UserDO> list = userService.page(page, null);
        return JSONObject.toJSONString(list);
    }

    @MyAspect(message = "新增")
    @RequiresPermissions("edit")
    @RequestMapping("/add")
    public String add(UserDO userDO){
        if( userService.save(userDO)) {
            return JSONObject.toJSONString("新增成功");
        }
        return JSONObject.toJSONString("新增失败");
    }

    @RequiresRoles("admin")
    @MyAspect(message = "逻辑删除")
    @RequestMapping("/remove")
    public String remove(String id){
        userService.removeById(id);
        return "逻辑删除";
    }

    @RequiresRoles("admin")
    @MyAspect(message = "物理删除")
    @RequestMapping("/delete")
    public String delete(String id){
        userService.deleteById(id);
        return "物理删除";
    }

}
