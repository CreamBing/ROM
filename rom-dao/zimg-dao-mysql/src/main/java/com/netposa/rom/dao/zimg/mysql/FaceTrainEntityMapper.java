package com.netposa.rom.dao.zimg.mysql;

import com.netposa.rom.model.zimg.FaceTrainEntity;
import com.netposa.rom.model.zimg.FaceTrainEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceTrainEntityMapper {
    long countByExample(FaceTrainEntityExample example);

    int deleteByExample(FaceTrainEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceTrainEntity record);

    int insertSelective(FaceTrainEntity record);

    List<FaceTrainEntity> selectByExample(FaceTrainEntityExample example);

    FaceTrainEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceTrainEntity record, @Param("example") FaceTrainEntityExample example);

    int updateByExample(@Param("record") FaceTrainEntity record, @Param("example") FaceTrainEntityExample example);

    int updateByPrimaryKeySelective(FaceTrainEntity record);

    int updateByPrimaryKey(FaceTrainEntity record);
}