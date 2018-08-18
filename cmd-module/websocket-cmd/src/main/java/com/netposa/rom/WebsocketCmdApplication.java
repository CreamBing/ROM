package com.netposa.rom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title: WebsocketCmdApplication</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/8/16
 * @Company 东方网力
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.netposa.rom.cmd.websocket","com.netposa.rom.common.redis",
"com.netposa.rom.common.kafka"})
@EnableConfigurationProperties
public class WebsocketCmdApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsocketCmdApplication.class, args);
    }
}
