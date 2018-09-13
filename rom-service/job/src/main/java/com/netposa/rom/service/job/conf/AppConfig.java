package com.netposa.rom.service.job.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: AppConfig</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/7/26
 * @Company 东方网力
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {
    /**
     * xxl-job相关配置
     */
    private String xxlJobAdminAddresses;
    private String xxlJobExecutorAppname;
    private String xxlJobExecutorIp;
    private int xxlJobExecutorPort;
    private String xlJobAccessToken;
    private String xxlJobExecutorLogpath;
    private int xxlJobExecutorLogretentiondays;

}
