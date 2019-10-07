package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.ConstructDetailMapper;
import com.dgpalife.resourcemanagement.mapper.OrderMapper;
import com.dgpalife.resourcemanagement.model.ConstructDetail;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ConstructDetailMapper constructDetailMapper;

    @Override
    public Integer createOrder(Order order) {
        try{
            Long orderId = orderMapper.insertSelective(order);
            List<ConstructDetail> constructDetailList = order.getConstructDetailList();
            for (ConstructDetail onstructDetail: constructDetailList) {
                onstructDetail.setOrderId(orderId);
            }
            constructDetailMapper.insertByBatch(constructDetailList);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
