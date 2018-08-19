package com.netposa.rom.access.websocket.conf;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>Title: AppConf</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/16
 * @Company 东方网力
 */
@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppConf {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConf.class);

    private int socketPort;
    private String socketClientEvent;

    private String kafkaSocketCmdTopic;

    @PostConstruct
    private void initConf() {
        LOGGER.info("\napp 远程配置加载完成:" +
                        "\n\t\tsocketPort:{}" +
                        "\n\t\tsocketClientEvent:{}"+
                        "\n\t\tkafkaSocketCmdTopic:{}"
                , socketPort, socketClientEvent,kafkaSocketCmdTopic);
    }
}
