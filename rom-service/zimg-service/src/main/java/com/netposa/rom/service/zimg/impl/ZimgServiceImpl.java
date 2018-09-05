package com.netposa.rom.service.zimg.impl;

import com.netposa.rom.common.zimg.ZimgClient;
import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.service.zimg.ZimgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZimgServiceImpl implements ZimgService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZimgServiceImpl.class);

    @Autowired
    ZimgClient zimgClient;

    @Override
    public ZimgFile upload(String file) {
        return zimgClient.upload(file);
    }

    @Override
    public void delete(String md5) {
        try {
            zimgClient.delete(md5);
        }catch (Exception e){
            LOGGER.error("zimg 刪除[{}]图片失败:{}",md5,e.getMessage());
        }
    }
}
