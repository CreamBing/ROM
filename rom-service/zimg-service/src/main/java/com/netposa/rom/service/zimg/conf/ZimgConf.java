package com.netposa.rom.service.zimg.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "zimg")
@Component
public class ZimgConf {

    private String serverUrl;

}
