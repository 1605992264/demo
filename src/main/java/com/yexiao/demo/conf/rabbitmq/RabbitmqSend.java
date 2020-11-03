package com.yexiao.demo.conf.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/10/30 16:09
 **/
@Component
@Slf4j
public class RabbitmqSend implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    @Autowired
    private RabbitTemplate template;

    @PostConstruct
    public void init() {
        // 消息发送成功/失败监控
        template.setConfirmCallback(this);
        // 队列不存在，会进行重试
        template.setReturnCallback(this);
        template.setMandatory(true);
    }

    /**
     * @param routingKey 路由关键字
     * @param msg 消息体
     */
    public void sendDirectMsg(String routingKey, String msg) {
        template.convertAndSend(routingKey, msg);
    }

    /**
     * @param routingKey 路由关键字
     * @param msg 消息体
     * @param exchange 交换机
     */
    public void sendExchangeMsg(String exchange, String routingKey, String msg) {
        template.convertAndSend(exchange, routingKey, msg);
    }

    /**
     * @param map 消息headers属性
     * @param exchange 交换机
     * @param msg 消息体
     */
    public void sendHeadersMsg(String exchange, String msg, Map<String, Object> map) {
        template.convertAndSend(exchange, null, msg, message -> {
            message.getMessageProperties().getHeaders().putAll(map);
            return message;
        });
    }

    /**
     * 消息发送成功/失败监控
     * @param correlationData
     * @param isSuccess 是否成功
     * @param s
     * */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSuccess, String s) {
        if(isSuccess){
            System.out.println("成功");
        }else {
            //todo: 如果失败如何处理？
            log.info("失败");

        }
    }

    /**
     * 当路由不到队列时返回给消息发送者
     * @param message
     * */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        //todo: 消息发送到服务器失败，如何处理？重新发送？记录？
        log.info(i + "   s=" + s + "    s1=" +s1+ "     s2=" + s2);
    }
}
