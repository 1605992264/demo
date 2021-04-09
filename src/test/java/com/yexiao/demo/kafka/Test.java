package com.yexiao.demo.kafka;

import org.apache.kafka.clients.ClientUtils;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.NetworkClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xuhf
 * @date 2021/4/6 11:24
 **/
public class Test {

    public static void main(String[] args) {
        HashMap<String, Object> configMap = new HashMap<>();
        configMap.put("bootstrap.servers","localhost:9092");
        KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<>(configMap);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<String, Object>("testTopic","aaa","你好！");
        kafkaProducer.send(producerRecord);


        configMap.put("group.id","test");
        KafkaConsumer<String, Object> kafkaConsumer = new KafkaConsumer<>(configMap);
        kafkaConsumer.subscribe(Arrays.asList("testTopic","dealTopic"));

    }

}
