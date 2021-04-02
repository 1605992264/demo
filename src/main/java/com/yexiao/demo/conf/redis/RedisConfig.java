package com.yexiao.demo.conf.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.SpringServletContainerInitializer;

/**
 * @author xuhf
 * @date 2020/10/29 15:39
 * Redis配置
 **/
@Configuration
public class RedisConfig implements ApplicationListener<ApplicationStartedEvent> {

    @Value("${redis.subscribe:test}")
    private String subscribeList;

    private RedisMessageListener redisMessageListener;
    private RedisMessageListenerTwo redisMessageListenerTwo;

    public RedisConfig(RedisMessageListener redisMessageListener,RedisMessageListenerTwo redisMessageListenerTwo){
        this.redisMessageListener = redisMessageListener;
        this.redisMessageListenerTwo = redisMessageListenerTwo;
    }

    /**
     * 防止乱码
     * */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    /**
     * 订阅事件
     * */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        RedisConnectionFactory redisConnectionFactory = event.getApplicationContext().getBean(RedisConnectionFactory.class);
        redisConnectionFactory.getConnection().subscribe(redisMessageListener,subscribeList.getBytes());
        redisConnectionFactory.getConnection().subscribe(redisMessageListenerTwo,subscribeList.getBytes());
    }
}
