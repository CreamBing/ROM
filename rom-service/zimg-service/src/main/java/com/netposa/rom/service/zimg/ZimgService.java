package com.netposa.rom.service.zimg;

import com.netposa.rom.common.zimg.bean.ZimgFile;

/**
 * zimg:v3.1
 * 操作服务类
 */
public interface ZimgService {

    ZimgFile upload(String file) throws Exception;

    void delete(String md5);
}
