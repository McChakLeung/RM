package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.EquipmentPurchaseRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentPurchaseRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EquipmentPurchaseRecord record);

    int insertSelective(EquipmentPurchaseRecord record);

    EquipmentPurchaseRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipmentPurchaseRecord record);

    int updateByPrimaryKey(EquipmentPurchaseRecord record);

    List<EquipmentPurchaseRecord> selectEquipmentPurchaseRecordByOrderID(Long id);

    int getPurchaseNumByOrderId(Long order_id);

    void deleteByOrderId(Long id);
}