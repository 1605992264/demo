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

    public List<UserDO> getAllSession(){
        Set keys = redisTemplate.keys( pre + "*");
        List<UserDO> list = new LinkedList<>();
        keys.forEach(key->{
            Object o = redisTemplate.opsForValue().get(key);
            list.add(JSONObject.parseObject(JSONObject.toJSONString(o),UserDO.class));
        });
        return list;
    }

    public void addUser(UserDO session){
        redisTemplate.opsForValue().set(pre + session.getToken() , session,30, TimeUnit.MINUTES);
    }

    public void deleteUser(String sessionId){
        redisTemplate.delete(pre + sessionId);
    }


}
