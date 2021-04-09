package com.yexiao.demo.controller;

import cn.hutool.http.HttpUtil;
import com.yexiao.demo.base.domain.R;
import com.yexiao.demo.domain.UserDO;
import com.yexiao.demo.extra.ding.DingService;
import com.yexiao.demo.service.impl.TestService;
import com.yexiao.demo.utils.UserUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author xuhf
 * @date 2020/8/20 14:44
 **/
@RequestMapping("/test")
@RestController
@Validated
public class TestController implements InitializingBean {

    @Autowired
    private TestService testService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DingService dingService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Autowired
    private KafkaConsumer kafkaConsumerTwo;

    @RequestMapping("/minio/upload")
    public R upload(MultipartFile file){
        boolean upload = testService.upload(file);
        if(upload){
            return R.success();
        }
        return R.error();
    }

    @RequestMapping("/minio/fileList")
    public R fileList(String name){
        List list = testService.fileList(name);
        return R.success(list);
    }

    @RequestMapping("/sql")
    public void sql(String sql){
        jdbcTemplate.execute(sql);
    }

    @InitBinder
    public void  init(){
        System.out.println("每次调用接口(被@RequestMapping注解了的)都会调用");
    }

    @RequestMapping("/list")
    public R list(Map<String,Object> map) {
        return R.success(map);
    }

    @RequestMapping("getNowUser")
    public R getNowUser(){
        UserDO user = UserUtils.getUser();
        return R.success(user);
    }

    @RequestMapping("timeout")
    public R timeout(){
        String s = HttpUtil.get("http://localhost:8088/cds/cdsFile/download");
        return R.success(s);
    }

    @RequestMapping("dingTest")
    public R dingTest(){
        dingService.test();
        return R.success();
    }

    @RequestMapping("getAll")
    public R a(){
        String deviceInfo = "";
        String callBackUrl = "回调地址";
        HttpUtil.post(callBackUrl,deviceInfo);
        return R.success();
    }

    @RequestMapping("redisPub")
    public R redisPub(){
        redisTemplate.getRequiredConnectionFactory().getConnection().
                publish("test".getBytes(),"你好啊".getBytes());
        return R.success();
    }

    @RequestMapping("kafkaPub/{value}")
    public R kafkaPub(@PathVariable("value") String value){
        ProducerRecord<String, Object> producerRecord =
                new ProducerRecord<String, Object>("test",value);
        kafkaProducer.send(producerRecord);
        return R.success();
    }

    @Override
    public void afterPropertiesSet(){
        System.out.println("bean初始化时调用");
    }

}
