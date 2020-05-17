package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ChangeItemRecord;

public interface ChangeItemRecordMapper {
    int insert(ChangeItemRecord record);

    int insertSelective(ChangeItemRecord record);
}