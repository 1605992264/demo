package com.yexiao.demo.conf.kafka;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author xuhf
 * @date 2021/4/6 15:09
 **/
@Component
public class DefaultConsumerRebalanceListener implements ConsumerRebalanceListener {

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("------------------------------");
        System.out.println(partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("------------------------------");
        System.out.println(partitions);
    }
}
