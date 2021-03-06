package com.yexiao.demo.base.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuhf
 * @date 2020/9/17 9:47
 **/
public class CacheUtils {

    private CacheUtils(){

    }

    private static ConcurrentHashMap<String,CacheDO> cacheMap = new ConcurrentHashMap<>();

    public static void put(String key,Object value,Integer timeout){
        CacheDO cacheDO = new CacheDO(value,timeout);
        cacheMap.put(key,cacheDO);
    }

    public static void put(String key,Object value){
        CacheDO cacheDO = new CacheDO(value);
        cacheMap.put(key,cacheDO);
    }

    /**
     * 是否包含
     * @return true 包含
     * */
    public static boolean contain(Object key){
        CacheDO cacheDO = cacheMap.get(key);
        if(cacheDO != null){
            return true;
        }
        return false;
    }


    public static Object get(String key){
        CacheDO cacheDO = cacheMap.get(key);
        if(cacheDO == null){
            return null;
        }
        return cacheDO.getData();
    }

    public static Map<String, CacheDO> getCacheMap() {
        return cacheMap;
    }

}
