package com.netposa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.netposa.rom.service.scanlocaldir", "com.netposa.rom.service.zimg"})
@EnableConfigurationProperties
public class ScanningLocalDirApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScanningLocalDirApplication.class, args);
    }
}
