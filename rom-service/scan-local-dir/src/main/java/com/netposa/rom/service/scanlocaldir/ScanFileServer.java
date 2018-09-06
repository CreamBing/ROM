package com.netposa.rom.service.scanlocaldir;

import com.netposa.rom.service.scanlocaldir.conf.FtpFileConf;
import com.netposa.rom.service.scanlocaldir.listener.FtpFileListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ScanFileServer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ScanFileServer.class);

    @Autowired
    private FtpFileConf ftpFileConf;

    @Autowired
    private FtpFileListener visitor;

    @Autowired
    private ScanFileThreadServer scanFileThreadServer;


    @Override
    public void run(String... args) throws Exception {
        scanFileThreadServer.setFtpFileConf(ftpFileConf);
        scanFileThreadServer.setVisitor(visitor);
        Thread thread = new Thread(scanFileThreadServer);
        thread.start();
        log.info("开始扫描文件");
    }
}
