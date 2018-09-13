package com.netposa.rom.service.zimg.impl;

import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import com.netposa.rom.model.zimg.FaceTrainEntity;
import com.netposa.rom.model.zimg.ZimgRelationEntity;
import com.netposa.rom.service.zimg.ZimgServiceAbstract;
import com.netposa.rom.service.zimg.callback.impl.DeleteFromMysqlCallBack;
import com.netposa.rom.service.zimg.constants.Constants;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import com.netposa.rom.service.zimg.utils.ImageFace;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import org.bytedeco.javacpp.opencv_core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("zimgAndMysqlServiceImpl")
@Transactional(readOnly = true)
public class ZimgAndMysqlServiceImpl extends ZimgServiceAbstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZimgAndMysqlServiceImpl.class);

    @Autowired
    ZimgMysqlService zimgMysqlService;

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    protected void insertDb(ZimgFile zimgFile) {
        ZimgRelationEntity zimgRelationEntity = new ZimgRelationEntity();
        zimgRelationEntity.setMd5(zimgFile.getZimgName());
        zimgRelationEntity.setCreateTime(new Date());
        zimgRelationEntity.setUpdateTime(new Date());
        zimgRelationEntity.setUrl(zimgFile.getZimgPath());
        zimgRelationEntity.setHasDelete(Constants.NOT_DELETED);
        zimgMysqlService.insert(zimgRelationEntity);
        LOGGER.info("[{}]插入mysql成功",zimgFile.getZimgName());
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    protected void deleteFromDb(String md5) {
        zimgMysqlService.deleteByMD5(md5);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void insertFaceDb(ImageFace<Long> imageFace,String md5) {
        FaceTrainEntity faceTrainEntity = new FaceTrainEntity();
        if(imageFace.getFaceRects().size()==1){
            opencv_core.CvRect faceR = imageFace.getFaceRects().get(0);
            faceTrainEntity.setW(faceR.width());
            faceTrainEntity.setH(faceR.height());
            faceTrainEntity.setY(faceR.y());
            faceTrainEntity.setX(faceR.x());
        }else {
            LOGGER.error("单图人脸图中未检测到人脸或者人脸大于1,人脸数[{}]",imageFace.getFaceRects().size());
            return;
        }
        faceTrainEntity.setCreateTime(new Date());
        faceTrainEntity.setHasDeleted(false);
        faceTrainEntity.setHasRecognized(false);
        faceTrainEntity.setHasTrained(false);
        faceTrainEntity.setMd5(md5);
        faceTrainEntity.setUpdateTime(new Date());
        zimgMysqlService.insertFace(faceTrainEntity);
    }

}
