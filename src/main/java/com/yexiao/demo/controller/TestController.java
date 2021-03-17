package com.yexiao.demo.controller;

import cn.hutool.http.HttpUtil;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.impl.TestService;
import com.yexiao.demo.utils.UserUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RequestMapping("/test")
@RestController
@Validated
public class TestController implements InitializingBean {

    @Autowired
    private TestService testService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/minio/upload")
    public R upload(MultipartFile file){
        boolean upload = testService.upload(file);
        if(upload){
            return R.success();
        }
        return R.error();
    }

    @RequestMapping("/minio/fileList")
    public R fileList(String name){
        List list = testService.fileList(name);
        return R.success(list);
    }

    @RequestMapping("/sql")
    public void sql(String sql){
        jdbcTemplate.execute(sql);
    }

    @InitBinder
    public void  init(){
        System.out.println("每次调用接口(被@RequestMapping注解了的)都会调用");
    }

    @RequestMapping("/list")
    public R list(Map<String,Object> map) {
        return R.success(map);
    }

    @RequestMapping("getNowUser")
    public R getNowUser(){
        UserDO user = UserUtils.getUser();
        return R.success(user);
    }


    @RequestMapping("getAll")
    public R a(){
        String deviceInfo = "";
        String callBackUrl = "回调地址";
        HttpUtil.post(callBackUrl,deviceInfo);
        return R.success();
    }


    @Override
    public void afterPropertiesSet(){
        System.out.println("bean初始化时调用");
    }
}
