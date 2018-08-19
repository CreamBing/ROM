package com.netposa.rom.access.websocket.kafka;

import com.netposa.rom.access.websocket.conf.AppConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title: KafkaCmdConsumer</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/17
 * @Company 东方网力
 */
@Component
public class KafkaCmdConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCmdConsumer.class);

    @Autowired
    AppConf appConf;

    @KafkaListener(topics = "${app.kafkaSocketCmdTopic}",containerFactory = "jsonKafkaListenerContainerFactory")
    public void listenSocketCmds(List<String> cmds){
        LOGGER.info("topic[{}]开始消费",appConf.getKafkaSocketCmdTopic());
        cmds.forEach(System.out::println);
        LOGGER.info("topic[{}]结束消费",appConf.getKafkaSocketCmdTopic());
    }
}
