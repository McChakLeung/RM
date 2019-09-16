package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.OrderMapper;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Long createOrder(Order order) {
        orderMapper.insertSelective(order);
        return order.getId();
    }
}
