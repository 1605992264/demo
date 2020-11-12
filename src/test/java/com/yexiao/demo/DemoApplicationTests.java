package com.yexiao.demo;

import com.yexiao.demo.conf.rabbitmq.RabbitmqSend;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.service.DictService;
import com.yexiao.demo.service.UserService;
import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void contextLoads() {
        for(int i=0;i<20;i++){
            rabbitTemplate.convertAndSend("yexiao",i);
        }
    }

}
