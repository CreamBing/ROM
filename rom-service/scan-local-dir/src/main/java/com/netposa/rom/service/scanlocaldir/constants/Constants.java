package com.netposa.rom.service.scanlocaldir.constants;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Constants {

    public BlockingQueue<File> fileQueues = new ArrayBlockingQueue<File>(10000);

    public BlockingQueue<File> getFileQueues() {
        return this.fileQueues;
    }

}
