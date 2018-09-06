package com.netposa.rom.service.scanlocaldir.conf;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ConfigurationProperties(prefix = "ftp")
@Component
@Data
public class FtpFileConf {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpFileConf.class);

    private String imagePath;
    private String fileSuffix;
    private String okPath;

    @PostConstruct
    private void printConf(){
        LOGGER.info("\nftp 文件监听配置加载完成:" +
                "\n\tftpImagePath[{}]" +
                "\n\tfileSuffix[{}]" +
                "\n\tokPath[{}]",imagePath,fileSuffix,okPath);
    }
}
