package com.yexiao.demo.conf.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author xuhf
 * @date 2020/10/30 16:19
 **/
@Component
public class RabbitmqReceiver {

    @RabbitHandler
    @RabbitListener(queues = "lasting")
    public void stringHandler(Channel channel,Message message) {
        System.out.println("yexiao队列 message:" + message);
        try {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @RabbitHandler
    @RabbitListener(queues = "xiaoye")
    public void stringHandler2(Channel channel, Message message) throws IOException {
        System.out.println("xiaoye队列 message:" + message);
        String body = new String(message.getBody());
        if("ok".equals(body)){
            System.out.println("成功");
        }else if("error".equals(body)){
            int i = 1/0;
        }else if("deal".equals(body)){
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        }else{
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }

    }

    @RabbitHandler
    @RabbitListener(queues = "deadQueue")
    public void deadHandler(Channel channel,Message message) throws IOException {
        // todo: 消息失败处理 例如保存日志
        System.out.println("dead队列 message:" + message);
    }

}
