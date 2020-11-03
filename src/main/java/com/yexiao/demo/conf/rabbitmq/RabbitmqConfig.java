package com.yexiao.demo.conf.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/10/30 15:56
 **/
@Configuration
public class RabbitmqConfig {

    /**
     * 声明交换机
     * RabbitMQ常用的Exchange Type有
     * direct(默认):    完全匹配 routing key
     * topic:           direct的升级版 支持模糊查询 *用于匹配一个单词，# 用于匹配多个单词（可以是零个）
     * fanout:          无视 routing key
     * headers:         无视 routing key  根据 headers属性进行匹配
     */
    private static final String TOPIC_EXCHANGE = "topic-exchange";
    private static final String FANOUT_EXCHANGE = "fanout-exchange";
    private static final String HEADERS_EXCHANGE = "headers-exchange";

    private static final String QUEUE_NAME = "yexiao";

    /**
     * 声明队列
     * */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    /**
     * 声明Topic交换机
     * direct的升级版 支持模糊查询 *用于匹配一个单词，# 用于匹配多个单词（可以是零个）
     * */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 将队列与Topic交换机进行绑定，并指定路由键
     * */
    @Bean
    Binding topicBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(queue.getName());
    }

    /**
     * 声明fanout交换机
     * 无视 routing key
     * */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     * 将队列与fanout交换机进行绑定
     * */
    @Bean
    Binding fanoutBinding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /**
     * 声明Headers交换机
     * 无视 routing key  根据 headers属性进行匹配
     * */
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    /**
     * 将队列与headers交换机进行绑定
     * */
    @Bean
    Binding headersBinding(Queue queue, HeadersExchange headersExchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("First","A");
        map.put("Fourth","D");
        //whereAny表示部分匹配，whereAll表示全部匹配
//        return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();
        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
    }
}
