package com.netposa.rom.service.javacv;


import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.opencv_objdetect;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.destroyAllWindows;
import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgcodecs.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.*;

/**
 * <p>Title: FaceDetectionUtils</p>
 * <p>Description: 人脸检测工具类:opencv实现类</p>
 *
 * @author bing
 * @date 2018/9/12
 * @Company 东方网力
 */
public class FaceDetectionUtils {

    private static CvHaarClassifierCascade classifier = null;
    private static CascadeClassifier classifier1 = null;
    private static CvMemStorage storage = null;
    private static Exception exception = null;
    private static String classiferName = "/conf/haarcascade_frontalface_alt.xml";

    static {
        try {
            // Load the classifier file from Java resources.
            File classifierFile = Loader.extractResource(classiferName, null, "classifier", ".xml");
            if (classifierFile == null || classifierFile.length() <= 0) {
                throw new IOException("Could not extract \"" + classiferName + "\" from Java resources.");
            }
            // Preload the opencv_objdetect module to work around a known bug.
            Loader.load(opencv_objdetect.class);
//            classifier = new CvHaarClassifierCascade(cvLoad(classifierFile.getAbsolutePath()));
//            classifierFile.delete();
//            if (classifier.isNull()) {
//                throw new IOException("Could not load the classifier file.");
//            }
//            storage = CvMemStorage.create();

            classifier1 = new CascadeClassifier(classifierFile.getAbsolutePath());
        } catch (Exception e) {
            if (exception == null) {
                exception = e;
//                repaint();
            }
        }
    }

    public static ImageFace faceDetection(IplImage grabbedImage) {
        IplImage grayImage = IplImage.create(grabbedImage.width(), grabbedImage.height(), IPL_DEPTH_8U, 1);
        IplImage smallImage = IplImage.create(grabbedImage.width() / 4, grabbedImage.height() / 4, IPL_DEPTH_8U, 1);
        cvClearMemStorage(storage);
        cvCvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
        cvResize(grayImage, smallImage, CV_INTER_AREA);
        CvSeq faces = cvHaarDetectObjects(smallImage, classifier, storage, 1.1, 3,
                CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH);
        List<CvRect> faceRect = new ArrayList<>();
        ImageFace imageFace = new ImageFace();
        if (faces != null) {
            int total = faces.total();
            if (total > 0) {
                for (int i = 0; i < total; i++) {
                    CvRect r = new CvRect(cvGetSeqElem(faces, i));
                    faceRect.add(r);
                }
                imageFace.setCount(total);
                imageFace.setFaceRects(faceRect);
                return imageFace;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static ImageFace faceDetection1(IplImage grabbedImage1) {
        Mat grabbedImage = new Mat(grabbedImage1);
        Mat grayImage = new Mat(grabbedImage1.height(), grabbedImage1.width(), CV_8UC1);
        cvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
        RectVector faces = new RectVector();
        classifier1.detectMultiScale(grayImage, faces,
                1.1, 3, CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH, null, null);
        List<CvRect> faceRect = new ArrayList<>();
        ImageFace<Long> imageFace = new ImageFace<>();
        Point hatPoints = new Point(3);
        if (faces != null) {
            long  total = faces.size();
            if (total > 0) {
                for (int i = 0; i < total; i++) {
                    Rect r1 = faces.get(i);
                    CvRect r = new CvRect(r1);
                    faceRect.add(r);
                    int x = r.x(), y = r.y(), w = r.width(), h = r.height();
                    rectangle(grabbedImage, new Point(x, y), new Point(x + w, y + h), Scalar.RED, 1, CV_AA, 0);

                    // To access or pass as argument the elements of a native array, call position() before.
                    hatPoints.position(0).x(x - w / 10     ).y(y - h / 10);
                    hatPoints.position(1).x(x + w * 11 / 10).y(y - h / 10);
                    hatPoints.position(2).x(x + w / 2      ).y(y - h / 2 );
                    fillConvexPoly(grabbedImage, hatPoints.position(0), 3, Scalar.GREEN, CV_AA, 0);
                }
                threshold(grayImage, grayImage, 64, 255, CV_THRESH_BINARY);

                // To check if an output argument is null we may call either isNull() or equals(null).
                MatVector contours = new MatVector();
                findContours(grayImage, contours, CV_RETR_LIST, CV_CHAIN_APPROX_SIMPLE);
                long n = contours.size();
                for (long i = 0; i < n; i++) {
                    Mat contour = contours.get(i);
                    Mat points = new Mat();
                    approxPolyDP(contour, points, arcLength(contour, true) * 0.02, true);
                    drawContours(grabbedImage, new MatVector(points), -1, Scalar.BLUE);
                }
                while (true){
                    imshow("face_recognizer", grabbedImage);
                    char key = (char) waitKey(20);
                    // Exit this loop on escape:
                    if (key == 27) {
                        destroyAllWindows();
                        break;
                    }
                }
                imageFace.setCount(total);
                imageFace.setFaceRects(faceRect);
                return imageFace;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public static ImageFace<Long> faceDetectionWithoutPrint(IplImage grabbedImage1) {
        Mat grabbedImage = new Mat(grabbedImage1);
        Mat grayImage = new Mat(grabbedImage1.height(), grabbedImage1.width(), CV_8UC1);
        cvtColor(grabbedImage, grayImage, CV_BGR2GRAY);
        RectVector faces = new RectVector();
        classifier1.detectMultiScale(grayImage, faces,
                1.1, 3, CV_HAAR_FIND_BIGGEST_OBJECT | CV_HAAR_DO_ROUGH_SEARCH, null, null);
        List<CvRect> faceRect = new ArrayList<>();
        ImageFace<Long> imageFace = new ImageFace<>();
        if (faces != null) {
            long  total = faces.size();
            if (total > 0) {
                for (int i = 0; i < total; i++) {
                    Rect r1 = faces.get(i);
                    CvRect r = new CvRect(r1);
                    faceRect.add(r);
                }
                imageFace.setCount(total);
                imageFace.setFaceRects(faceRect);
                return imageFace;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        //single face
//        String fileName = "C:\\Users\\bing\\Pictures\\Camera Roll\\singleface\\527041000.jpg";
        //faces
        String fileName = "C:\\Users\\bing\\Pictures\\Camera Roll\\faces\\459314000.jpg";
        IplImage testImage = cvLoadImage(fileName);
        ImageFace imageFace = faceDetection1(testImage);
        if (imageFace != null) {
            System.out.println(imageFace.getCount());
        }
    }

}
