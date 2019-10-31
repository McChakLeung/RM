package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Integer createOrder(Order order);

    List<Map<String,Object>> selectOrderListByProjectId(Long id);
}
