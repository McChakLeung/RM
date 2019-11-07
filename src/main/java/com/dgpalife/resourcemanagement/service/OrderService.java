package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
//    Integer createOrder(Order order);

    List<Map<String,Object>> selectOrderListByProjectId(Long id);

    Page<Object> selectOrderListByUserId(Map<String, Object> params);

    Long saveOrder(Order order);

    Order selectOrderById(Long id);
}
