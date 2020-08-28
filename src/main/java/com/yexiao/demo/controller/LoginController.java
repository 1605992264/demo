package com.yexiao.demo.controller;

import com.alibaba.fastjson.JSONObject;
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

    @RequestMapping("/login")
        public String login(@RequestParam String username, @RequestParam String password){
        return JSONObject.toJSONString(userService.login(username,password));
    }

    @RequestMapping("/logout")
    public String logout(){
        userService.logout();
        return JSONObject.toJSONString("登出");
    }



}
