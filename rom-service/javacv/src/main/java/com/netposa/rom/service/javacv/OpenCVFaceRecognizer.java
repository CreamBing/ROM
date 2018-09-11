package com.netposa.rom.service.javacv;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacpp.opencv_face.FaceRecognizer;
import org.bytedeco.javacpp.opencv_face.FisherFaceRecognizer;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;
import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

/**
 * <p>Title: OpenCVFaceRecognizer</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/11
 * @Company 东方网力
 */
public class OpenCVFaceRecognizer {

    public static void main(String[] args) {
        String trainingDir = "C:\\Users\\bing\\Pictures\\Camera Roll\\traingakki";
        //gakki
        String testimag = "C:\\Users\\bing\\Pictures\\Camera Roll\\testgakki\\0a86a68f8c5494ee717f3aea24f5e0fe98257eb8.jpg";
        String testimagzb = "C:\\Users\\bing\\Pictures\\Camera Roll\\testgakki\\24400000.jpg";
        Mat testImage = imread(testimagzb, CV_LOAD_IMAGE_GRAYSCALE);
        resize(testImage, testImage, new opencv_core.Size(300, 300));

        File root = new File(trainingDir);

        FilenameFilter imgFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);

        MatVector images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            resize(img, img, new opencv_core.Size(300, 300));
            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }

        FaceRecognizer faceRecognizer = FisherFaceRecognizer.create();
        // FaceRecognizer faceRecognizer = EigenFaceRecognizer.create();
        // FaceRecognizer faceRecognizer = LBPHFaceRecognizer.create();

        faceRecognizer.train(images, labels);

        IntPointer label = new IntPointer(1);
        DoublePointer confidence = new DoublePointer(1);
        faceRecognizer.predict(testImage, label, confidence);
        int predictedLabel = label.get(0);

        System.out.println("Predicted label: " + predictedLabel);
    }
}
