package com.yexiao.demo.conf.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuhf
 * @date 2021/4/7 9:53
 **/
public class KafkaPubListenerThread extends Thread{

    private KafkaConsumer kafkaConsumer;
    private AtomicBoolean close = new AtomicBoolean(false);


    public KafkaPubListenerThread(KafkaConsumer kafkaConsumer){
        this.kafkaConsumer = kafkaConsumer;
    }


    @Override
    public void run() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (!close.get()){
            ConsumerRecords poll = kafkaConsumer.poll(10000);
            if(!poll.isEmpty()){
                Iterator<ConsumerRecord> iterator = poll.iterator();
                while (iterator.hasNext()){
                    ConsumerRecord next = iterator.next();
                    try {
                        if("1".equals(next.value()) || "99".equals(next.value())){
                            System.out.println("读取失败！");
                            throw new RuntimeException("111111");
                        }
                        System.out.println("读取到的内容：" + next);
                        atomicInteger.set(0);
                    }catch (Exception e){
                        if(atomicInteger.get() < 3){
                            kafkaConsumer.seek(new TopicPartition(next.topic(),next.partition()),next.offset());
                            atomicInteger.set(atomicInteger.get()+1);
                            break;
                        }else {
                            System.out.println("重试超过3次，存到本地记录:" + next);
                        }
                        //kafkaConsumer.commitAsync();
                    }
                }
                //kafkaConsumer.commitAsync();
            }
        }
        super.run();
    }



}
