package com.yexiao.demo.base.utils.schedule;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author xuhf
 * @date 2020/8/20 13:55
 * 定时任务
 **/
public class MyScheduleExecutorService  {

    private static ThreadLocal<ScheduledFuture> threadLocal = new ThreadLocal<>();

    private static ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10);

    /**
     * @param runnable 任务
     * @param seconds 每隔几秒执行
     * 执行定时任务
     * */
    public static ScheduledFuture task(Runnable runnable,long seconds){
        ScheduledFuture<?> schedule = scheduled.scheduleWithFixedDelay (new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }

        },0, seconds, TimeUnit.SECONDS);
        threadLocal.set(schedule);
        return schedule;
    }

    /**
     * 终止定时任务
     * */
    public static Boolean cancel(){
        return threadLocal.get().cancel(true);
    }


}
