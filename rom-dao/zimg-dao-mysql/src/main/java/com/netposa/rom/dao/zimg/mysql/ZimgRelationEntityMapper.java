package com.netposa.rom.dao.zimg.mysql;

import com.netposa.rom.model.zimg.ZimgRelationEntity;
import com.netposa.rom.model.zimg.ZimgRelationEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZimgRelationEntityMapper {
    long countByExample(ZimgRelationEntityExample example);

    int deleteByExample(ZimgRelationEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZimgRelationEntity record);

    int insertSelective(ZimgRelationEntity record);

    List<ZimgRelationEntity> selectByExample(ZimgRelationEntityExample example);

    ZimgRelationEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZimgRelationEntity record, @Param("example") ZimgRelationEntityExample example);

    int updateByExample(@Param("record") ZimgRelationEntity record, @Param("example") ZimgRelationEntityExample example);

    int updateByPrimaryKeySelective(ZimgRelationEntity record);

    int updateByPrimaryKey(ZimgRelationEntity record);
}