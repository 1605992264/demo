package com.yexiao.demo.controller;

import com.yexiao.demo.base.extra.weixin.MaterialType;
import com.yexiao.demo.base.extra.weixin.PublicNumberUtils;
import com.yexiao.demo.base.utils.schedule.MyScheduleExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDateTime;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RequestMapping("/test")
@RestController
public class TestController {


    @Autowired
    private PublicNumberUtils publicNumberUtils;

    @RequestMapping("/scheduled")
    public String scheduled(){
         MyScheduleExecutorService.task(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始定时任务:" + LocalDateTime.now());
            }
        },5);
        return "定时任务每5秒执行一次";
    }

    @RequestMapping("/cancel")
    public String cancel(){
        return MyScheduleExecutorService.cancel().toString();
    }



}
