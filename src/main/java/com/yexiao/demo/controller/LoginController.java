package com.yexiao.demo.controller;


import com.yexiao.demo.base.annotation.Log;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.service.UserService;
import com.yexiao.demo.service.impl.ShiroLoginServiceImpl;
import com.yexiao.demo.service.impl.SpringSecurityLoginServiceImpl;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RestController
public class LoginController {

    @Autowired
    private SpringSecurityLoginServiceImpl loginService;

    @Log(message = "登入")
    @PostMapping("/login")
    public R login(@RequestParam String username, @RequestParam String password){
        return R.success(loginService.login(username,password));
    }

    @Log(message = "登出")
    @RequestMapping("/logout")
    public R logout(){
        loginService.logout();
        return R.success("登出成功");
    }

}
