package com.yexiao.demo.base.cache;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/19 19:06
 * 静态定时任务
 **/
@Component
public class CacheDeleteTask {

    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    public void configureTasks() {
        /**
         * 自定义定时任务删除缓存
         * */
        List<String> removeKey = new ArrayList<>();
        Map<String, CacheDO> cacheMap = CacheUtils.getCacheMap();
        // 记录要删除的缓存
        for(Map.Entry<String, CacheDO> entry : cacheMap.entrySet()){
            CacheDO cache = entry.getValue();
            long timeout = cache.getCreateTime() + (cache.getTimeout() * 60000);
            if(timeout < System.currentTimeMillis()){
                removeKey.add(entry.getKey());
            }
        }
        // 删除缓存
        for(String key : removeKey){
            cacheMap.remove(key);
        }
    }
}
