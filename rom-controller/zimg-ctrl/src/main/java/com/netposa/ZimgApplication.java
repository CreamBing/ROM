package com.netposa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.netposa.rom.ctrl.zimg", "com.netposa.rom.service.zimg"})
@EnableConfigurationProperties
public class ZimgApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZimgApplication.class, args);
    }
}
