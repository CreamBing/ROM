package com.netposa.test.kafka;

import com.netposa.rom.WebsocketAccessApplication;
import com.netposa.rom.access.websocket.conf.AppConf;
import org.apache.kafka.clients.KafkaClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

/**
 * <p>Title: TestKafka</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/17
 * @Company 东方网力
 */
@SpringBootTest(classes = WebsocketAccessApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class TestKafka {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestKafka.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    AppConf appConf;

    @Autowired
    ConsumerFactory consumerFactory;

    @Test
    public void sendSocketCmd() {
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

    @Test
    public void getMetrics(){
        Map map =kafkaTemplate.metrics();
        System.out.println(map.size());
    }

    @Test
    public void getConsumerMetric(){
        Map<MetricName,Metric> map =consumerFactory.createConsumer().metrics();
//        MetricName metricName = new MetricName();
        Set<Map.Entry<MetricName,Metric>> mapEntry = map.entrySet();
        mapEntry.forEach(e -> System.out.println(e.getValue().metricName().name()+"***"+e.getValue().metricValue().toString()));
        String value = mapEntry.stream().filter(e -> e.getValue().metricName().name().equals("version")).findFirst().get().getValue().metricValue().toString();
        long lastHeartbeatSeconds = Double.valueOf(mapEntry.stream().filter(e -> e.getValue().metricName().name().equals("last-heartbeat-seconds-ago")).findFirst().get().getValue().metricValue().toString()).longValue();
        System.out.println(System.currentTimeMillis()/1000 - lastHeartbeatSeconds);
    }

    @Test
    public void getKafkaClient(){
        KafkaConsumer kafkaConsumer = (KafkaConsumer) consumerFactory.createConsumer();
    }

//    @Test
//    public void getNetworkClient(){
//        Boolean isConnected = kafkaClient.isReady(node,System.currentTimeMillis());
//        System.out.println(isConnected);
//    }

}
