//package com.yexiao.demo.conf.rabbitmq;
//
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author xuhf
// * @date 2020/10/30 15:56
// *
// **/
//@Configuration
//public class RabbitmqConfig implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
//
//    @Autowired
//    private RabbitTemplate template;
//
//    /**
//     * 声明交换机
//     * RabbitMQ常用的Exchange Type有
//     * direct(默认):    完全匹配 routing key 一条message只会进入一个queue
//     * topic:           direct的升级版 支持模糊查询 *用于匹配一个单词，# 用于匹配多个单词（可以是零个）
//     * fanout:          无视 routing key
//     * headers:         无视 routing key  根据 headers属性进行匹配
//     */
//    private static final String TOPIC_EXCHANGE = "topic-exchange";
//    private static final String FANOUT_EXCHANGE = "fanout-exchange";
//    private static final String HEADERS_EXCHANGE = "headers-exchange";
//    private static final String DEAD_EXCHANGE = "dead_exchange";
//
//    private static final String QUEUE_LASTING = "lasting";
//    private static final String DEAD_QUEUE= "deadQueue";
//    private static final String QUEUE_3 = "xiaoye";
//
//    @PostConstruct
//    public void init() {
//        // 消息发送成功/失败监控
//        template.setConfirmCallback(this);
//        // 队列不存在，会进行重试
//        template.setReturnCallback(this);
//        template.setMandatory(true);
//    }
//
//    /**
//     * 消息发送成功/失败监控
//     * 每一条发出的消息都会调用ConfirmCallback
//     * @param correlationData 相关数据 send时可以传
//     * @param ack 是否进入exchange
//     * @param failMessage 失败原因
//     * */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String failMessage) {
//        System.out.println("correlationData=" + correlationData + "原因:" + failMessage);
//        if(ack){
//            System.out.println("成功");
//        }else {
//            //todo: 如果失败如何处理？
//            System.out.println("失败");
//
//        }
//    }
//
//    /**
//     * 当路由不到队列时返回给消息发送者
//     * 只有在消息进入exchange但没有进入queue时才会调用
//     * @param message 发送的消息
//     * @param reason  失败原因
//     * @param routingKey 寻找queue的key
//     * @
//     * */
//    @Override
//    public void returnedMessage(Message message, int i, String reason, String s1, String routingKey) {
//        //todo: 消息发送到服务器失败，如何处理？重新发送？记录？
//        StringBuilder builder = new StringBuilder();
//        builder.append("\n").append("消息丢失!").append(message.toString())
//                .append("\n").append("失败原因:").append(reason)
//                .append("\n").append("routingKey = ").append(routingKey);
//    }
//
//    /**
//     * 声明队列
//     * */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_LASTING,true,true,false);
//    }
//
//    /**
//     * 声明死信队列
//     * dead letter exchange
//     * 这个与spring-amqp无关，是rabbitmq的设置。
//     * 将一个queue设置了x-dead-letter-exchange及x-dead-letter-routing-key两个参数后，
//     * 这个queue里丢弃的消息将会进入dead letter exchange，并route到相应的queue里去。
//     * 丢弃的消息:
//     * The message is rejected (basic.reject or basic.nack) with requeue=false,
//     * The TTL for the message expires;
//     * The queue length limit is exceeded.
//     * */
//    @Bean
//    public Queue deadQueue() {
//        return new Queue(DEAD_QUEUE,true,false,false);
//    }
//
//    @Bean
//    public DirectExchange deadExchange(){
//        DirectExchange directExchange = new DirectExchange(DEAD_EXCHANGE);
//        return directExchange;
//    }
//
//    @Bean
//    public Binding deadBinding(DirectExchange deadExchange,Queue deadQueue){
//        return BindingBuilder.bind(deadQueue).to(deadExchange).withQueueName();
//    }
//
//    /**
//     * 声明队列
//     * */
//    @Bean
//    public Queue queue3() {
//        Map<String,Object> map = new HashMap<>();
//        map.put("x-dead-letter-exchange",DEAD_EXCHANGE);
//        map.put("x-dead-letter-routing-key",DEAD_QUEUE);
//        return new Queue(QUEUE_3,true,false,false,map);
//    }
//
//    /**
//     * 声明Topic交换机
//     * direct的升级版 支持模糊查询 *用于匹配一个单词，# 用于匹配多个单词（可以是零个）
//     * */
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//
//    /**
//     * 将队列与Topic交换机进行绑定，并指定路由键
//     * */
//    @Bean
//    Binding topicBinding(Queue queue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue).to(topicExchange).with("*xiao*");
//    }
//
//    /**
//     * 声明fanout交换机
//     * 无视 routing key
//     * */
//    @Bean
//    FanoutExchange fanoutExchange() {
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//
//    /**
//     * 将队列与fanout交换机进行绑定
//     * */
//    @Bean
//    Binding fanoutBinding(Queue queue, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(queue).to(fanoutExchange);
//    }
//
//    /**
//     * 声明Headers交换机
//     * 无视 routing key  根据 headers属性进行匹配
//     * */
//    @Bean
//    HeadersExchange headersExchange() {
//        return new HeadersExchange(HEADERS_EXCHANGE);
//    }
//
//    /**
//     * 将队列与headers交换机进行绑定
//     * */
//    @Bean
//    Binding headersBinding(Queue queue, HeadersExchange headersExchange) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("First","A");
//        map.put("Fourth","D");
//        //whereAny表示部分匹配，whereAll表示全部匹配
////        return BindingBuilder.bind(queue).to(headersExchange).whereAll(map).match();
//        return BindingBuilder.bind(queue).to(headersExchange).whereAny(map).match();
//    }
//}
