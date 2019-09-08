package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.EquipmentPort;

public interface EquipmentPortMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EquipmentPort record);

    int insertSelective(EquipmentPort record);

    EquipmentPort selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipmentPort record);

    int updateByPrimaryKey(EquipmentPort record);
}