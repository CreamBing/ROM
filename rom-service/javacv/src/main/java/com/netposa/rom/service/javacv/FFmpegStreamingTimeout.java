package com.netposa.rom.service.javacv;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.avformat;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>Title: FFmpegStreamingTimeout</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/11
 * @Company 东方网力
 */
public class FFmpegStreamingTimeout {

    static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

    /**
     * There is no universal option for streaming timeout. Each of protocols has
     * its own list of options.
     */
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

    private static final String SOURCE_RTSP = "rtsp://admin:zb123456@192.168.14.77:554/h264/ch1/main/av_stream";
    private static final int TIMEOUT = 10; // In seconds.

    public static void main(String[] args) {
//        testShow();
        testRtsp();
//        rtspStreamingTest();
//        testWithCallback(); // This is not working properly. It's just for test.
    }

    private static void rtspStreamingTest() {
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(SOURCE_RTSP);
            /**
             * "timeout" - IS IGNORED when a network cable have been unplugged
             * before a connection and sometimes when connection is lost.
             *
             * "rw_timeout" - IS IGNORED when a network cable have been
             * unplugged before a connection but the option takes effect after a
             * connection was established.
             *
             * "stimeout" - works fine.
             */
            grabber.setOption(
                    TimeoutOption.STIMEOUT.getKey(),
                    String.valueOf(TIMEOUT * 1000000)
            ); // In microseconds.
//            grabber.setOption("rtsp_transport", "tcp");
            // 一直报错的原因！！！就是因为是 2560 * 1440的太大了。。
            grabber.setImageHeight(480);
            grabber.setImageWidth(860);
            grabber.start();

            Frame frame = null;
            /**
             * When network is disabled (before brabber was started) grabber
             * throws exception: "org.bytedeco.javacv.FrameGrabber$Exception:
             * avformat_open_input() error -138: Could not open input...".
             *
             * When connections is lost (after a few grabbed frames)
             * grabber.grab() returns null without exception.
             */
            while ((frame = grabber.grab()) != null) {
                System.out.println("frame grabbed at " + grabber.getTimestamp());
                opencv_core.Mat mat = converter.convertToMat(frame);
                opencv_imgcodecs.imwrite("C:\\Users\\bing\\Pictures\\Camera Roll\\zb\\" + grabber.getTimestamp() + ".jpg", mat);
                Thread.sleep(10000);
                System.out.println("成功截取");
            }
        } catch (Exception ex) {
            System.out.println("exception: " + ex);
        }
    }

    private static void testShow() {
        String rtspPath = "rtsp://admin:zb123456@192.168.14.77:554/live.sdp";
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtspPath);
        grabber.setImageHeight(480);
        grabber.setImageWidth(860);
        grabber.setOption("rtsp_transport", "tcp");
        CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setAlwaysOnTop(true);
        try {
            grabber.start();
            while (true) {
                if (!canvas.isDisplayable()) {//窗口是否关闭
                    grabber.stop();//停止抓取
                    System.exit(2);//退出
                }
                canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
                Thread.sleep(50);//50毫秒刷新一次图像
            }
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }

    private static void testRtsp() {
        try {
            // main 方法中的代码,非常简单
//        String rtspPath = "rtsp://admin:123123@192.168.1.64:554/Streaming/Channels/1";
//            String rtspPath = "rtsp://admin:zb123456@192.168.14.77:554/Streaming/Channels/1";
            String rtspPath = "rtsp://admin:zb123456@192.168.14.77:554/live.sdp";
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtspPath);
// grabber.setOption("rtsp_transport", "tcp"); 使用tcp的方式进行传输，网上找的资料，结果使用了这个以后，直接读取到50帧后就一直返回Null了。。这里把它注释，因为默认是使用udp的方式接收。就会出现以下的错误。而且每次都是在30帧的时候就报这些错误，好奇怪。
            grabber.setOption("rtsp_transport", "tcp");
            // 一直报错的原因！！！就是因为是 2560 * 1440的太大了。。
            grabber.setImageHeight(480);
            grabber.setImageWidth(860);
            grabber.setFrameRate(1);
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
                    System.out.println("frame is null " + i);
                    continue;
                }
                System.out.println(i + ":" + grabber.getFormat() + ":" + grabber.getFrameNumber());
                opencv_core.Mat mat = converter.convertToMat(frame);
                if (FaceDetectionUtils.faceDetectionWithoutPrint(conveter.convertToIplImage(frame)) != null) {
                    opencv_imgcodecs.imwrite("C:\\Users\\bing\\Pictures\\Camera Roll\\zb\\" + grabber.getTimestamp() + ".jpg", mat);
                    Thread.sleep(1000);
                    System.out.println("成功截取");
                }
            }
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }

    //    private static void ee() throws Exception{
