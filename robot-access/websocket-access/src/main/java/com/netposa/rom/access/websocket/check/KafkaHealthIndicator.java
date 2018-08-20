/**
 * creambing.com Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */


package com.netposa.rom.access.websocket.check;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Class Name:CustomHealthIndicator
 * Description:自定义健康检查
 *
 * @author Bing
 * @version v1.0
 * @create 2018-08-19  14:37
 */
@Component
public class KafkaHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaHealthIndicator.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Qualifier("consumerFactory")
    @Autowired
    ConsumerFactory consumer;

    @Autowired
    ProducerFactory producerFactory;

    @Qualifier("kafkaListenerContainerFactory")
    @Autowired
    ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;


    @Override
    public Health health() {
        KafkaHealthBean kafkaHealthBean = getKafkaConsumerHealth();
        if (kafkaHealthBean == null||!kafkaHealthBean.getIsConnected()) {
            return Health.down().build();
        } else {
            return Health.up().withDetail("version", kafkaHealthBean.getDetails().getVersion())
                    .withDetail("connectionCount", kafkaHealthBean.getDetails().getConnectionCount())
                    .withDetail("lastHeartbeatSeconds", kafkaHealthBean.getDetails().getLastHeartbeatSeconds()).build();
        }
    }

    private KafkaHealthBean getKafkaConsumerHealth() {
        try {
            Map<MetricName, Metric> map = consumer.createConsumer().metrics();
            Set<Map.Entry<MetricName, Metric>> mapEntry = map.entrySet();
            String version = mapEntry.stream().filter(e -> e.getValue().metricName().name().equals("version")).findFirst().get().getValue().metricValue().toString();
            String connectionCount = mapEntry.stream().filter(e -> e.getValue().metricName().name().equals("connection-count")).findFirst().get().getValue().metricValue().toString();
            long lastHeartbeatSeconds = System.currentTimeMillis() / 1000 - Double.valueOf(mapEntry.stream().filter(e -> e.getValue().metricName().name().equals("last-heartbeat-seconds-ago")).findFirst().get().getValue().metricValue().toString()).longValue();
            KafkaHealthBean kafkaHealthBean = new KafkaHealthBean();
            KafkaHealthBean.Details details = new KafkaHealthBean.Details();
            details.setVersion(version);
            details.setConnectionCount(connectionCount);
            details.setLastHeartbeatSeconds(lastHeartbeatSeconds);
            kafkaHealthBean.setDetails(details);
            kafkaHealthBean.setIsConnected(isKafkaAble());
            return kafkaHealthBean;
        } catch (Exception e) {
            LOGGER.error("kafka消费者健康检查获取异常:[{}]", e.getMessage());
            return null;
        }
    }

    private boolean isKafkaAble() {
        boolean isConnected = true;
        try {
            consumer.createConsumer().listTopics();
        } catch (Exception e) {
            LOGGER.error("kafka获取topic异常,说明kafka服务端异常,原因:[{}]", e.getMessage());
            isConnected = false;
        }
        return isConnected;
    }

}
