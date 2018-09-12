package com.netposa.rom.service.javacv.thread;

import com.netposa.rom.service.javacv.FaceDetectionUtils;
import com.netposa.rom.service.javacv.ImageFace;
import com.netposa.rom.service.javacv.conf.AppConf;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Title: FFmpegStreamingFromRstpThread</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/12
 * @Company 东方网力
 */
@Component
public class FFmpegStreamingFromRstpThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FFmpegStreamingFromRstpThread.class);

    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

    @Autowired
    AppConf appConf;

    @Override
    public void run() {
        try {
            testRtsp();
        } catch (Exception e) {
            LOGGER.error("抓拍异常");
        }
    }

    private void testRtsp() {
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(appConf.getRstpUrl());
            grabber.setOption("rtsp_transport", "tcp");
//            grabber.setOption(
//                    TimeoutOption.STIMEOUT.getKey(),
//                    String.valueOf(appConf.getSnapshotInterval()*2*1000000)
//            );
            grabber.setImageHeight(appConf.getImgH());
            grabber.setImageWidth(appConf.getImgW());
            grabber.start();
            OpenCVFrameConverter.ToIplImage conveter = new OpenCVFrameConverter.ToIplImage();
            for (int i = 1; true; i++) {
                Frame frame = null;
                while (true) {
                    if (i % 25 == 0) {
                        break;
                    }
                    grabber.grabFrame();
                    i++;
                }
                frame = grabber.grabFrame();
                if (frame == null) {
                    LOGGER.error("frame is null " + i);
                    continue;
                }
                LOGGER.info(i + ":" + grabber.getFormat() + ":" + grabber.getFrameNumber());
                opencv_core.Mat mat = converter.convertToMat(frame);
                ImageFace<Long> faces = FaceDetectionUtils.faceDetectionWithoutPrint(conveter.convertToIplImage(frame));
                if (faces != null) {
                    if (faces.getCount() == 1) {
                        opencv_imgcodecs.imwrite(appConf.getSingleFaceSavePath() + grabber.getTimestamp() + ".jpg", mat);
                    } else if (faces.getCount() > 1) {
                        opencv_imgcodecs.imwrite(appConf.getFacesSavePath() + grabber.getTimestamp() + ".jpg", mat);
                    }
                    if(i>999999999){
                        i=1;//重置i//间隔截取这里还是有些问题如果Thread.sleep()的话,这里的流停掉了，然后一段时间后就会取不到帧
                    }
//                    Thread.sleep(appConf.getSnapshotInterval()*1000);
                    LOGGER.info("成功截取");
                }
            }
        } catch (Exception e) {
            LOGGER.error("exception: " + e);
        }
    }

    private static enum TimeoutOption {
        /**
         * Depends on protocol (FTP, HTTP, RTMP, SMB, SSH, TCP, UDP, or UNIX).
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html
         */
        TIMEOUT,
        /**
         * Protocols
         * <p>
         * Maximum time to wait for (network) read/write operations to complete,
         * in microseconds.
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html#Protocols
         */
        RW_TIMEOUT,
        /**
         * Protocols -> RTSP
         * <p>
         * Set socket TCP I/O timeout in microseconds.
         * <p>
         * http://ffmpeg.org/ffmpeg-all.html#rtsp
         */
        STIMEOUT;

        public String getKey() {
            return toString().toLowerCase();
        }

    }
}
