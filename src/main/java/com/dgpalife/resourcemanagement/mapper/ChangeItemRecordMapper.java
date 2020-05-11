package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ChangeItemRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChangeItemRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ChangeItemRecord record);

    int insertSelective(ChangeItemRecord record);

    ChangeItemRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChangeItemRecord record);

    int updateByPrimaryKeyWithBLOBs(ChangeItemRecord record);

    int updateByPrimaryKey(ChangeItemRecord record);
}