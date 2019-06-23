package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Orders;

import java.util.HashMap;
import java.util.Map;

public interface OrderService {
    Map selectOrdersByCreatorName(String creatorName);

    void insertOrder(Orders orders);

    HashMap selectVerifyOrdersNotIncludeOption(String creatorName);

    HashMap<String,Object>  selectOrderByID(Long orderId);

    Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap);

    Map selectVerifyOrdersByOption(Map<String, Object> optionMap);
}
