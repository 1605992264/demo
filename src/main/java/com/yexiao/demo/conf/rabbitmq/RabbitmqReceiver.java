package com.yexiao.demo.conf.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
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
    public void stringHandler(Channel channel,Message message) {
        System.out.println("yexiao队列 message:" + message);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "xiaoye")
    public void stringHandler2(Channel channel, Message message) {
        System.out.println("xiaoye队列 message:" + message);

    }

    @RabbitHandler
    @RabbitListener(queues = "yeshen")
    public void stringHandler3(Channel channel, Message message) {
        System.out.println("yeshen队列 message:" + message);
    }

}
