package com.netposa.rom.service.javacv.conf;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>Title: AppConf</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/12
 * @Company 东方网力
 */
@Data
@ConfigurationProperties(prefix = "app")
@Component
public class AppConf {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConf.class);

    /**
     * 单人脸存储路径
     */
    private String singleFaceSavePath;
    /**
     * 多人脸存储路径
     */
    private String facesSavePath;
    /**
     * 摄像机流地址
     */
    private String rstpUrl;
    /**
     * 抓拍图片截图宽度
     */
    private int imgW;
    /**
     * 抓拍图片截图高度
     */
    private int imgH;
    /**
     * 抓拍间隔
     */
    private int snapshotInterval;

    @PostConstruct
    public void printConf() {
        LOGGER.info("\napp配置加载完毕:" +
                        "\n\tsingleFaceSavePath:[{}]" +
                        "\n\tfacesSavePath:[{}]" +
                        "\n\trstpUrl:[{}]" +
                        "\n\timgW:[{}]" +
                        "\n\timgH:[{}]" +
                        "\n\tsnapshotInterval:[{}]", singleFaceSavePath,
                facesSavePath, rstpUrl,imgW,imgH,snapshotInterval);
    }
}
