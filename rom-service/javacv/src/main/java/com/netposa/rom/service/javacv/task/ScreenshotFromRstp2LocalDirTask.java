package com.netposa.rom.service.javacv.task;

import com.netposa.rom.service.javacv.thread.FFmpegStreamingFromRstpThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p>Title: ScreenshotFromRstp2LocalDirTask</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/12
 * @Company 东方网力
 */
@Component
public class ScreenshotFromRstp2LocalDirTask implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotFromRstp2LocalDirTask.class);

    @Autowired
    FFmpegStreamingFromRstpThread fFmpegStreamingFromRstpThread;

    @Override
    public void run(String... args) throws Exception {
        Thread thread = new Thread(fFmpegStreamingFromRstpThread);
        thread.start();
        LOGGER.info("开始截图");
    }
}
