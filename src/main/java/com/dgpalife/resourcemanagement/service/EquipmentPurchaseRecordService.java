package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.EquipmentPurchaseRecord;

import java.util.List;

public interface EquipmentPurchaseRecordService {
    void saveEquipmentPurchaseRecordByBatch(List<EquipmentPurchaseRecord> equipmnetPurchaseDetailList);

    int getPurchaseNumByOrderId(Long order_id);
}
