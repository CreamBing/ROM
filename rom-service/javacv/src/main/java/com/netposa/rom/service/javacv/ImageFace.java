package com.netposa.rom.service.javacv;

import lombok.Data;
import org.bytedeco.javacpp.opencv_core;

import java.util.List;

/**
 * <p>Title: ImageFace</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/12
 * @Company 东方网力
 */
@Data
public class ImageFace<T> {

    private T count;

    private List<opencv_core.CvRect> faceRects;
}
