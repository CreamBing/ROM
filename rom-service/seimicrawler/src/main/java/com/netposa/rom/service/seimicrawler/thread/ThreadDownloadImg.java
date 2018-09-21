package com.netposa.rom.service.seimicrawler.thread;


import com.netposa.rom.service.seimicrawler.utils.FileUrlConvertUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

public class ThreadDownloadImg implements Runnable {

    public ThreadDownloadImg(String strUrl) {
        this.strUrl = strUrl;
    }

    public ThreadDownloadImg() {
    }

    private String strUrl;
    String loadurl = "C:\\Users\\bing\\Pictures\\Camera Roll\\zb\\sun";

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadDownloadImg.class);

    @Override
    public void run() {
        URL url;
        try {
            url = new URL(strUrl);
            String path = url.getPath();
            String filename = path.substring(path.lastIndexOf('/') + 1);
            File file = new File(loadurl + System.getProperty("file.separator") + filename);
            FileUtils.copyURLToFile(url, file);
//            File file = new File(loadurl+System.getProperty("file.separator")+System.currentTimeMillis()+".jpg");
//            FileUrlConvertUtils fileUrlUtils = new FileUrlConvertUtils();
//            byte[] fileBytes = fileUrlUtils.loadFileByteFromURL(strUrl);
//            FileUtils.writeByteArrayToFile(file,fileBytes);
            LOGGER.info("[{}]下载图片", strUrl);
        } catch (Exception e2) {
            LOGGER.error("[{}]下载图片异常，新建URL失败,原因:[{}]", strUrl, e2.getMessage());
        }
    }
}
