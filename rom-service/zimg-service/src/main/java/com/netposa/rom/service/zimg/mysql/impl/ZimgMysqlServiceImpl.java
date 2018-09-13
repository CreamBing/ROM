package com.netposa.rom.service.zimg.mysql.impl;

import com.netposa.rom.dao.zimg.mysql.FaceTrainEntityMapper;
import com.netposa.rom.dao.zimg.mysql.ZimgRelationEntityMapper;
import com.netposa.rom.model.zimg.FaceTrainEntity;
import com.netposa.rom.model.zimg.ZimgRelationEntity;
import com.netposa.rom.model.zimg.ZimgRelationEntityExample;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ZimgMysqlServiceImpl implements ZimgMysqlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZimgMysqlServiceImpl.class);

    @Autowired
    ZimgRelationEntityMapper zimgRelationEntityMapper;

    @Autowired
    FaceTrainEntityMapper faceTrainEntityMapper;


    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int insert(ZimgRelationEntity entity) {
        return zimgRelationEntityMapper.insert(entity);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int delete(ZimgRelationEntityExample entityExample) {
        return zimgRelationEntityMapper.deleteByExample(entityExample);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int deleteByMD5(String md5) {
        ZimgRelationEntityExample zimgRelationEntityExample = new ZimgRelationEntityExample();
        ZimgRelationEntityExample.Criteria criteria = zimgRelationEntityExample.createCriteria();
        criteria.andMd5EqualTo(md5);
        int result = zimgRelationEntityMapper.deleteByExample(zimgRelationEntityExample);
        LOGGER.info("[{}]mysql数据库删除成功",md5);
        return result;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public int insertFace(FaceTrainEntity entity) {
        return faceTrainEntityMapper.insert(entity);
    }
}