//        opencv_objdetect.CvHaarClassifierCascade classifier = null;
//        opencv_core.CvMemStorage storage = null;
//        opencv_core.IplImage grabbedImage = null, grayImage = null, smallImage = null;
//        // Load the classifier file from Java resources.
//        String classiferName = "haarcascade_frontalface_alt.xml";
//        File classifierFile = Loader.extractResource(classiferName, null, "classifier", ".xml");
//        if (classifierFile == null || classifierFile.length() <= 0) {
//            throw new IOException("Could not extract \"" + classiferName + "\" from Java resources.");
//        }
//
//        // Preload the opencv_objdetect module to work around a known bug.
//        Loader.load(opencv_objdetect.class);
//        classifier = new opencv_objdetect.CvHaarClassifierCascade(cvLoad(classifierFile.getAbsolutePath()));
//        classifierFile.delete();
//        if (classifier.isNull()) {
//            throw new IOException("Could not load the classifier file.");
//        }
//
//        storage = opencv_core.CvMemStorage.create();
//        cvClearMemStorage(storage);
//        cvCvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
//        cvResize(grayImage, smallImage, CV_INTER_AREA);
//        faces = cvHaarDetectObjects(smallImage, classifier, storage, 1.1, 3,
//                CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH);
//        if (faces != null) {
//            repaint();
//        }
//    }
    private static void testWithCallback() {
        try {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(SOURCE_RTSP);
            /**
             * grabber.getFormatContext() is null before grabber.start().
             *
             * But if network is disabled grabber.start() will never return.
             *
             * That's why interrupt_callback not suitable for "network disabled
             * case".
             */
            grabber.start();

            final AtomicBoolean interruptFlag = new AtomicBoolean(false);
            avformat.AVIOInterruptCB.Callback_Pointer cp = new avformat.AVIOInterruptCB.Callback_Pointer() {
                @Override
                public int call(Pointer pointer) {
                    // 0 - continue, 1 - exit
                    int interruptFlagInt = interruptFlag.get() ? 1 : 0;
                    System.out.println("callback, interrupt flag == " + interruptFlagInt);
                    return interruptFlagInt;
                }

            };
            avformat.AVFormatContext oc = grabber.getFormatContext();
            avformat.avformat_alloc_context();
            avformat.AVIOInterruptCB cb = new avformat.AVIOInterruptCB();
            cb.callback(cp);
            oc.interrupt_callback(cb);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(TIMEOUT);
                    interruptFlag.set(true);
                    System.out.println("interrupt flag was changed");
                } catch (InterruptedException ex) {
                    System.out.println("exception in interruption thread: " + ex);
                }
            }).start();

            Frame frame = null;
            /**
             * On one of my RTSP cams grabber stops calling callback on
             * connection lost. I think it's has something to do with message:
             * "[swscaler @ 0000000029af49e0] deprecated pixel format used, make
             * sure you did set range correctly".
             *
             * So there is at least one case when grabber stops calling
             * callback.
             */
            while ((frame = grabber.grab()) != null) {
                System.out.println("frame grabbed at " + grabber.getTimestamp());
                opencv_core.Mat mat = converter.convertToMat(frame);
                opencv_imgcodecs.imwrite("C:\\Users\\bing\\Pictures\\Camera Roll\\zb\\" + grabber.getTimestamp() + ".jpg", mat);
                Thread.sleep(10000);
                System.out.println("成功截取");
            }
            System.out.println("loop end with frame: " + frame);
        } catch (Exception ex) {
            System.out.println("exception: " + ex);
        }
    }
}
