package com.netposa.rom.service.scanlocaldir.listener;

import com.netposa.rom.service.scanlocaldir.conf.FtpFileConf;
import com.netposa.rom.service.scanlocaldir.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.BlockingQueue;

@Component
public class FtpFileListener extends SimpleFileVisitor<Path> {

    private static Logger log = LoggerFactory.getLogger(FileVisitor.class);

    @Autowired
    private FtpFileConf conf;

    private BlockingQueue<File> fileQueues = new Constants().getFileQueues();

    public BlockingQueue<File> getFileQueues() {
        return fileQueues;
    }

    // 在访问子目录前触发该方法
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (!file.toString().endsWith(conf.getFileSuffix())) {
            String fileName = file.toString();
            String fileP = file.toFile().getParentFile().getParent();
            if(!Files.exists(Paths.get(fileP))){
                log.error("ftp 扫描目录至少要有一级!");
                System.exit(-1);
            }
            String okpath = fileP+File.separator+conf.getOkPath();
            Path okPaths = Paths.get(okpath);
            Path targatPath = Paths.get(okpath+File.separator+file.getFileName());
            log.debug(file.toFile().getPath());
            try {
                if(!Files.exists(okPaths)){
                    Files.createDirectories(okPaths);
                }
                Files.move(file,targatPath,StandardCopyOption.REPLACE_EXISTING);
                log.debug("[{}]文件已经放入队列中", fileName);
                fileQueues.add(targatPath.toFile());
            } catch (IOException e) {
                log.error("[{}]文件移动到ok目录下出现异常[{}]",fileName,e.getMessage());
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
