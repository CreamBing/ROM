package com.netposa.rom.service.zimg.impl;

import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import com.netposa.rom.model.zimg.ZimgRelationEntity;
import com.netposa.rom.service.zimg.ZimgServiceAbstract;
import com.netposa.rom.service.zimg.callback.impl.DeleteFromMysqlCallBack;
import com.netposa.rom.service.zimg.constants.Constants;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("zimgServiceMysqlImpl")
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

}
