package com.yexiao.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yexiao.demo.base.domain.BasePage;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xuhf
 * @date 2020/8/20 11:03
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("getOnlineUsers")
    public R onlineUsers(HttpServletRequest request, HttpServletResponse response){
        List<UserDO> userDOList = userService.onlineUsers(request,response);
        return R.success(userDOList);
    }
    
    @PostMapping("/register")
    public R register(UserDO userDO){
        if( userService.register(userDO) ){
            return R.success("注册成功");
        }
        return R.error("注册失败");
    }

    @GetMapping("/list")
    public R list(BasePage<UserDO> basePage, UserDO userDO){
        IPage<UserDO> list = userService.page(basePage.newMybatisPlusPage(),userDO);
        return R.success(list);
    }

    @GetMapping("/get")
    public R list(String id){
        UserDO user = userService.getById(id);
        return R.success(user);
    }

    @RequiresPermissions("edit")
    @PostMapping("/update")
    public R add(UserDO userDO){
        if( userService.updateById(userDO)) {
            return R.success("更新成功");
        }
        return R.error("更新失败");
    }

    @RequiresRoles("admin")
    @PostMapping("/remove")
    public R remove(String id){
        if(userService.removeById(id)){
            return R.success("逻辑删除成功");
        }
        return R.error("逻辑删除失败");
    }

    @RequiresRoles("admin")
    @PostMapping("/delete")
    public R delete(String id){
        if(userService.deleteById(id)){
            return R.success("物理删除成功");
        }
        return R.error("物理删除失败");
    }

}
