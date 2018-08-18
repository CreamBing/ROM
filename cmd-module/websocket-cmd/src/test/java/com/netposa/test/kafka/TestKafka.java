package com.netposa.test.kafka;

import com.netposa.rom.WebsocketCmdApplication;
import com.netposa.rom.cmd.websocket.conf.AppConf;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Title: TestKafka</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/17
 * @Company 东方网力
 */
@SpringBootTest(classes = WebsocketCmdApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestKafka {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestKafka.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    AppConf appConf;

    @Test
    public void sendSocketCmd(){
        String s = "{\n" +
                "\t\"msgType\": \"MT_MSG_CMD_CONTROL_REQ\",\n" +
                "\t\"requestId\": \"xxxx\",\n" +
                "\t\"robotCode\": \"511290188668014\",\n" +
                "\t\"cmds\": {\n" +
                "\t\t\"cmdLocation\": {\n" +
                "\t\t\t\"correctionType\":1,\n" +
                "\t\t\t\"longitude\": 116.35,\n" +
                "\t\t\t\"latitude\": 33.25\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        kafkaTemplate.send(appConf.getKafkaSocketCmdTopic(),s);
        LOGGER.info("消费发送成功!");
    }
}
