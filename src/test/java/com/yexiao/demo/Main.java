package com.yexiao.demo;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.util.VersionUtil;
import com.yexiao.demo.base.domain.BaseEntity;
import com.yexiao.demo.base.domain.UserInfoBaseEntity;
import com.yexiao.demo.base.tree.CreateTreeData;
import com.yexiao.demo.base.tree.TreeNode;
import com.yexiao.demo.conf.kafka.MyKafkaProperties;
import com.yexiao.demo.domain.UserDO;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.config.YamlProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.json.YamlJsonParser;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Version;
import org.yaml.snakeyaml.Yaml;
import sun.util.resources.zh.TimeZoneNames_zh_CN;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuhf
 * @date 2020/8/21 16:55
 **/
public class Main {




    public static void main(String[] args) throws Exception {
        MyKafkaProperties myKafkaProperties = new MyKafkaProperties();
        KafkaProducer kafkaProducer = new KafkaProducer(myKafkaProperties.buildProducerProperties());
        for(int i=0;i<1000;i++){
            ProducerRecord<String, Object> producerRecord = new ProducerRecord<>("testTopic", String.valueOf(i));
            kafkaProducer.send(producerRecord);
        }
    }


}




