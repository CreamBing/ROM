package com.netposa.rom.access.websocket.check;

import com.netposa.rom.access.websocket.WebSocketOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.Configuration;

/**
 * <p>Title: WebSocketHealthIndicator</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/20
 * @Company 东方网力
 */
@Component
public class WebSocketHealthIndicator implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHealthIndicator.class);

    @Autowired
    WebSocketOperation webSocketOperation;

    @Override
    public Health health() {
        Configuration conf = webSocketOperation.getConf();
        int size = webSocketOperation.getSocketClientSize();
        int port = conf.getPort();
        String hostname = conf.getHostname();
        if (StringUtils.isBlank(hostname)) {
            hostname = "";
        }
        if (size < 1) {
            return Health.down().withDetail("hostname", hostname).withDetail("port", port).withDetail("clients",size).build();
        } else {
            return Health.up().withDetail("hostname", hostname).withDetail("port", port).withDetail("clients",size).build();
        }
    }
}
