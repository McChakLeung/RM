package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.EquipmentPurchaseRecord;

public interface EquipmentPurchaseRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EquipmentPurchaseRecord record);

    int insertSelective(EquipmentPurchaseRecord record);

    EquipmentPurchaseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipmentPurchaseRecord record);

    int updateByPrimaryKey(EquipmentPurchaseRecord record);
}