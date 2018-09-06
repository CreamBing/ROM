package com.netposa.rom.service.zimg;

import com.netposa.rom.common.zimg.ZimgClient;
import com.netposa.rom.common.zimg.bean.ZimgFile;
import com.netposa.rom.common.zimg.httpasyncclient.DeleteCallBack;
import com.netposa.rom.service.zimg.exception.UploadFailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ZimgServiceAbstract implements ZimgService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ZimgServiceAbstract.class);

    @Autowired
    ZimgClient zimgClient;


    protected abstract void insertDb(ZimgFile zimgFile);

    protected abstract void deleteFromDb(String md5);

    protected abstract DeleteCallBack deleteCallBack();

    @Override
    public ZimgFile upload(String file) throws Exception{
        ZimgFile zimgFile =  zimgClient.upload(file);
        if(zimgFile == null){
            throw new UploadFailException("zimg上传失败,返回的zimg对象为空");
        }
        insertDb(zimgFile);
        return zimgFile;
    }

    @Override
    public void delete(String md5) {
        try {
            //zimg上删除图片之后的回调方法
            DeleteCallBack deleteCallBack = deleteCallBack();
            zimgClient.delete(md5,deleteCallBack);
//            deleteFromDb(md5);
        }catch (Exception e){
            LOGGER.error("zimg 刪除[{}]图片失败:{}",md5,e.getMessage());
        }
    }
}
