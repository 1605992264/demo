package com.yexiao.demo.conf.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author xuhf
 * @date 2021/4/2 10:30
 **/
@Component
public class RedisMessageListenerTwo implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("我是2号"+message);
    }
}
