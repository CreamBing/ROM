package com.netposa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author creambing
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.netposa.rom.service.javacv"})
@EnableConfigurationProperties
public class JavaCvApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCvApplication.class, args);
    }
}
