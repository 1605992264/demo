package com.yexiao.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yexiao.demo.base.domain.BasePage;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import com.yexiao.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/register")
    public R register(UserDO userDO){
        if( userService.register(userDO) ){
            return R.success("注册成功");
        }
        return R.error("注册失败");
    }

    @MyAspect(message = "列表")
    @RequestMapping("/list")
    public R list(BasePage<UserDO> basePage, UserDO entity){
        IPage<UserDO> list = userService.page(basePage.newMybatisPlusPage(),null);
        return R.success(list);
    }

    @MyAspect(message = "查询单个用户信息")
    @RequestMapping("/get")
    public R list(String id){
        UserDO user = userService.getById(id);
        return R.success(user);
    }

    @MyAspect(message = "新增")
    @RequiresPermissions("edit")
    @RequestMapping("/add")
    public R add(UserDO userDO){
        if( userService.save(userDO)) {
            return R.success("新增成功");
        }
        return R.error("新增失败");
    }

    @RequiresRoles("admin")
    @MyAspect(message = "逻辑删除")
    @RequestMapping("/remove")
    public R remove(String id){
        if(userService.removeById(id)){
            return R.success("逻辑删除成功");
        }
        return R.error("逻辑删除失败");
    }

    @RequiresRoles("admin")
    @MyAspect(message = "物理删除")
    @RequestMapping("/delete")
    public R delete(String id){
        if(userService.deleteById(id)){
            return R.success("物理删除成功");
        }
        return R.error("物理删除失败");
    }

}
