package com.yexiao.demo.conf.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author xuhf
 * @date 2021/4/6 15:15
 **/
@ConfigurationProperties(prefix = "spring.kafka")
@Component
public class MyKafkaProperties {

    private List<String> bootstrapServers = new ArrayList<>(
            Collections.singletonList("localhost:9092"));

    private Consumer consumer = new Consumer();
    private Producer producer = new Producer();


    public Map<String,Object> buildAdminClientProperties(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bootstrap.servers",this.bootstrapServers);
        return hashMap;
    }

    public Map<String,Object> buildConsumerProperties(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bootstrap.servers",this.bootstrapServers);
        hashMap.put("group.id",consumer.groupId);
        hashMap.put("enable.auto.commit",consumer.enableAutoCommit);
        hashMap.put("auto.commit.interval.ms",consumer.autoCommitInterval);
        hashMap.put("key.deserializer",consumer.keyDeserializer);
        hashMap.put("value.deserializer",consumer.valueDeserializer);
        return hashMap;
    }

    public Map<String,Object> buildProducerProperties(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("bootstrap.servers",this.bootstrapServers);
        hashMap.put("acks",producer.acks);
        hashMap.put("retries",producer.retries);
        hashMap.put("batch.size",producer.batchSize);
        hashMap.put("buffer.memory",producer.bufferMemory);
        //hashMap.put("linger.ms",1);
        hashMap.put("key.serializer",producer.keySerializer);
        hashMap.put("value.serializer",producer.valueSerializer);
        return hashMap;
    }

    public static class Consumer{
        private String groupId = "test";
        private boolean enableAutoCommit = false;
        private Integer autoCommitInterval = 1000;
        private Class keyDeserializer = StringDeserializer.class;
        private Class valueDeserializer = StringDeserializer.class;

        public boolean isEnableAutoCommit() {
            return enableAutoCommit;
        }

        public void setEnableAutoCommit(boolean enableAutoCommit) {
            this.enableAutoCommit = enableAutoCommit;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public Integer getAutoCommitInterval() {
            return autoCommitInterval;
        }

        public void setAutoCommitInterval(Integer autoCommitInterval) {
            this.autoCommitInterval = autoCommitInterval;
        }

        public Class getKeyDeserializer() {
            return keyDeserializer;
        }

        public void setKeyDeserializer(Class keyDeserializer) {
            this.keyDeserializer = keyDeserializer;
        }

        public Class getValueDeserializer() {
            return valueDeserializer;
        }

        public void setValueDeserializer(Class valueDeserializer) {
            this.valueDeserializer = valueDeserializer;
        }
    }

    public static class Producer{

        private String acks = "all";
        private Integer retries = 3;
        private Integer batchSize = 16384;
        private Long bufferMemory = 33554432L;

        private Class<?> keySerializer = StringSerializer.class;
        private Class<?> valueSerializer = StringSerializer.class;

        public String getAcks() {
            return acks;
        }

        public void setAcks(String acks) {
            this.acks = acks;
        }

        public Integer getRetries() {
            return retries;
        }

        public void setRetries(Integer retries) {
            this.retries = retries;
        }

        public Integer getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(Integer batchSize) {
            this.batchSize = batchSize;
        }

        public Long getBufferMemory() {
            return bufferMemory;
        }

        public void setBufferMemory(Long bufferMemory) {
            this.bufferMemory = bufferMemory;
        }

        public Class<?> getKeySerializer() {
            return keySerializer;
        }

        public void setKeySerializer(Class<?> keySerializer) {
            this.keySerializer = keySerializer;
        }

        public Class<?> getValueSerializer() {
            return valueSerializer;
        }

        public void setValueSerializer(Class<?> valueSerializer) {
            this.valueSerializer = valueSerializer;
        }
    }


    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public List<String> getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(List<String> bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }
}
