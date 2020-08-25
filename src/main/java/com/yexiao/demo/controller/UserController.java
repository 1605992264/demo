package com.yexiao.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.base.utils.annotation.MyAspect;
import com.yexiao.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author xuhf
 * @date 2020/8/20 11:03
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @MyAspect(message = "我是message参数")
    @RequestMapping("/test")
    public String test(){
        Object test = redisTemplate.opsForValue().get("test");
        if(test != null){
            return  JSONObject.toJSONString(test);
        }else {
            List<UserDO> list = userService.list(null);
            redisTemplate.opsForValue().set("test",list,30, TimeUnit.MINUTES);
            return JSONObject.toJSONString(list);
        }
    }

}
