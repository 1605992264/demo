package com.yexiao.demo.conf.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xuhf
 * @date 2020/10/30 16:19
 **/
@Component
public class RabbitmqReceiver {

    @RabbitHandler
    @RabbitListener(queues = "yexiao")
    public void stringHandler(String message) {
        System.out.println("string队列 message:" + message);
    }

}
