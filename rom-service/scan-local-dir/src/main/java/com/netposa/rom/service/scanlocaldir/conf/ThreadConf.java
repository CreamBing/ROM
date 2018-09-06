package com.netposa.rom.service.scanlocaldir.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "thread")
@Component
@Data
public class ThreadConf {
    private int corePoolSize;
    private int maximumPoolSizeSize;
    private long keepAliveTime;
    private int queueNum;
}
