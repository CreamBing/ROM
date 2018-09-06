package com.netposa.rom.service.scanlocaldir;

import com.netposa.rom.service.zimg.ZimgService;
import com.netposa.rom.service.zimg.impl.ZimgAndMysqlServiceImpl;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class Img2ZimgThreadServer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Img2ZimgThreadServer.class);

    ZimgService zimgService = SpringContextUtils.getBean(ZimgAndMysqlServiceImpl.class);

    private String filePathStr;

    public Img2ZimgThreadServer(String filePathStr) {
        this.filePathStr = filePathStr;
    }

    public Img2ZimgThreadServer() {
    }

    @Override
    public void run() {
        Path filePath = Paths.get(filePathStr);
        if (!Files.exists(filePath)) {
            LOGGER.error("[{}]文件不存在", filePath);
            return;
        }
        try {
            zimgService.upload(filePathStr);
            LOGGER.info("[{}]上传成功",filePathStr);
            Thread.sleep(2000);
        } catch (Exception e) {
            LOGGER.error("[{}]上传zimg失败:[{}]",filePathStr,e.getMessage());
        }
    }
}
