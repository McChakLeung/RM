package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
}