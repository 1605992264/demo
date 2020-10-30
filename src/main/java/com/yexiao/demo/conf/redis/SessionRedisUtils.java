package com.yexiao.demo.conf.redis;

import com.alibaba.fastjson.JSONObject;
import com.yexiao.demo.domain.UserDO;
import org.apache.catalina.User;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhf
 * @date 2020/10/29 15:13
 **/
@Component
public class SessionRedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    private static String pre = "sessionId";

    public List<String> getAllSessionId(){
        Set keys = redisTemplate.keys( pre + "*");
        List<String> list = new LinkedList<>();
        keys.forEach(key->{
            list.add(redisTemplate.opsForValue().get(key).toString());
        });
        return list;
    }

    public void addSession(String sessionId){
        redisTemplate.opsForValue().set(pre + sessionId , sessionId,30, TimeUnit.MINUTES);
    }

    public void deleteSession(String sessionId){
        redisTemplate.delete(pre + sessionId);
    }


}
