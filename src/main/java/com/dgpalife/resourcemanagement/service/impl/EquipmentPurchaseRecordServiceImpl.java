package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.EquipmentPurchaseRecordMapper;
import com.dgpalife.resourcemanagement.model.EquipmentPurchaseRecord;
import com.dgpalife.resourcemanagement.service.EquipmentPurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentPurchaseRecordServiceImpl implements EquipmentPurchaseRecordService {

    @Autowired
    private EquipmentPurchaseRecordMapper equipmentPurchaseRecordMapper;

    @Override
    public void saveEquipmentPurchaseRecordByBatch(List<EquipmentPurchaseRecord> equipmentPurchaseRecordList) {
        for(EquipmentPurchaseRecord equipmentPurchaseRecord:equipmentPurchaseRecordList){
            equipmentPurchaseRecordMapper.insertSelective(equipmentPurchaseRecord);
        }
    }

    @Override
    public int getPurchaseNumByOrderId(Long order_id) {
        return equipmentPurchaseRecordMapper.getPurchaseNumByOrderId(order_id);
    }
}
