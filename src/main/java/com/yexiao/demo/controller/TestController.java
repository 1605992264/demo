package com.yexiao.demo.controller;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.service.DictService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RequestMapping("/test")
@RestController
@Validated
public class TestController implements InitializingBean {

    @Autowired
    private DictService dictService;
    
    @InitBinder
    public void  init(){
        System.out.println("每次调用接口(方法)都会调用");
    }

    @RequestMapping("/list")
    public R list( @NotEmpty(message = "a不能为null")@Size(min = 1,max = 5,message = "长度应该在1-5为之间") String a) throws Exception {
        return R.success(a);
    }

    @RequestMapping("getAll")
    public R a(){
        String deviceInfo = "";
        String callBackUrl = "回调地址";
        HttpUtil.post(callBackUrl,deviceInfo);
        return R.success();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("bean初始化时调用");
    }
}
