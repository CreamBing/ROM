package com.netposa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.netposa.rom.service.job"})
public class JobServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(JobServerApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(JobServerApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
