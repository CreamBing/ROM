package com.netposa.rom.service.scanlocaldir;

import com.netposa.rom.service.scanlocaldir.conf.FtpFileConf;
import com.netposa.rom.service.scanlocaldir.listener.FtpFileListener;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

@Component
@Data
public class ScanFileThreadServer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ScanFileThreadServer.class);

    private FtpFileConf ftpFileConf;

    private FtpFileListener visitor;



    @Override
    public void run() {
        ThreadPoolExecutor executor = ThreadPool.getInstance();
        while (true) {
            try {
                Path fileDir = Paths.get(ftpFileConf.getImagePath());
                Files.walkFileTree(fileDir, visitor);
                BlockingQueue<File> queue = visitor.getFileQueues();
                log.debug("扫描文件:" + queue.size());
                if (queue.size() > 0) {
                    //TODO 处理 测试
                    queue.forEach(q -> {
                        Img2ZimgThreadServer img2ZimgThreadServer = new Img2ZimgThreadServer(q.getPath());
                        executor.execute(img2ZimgThreadServer);
                        queue.poll();
                    });
                }
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                log.error("文件线程扫描出现异常:原因[{}]", e.getMessage());
            }
        }
    }
}
