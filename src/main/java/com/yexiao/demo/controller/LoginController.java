package com.yexiao.demo.controller;


import com.yexiao.demo.base.annotation.Log;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Log(message = "登入")
    @RequestMapping("/login")
    public R login(@RequestParam String username, @RequestParam String password){
        return R.success(userService.login(username,password));
    }

    @Log(message = "登出")
    @RequestMapping("/logout")
    public R logout(){
        userService.logout();
        return R.success("登出成功");
    }

}
