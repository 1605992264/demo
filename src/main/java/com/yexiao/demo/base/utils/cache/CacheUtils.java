package com.yexiao.demo.base.utils.cache;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/9/17 9:47
 **/
@Component
public class CacheUtils {

    public CacheUtils(){

    }

    private Map<String,CacheDO> cacheMap = new HashMap<>();

    public void put(String key,Object value,Integer timeout){
        CacheDO cacheDO = new CacheDO(value,timeout);
        cacheMap.put(key,cacheDO);
    }

    public void put(String key,Object value){
        CacheDO cacheDO = new CacheDO(value);
        cacheMap.put(key,cacheDO);
    }

    /**
     * 是否包含
     * @return true 包含
     * */
    public boolean contain(Object key){
        CacheDO cacheDO = cacheMap.get(key);
        if(cacheDO != null){
            return true;
        }
        return false;
    }


    public Object get(String key){
        CacheDO cacheDO = cacheMap.get(key);
        if(cacheDO == null){
            return null;
        }
        return cacheDO.getData();
    }

    public Map<String, CacheDO> getCacheMap() {
        return cacheMap;
    }

}
