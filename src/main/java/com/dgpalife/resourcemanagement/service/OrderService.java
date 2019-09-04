package com.dgpalife.resourcemanagement.service;

import java.util.Map;

public interface OrderService {
    Map selectOrdersByCreatorName(String creatorName);

    void insertOrder(Orders orders);

    Map selectVerifyOrdersNotIncludeOption(String creatorName);

    Map selectOrderByID(Long orderId);

    Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap);

    Map selectVerifyOrdersByOption(Map<String, Object> optionMap);
}
