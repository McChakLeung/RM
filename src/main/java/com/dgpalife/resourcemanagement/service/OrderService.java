package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Order;

import java.util.Map;

public interface OrderService {
//    Map selectOrdersByCreatorName(String creatorName);
//
//    void insertOrder(Order order);
//
//    Map selectVerifyOrdersNotIncludeOption(String creatorName);
//
//    Map selectOrderByID(Long orderId);
//
//    Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap);
//
//    Map selectVerifyOrdersByOption(Map<String, Object> optionMap);

    Page<Order> selectOrderListByUserID(Map<String, Object> params);

    Order selectOrderByID(Long id);

    int updateOrder(Order order);
}
