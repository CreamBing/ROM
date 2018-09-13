package com.netposa.rom.service.job.handler;

import com.netposa.rom.dao.zimg.mysql.FaceTrainEntityMapper;
import com.netposa.rom.model.zimg.FaceTrainEntity;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>Title: GetFaceImgFromMysqlJob</p>
 * <p>Description: </p>
 *
 * @author bing
 * @date 2018/9/13
 * @Company 东方网力
 */
@JobHandler(value="getFaceImgFromMysqlJob")
@Component
public class GetFaceImgFromMysqlJob extends IJobHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetFaceImgFromMysqlJob.class);

    @Autowired
    FaceTrainEntityMapper faceTrainEntityMapper;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        List<FaceTrainEntity> lists = faceTrainEntityMapper.selectByExample(null);
        LOGGER.info("face list size:[{}]",lists.size());
        return null;
    }
}
