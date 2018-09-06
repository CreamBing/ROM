package com.netposa.rom.service.zimg.callback.impl;

import com.netposa.rom.service.zimg.callback.DeleteCallBackAbstract;
import com.netposa.rom.service.zimg.mysql.ZimgMysqlService;
import com.netposa.rom.service.zimg.mysql.impl.ZimgMysqlServiceImpl;
import com.netposa.rom.service.zimg.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteFromMysqlCallBack extends DeleteCallBackAbstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFromMysqlCallBack.class);

    ZimgMysqlService mysqlService = SpringContextUtils.getBean(ZimgMysqlServiceImpl.class);

    public void deleteFromDb(String md5) {
        mysqlService.deleteByMD5(md5);
    }

}
