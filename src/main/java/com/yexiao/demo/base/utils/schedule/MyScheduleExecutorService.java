package com.yexiao.demo.base.utils.schedule;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author xuhf
 * @date 2020/8/20 13:55
 * 定时任务
 **/
public class MyScheduleExecutorService  {

    private static ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10);

    /**
     * @param runnable 任务
     * @param seconds 每隔几秒执行
     * 循环执行定时任务
     * */
    public static ScheduledFuture loopTask(Runnable runnable,long seconds){
        ScheduledFuture<?> schedule = scheduled.scheduleWithFixedDelay (new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }

        },0, seconds, TimeUnit.SECONDS);
        return schedule;
    }

    /**
     * 单次延迟实现
     * @param runnable 要执行的任务
     * @param seconds 延迟时间（秒）
     * */
    public static void singleTask(Runnable runnable,long seconds){
        scheduled.schedule(runnable,seconds,TimeUnit.SECONDS);
    }


}
