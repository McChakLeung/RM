package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.OrderMapper;
import com.dgpalife.resourcemanagement.mapper.Order_resourceMapper;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Order_resource;
import com.dgpalife.resourcemanagement.service.Order_resourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Order_resourceServiceImpl implements Order_resourceService{

    @Autowired
    private Order_resourceMapper order_resourceMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Map<String,Object>> queryOrderlistByResourceID(Long id) {
        List<Map<String,Object>> orderList = new ArrayList<>();
        List<Order_resource> order_resourceList = order_resourceMapper.queryOrderlistByResourceID(id);
        for(Order_resource order_resource : order_resourceList){
            Map<String,Object> order = orderMapper.queryOrderByID(order_resource.getOrderId());
            orderList.add(order);
        }
        return orderList;

    }
}
