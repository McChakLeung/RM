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

    void updateOrder(Order order);

    Order queryOrderByPiid(String processInstanceId);

    void passAuth(Long order_id,String comment);

    void refuseAuth(Long order_id, String comment);

    Page<Object> selectPreHandleOrder(Map<String, Object> params);

    //Order selectOrderByIdAndStatus(Long id, String Status);
}
