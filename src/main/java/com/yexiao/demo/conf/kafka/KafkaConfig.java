package com.yexiao.demo.conf.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author xuhf
 * @date 2021/4/6 15:07
 **/
@Configuration
public class KafkaConfig implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    DefaultConsumerRebalanceListener consumerRebalanceListener;

    @Bean
    public KafkaConsumer kafkaConsumer(MyKafkaProperties kafkaProperties){
        KafkaConsumer kafkaConsumer = new KafkaConsumer(kafkaProperties.buildConsumerProperties());
        return kafkaConsumer;
    }

    @Bean
    public AdminClient adminClient(MyKafkaProperties kafkaProperties){
        AdminClient adminClient = KafkaAdminClient.create(kafkaProperties.buildAdminClientProperties());
        return adminClient;
    }

    @Bean
    public KafkaConsumer kafkaConsumerTwo(MyKafkaProperties kafkaProperties){
        Map<String, Object> configs = kafkaProperties.buildConsumerProperties();
//        configs.put("group.id","test2");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(configs);
        return kafkaConsumer;
    }

    @Bean
    public KafkaProducer kafkaProducer(MyKafkaProperties kafkaProperties){
        KafkaProducer kafkaProducer = new KafkaProducer(kafkaProperties.buildProducerProperties());
        return kafkaProducer;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

//        KafkaConsumer kafkaConsumer = (KafkaConsumer)event.getApplicationContext().getBean("kafkaConsumer");
//        kafkaConsumer.subscribe(Arrays.asList("testTopic"),consumerRebalanceListener);
//        new KafkaPubListenerThread(kafkaConsumer).start();

//        KafkaConsumer kafkaConsumerTwo = (KafkaConsumer)event.getApplicationContext().getBean("kafkaConsumerTwo");
//        kafkaConsumerTwo.subscribe(Arrays.asList("testTopic"),consumerRebalanceListener);
//        new KafkaPubListenerThread(kafkaConsumerTwo).start();


    }
}
