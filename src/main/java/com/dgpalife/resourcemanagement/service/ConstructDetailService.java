package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.ConstructDetail;

import java.util.List;

public interface ConstructDetailService {
    void saveConstructDetailBatch(List<ConstructDetail> constructDetailList);

    int getConstructDetailNumByOrderId(Long order_id);
}
