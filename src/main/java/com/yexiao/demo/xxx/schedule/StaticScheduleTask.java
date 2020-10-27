package com.yexiao.demo.xxx.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author xuhf
 * @date 2020/8/19 19:06
 * 静态定时任务
 **/
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticScheduleTask {

    //3.添加定时任务 每30秒执行一次
    //@Scheduled(cron = "0/30 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate=5000)
    public void configureTasks() {
        /**
         * 每5秒要执行的任务
         * */
    }
}
