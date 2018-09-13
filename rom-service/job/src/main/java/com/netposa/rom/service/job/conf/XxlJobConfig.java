package com.netposa.rom.service.job.conf;

import com.xxl.job.core.executor.XxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Configuration
@ComponentScan(basePackages = "com.netposa.rom.service.job.handler")
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Autowired
    private AppConfig appConfig;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(appConfig.getXxlJobAdminAddresses());
        xxlJobExecutor.setAppName(appConfig.getXxlJobExecutorAppname());
        xxlJobExecutor.setIp(appConfig.getXxlJobExecutorIp());
        xxlJobExecutor.setPort(appConfig.getXxlJobExecutorPort());
        xxlJobExecutor.setAccessToken(appConfig.getXlJobAccessToken());
        xxlJobExecutor.setLogPath(appConfig.getXxlJobExecutorLogpath());
        xxlJobExecutor.setLogRetentionDays(appConfig.getXxlJobExecutorLogretentiondays());
        return xxlJobExecutor;
    }

    public String getAdminAddresses() {
        return appConfig.getXxlJobAdminAddresses();
    }

    public String getAppName() {
        return appConfig.getXxlJobExecutorAppname();
    }
}