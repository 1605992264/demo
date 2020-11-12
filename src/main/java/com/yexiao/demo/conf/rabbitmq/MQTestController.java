package com.yexiao.demo.conf.rabbitmq;

import com.yexiao.demo.base.domain.R;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhf
 * @date 2020/11/4 11:01
 **/
@RestController
@RequestMapping("mq")
public class MQTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("send")
    public R send(String message){
        rabbitTemplate.convertAndSend("yexiao",message);
        return R.success();
    }

    @RequestMapping("sendKey")
    public R sendKey(String key,String message){
        rabbitTemplate.convertAndSend(key,message);
        return R.success();
    }

    @RequestMapping("sendExchange")
    public R send(String exchange,String key,String message){
        rabbitTemplate.convertAndSend(exchange,key,message);
        return R.success();
    }

}
