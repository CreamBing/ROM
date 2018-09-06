package com.netposa.rom.service.zimg.mysql;

import com.netposa.rom.model.zimg.ZimgRelationEntity;
import com.netposa.rom.model.zimg.ZimgRelationEntityExample;

public interface ZimgMysqlService {

    int insert(ZimgRelationEntity entity);

    int delete(ZimgRelationEntityExample entityExample);

    int deleteByMD5(String md5);
}
