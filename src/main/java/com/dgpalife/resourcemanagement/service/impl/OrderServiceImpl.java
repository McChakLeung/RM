package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.DepartmentMapper;
import com.dgpalife.resourcemanagement.mapper.OrdersMapper;
import com.dgpalife.resourcemanagement.model.Orders;
import com.dgpalife.resourcemanagement.service.DepartmentService;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Map selectOrdersByCreatorName(String creatorName) {
        System.out.println(creatorName);
        Map optionMap = new HashMap();
        optionMap.put("creatorName",creatorName);
        Map resultMap = new HashMap();
        //查询订单数据
        List<HashMap> orderList = ordersMapper.selectOrdersByCreatorName(optionMap);
        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();

        resultMap.put("orderList",orderList);
        resultMap.put("departmentList",departmentList);
        System.out.println(resultMap.get(orderList));

        return resultMap;
    }

    @Override
    public void insertOrder(Orders orders) {
        ordersMapper.insertSelective(orders);
    }

    @Override
    public HashMap selectOrdersNotIncludeOption() {
        HashMap<String,Object> resultMap = new HashMap();

        //查询资源信息数据
        List<HashMap> orderList = ordersMapper.selectOrdersNotIncludeOption();

        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();

        //将查询的结果集添加到map集合中
        resultMap.put("orderList",orderList);
        resultMap.put("departmentList",departmentList);
        return resultMap;
    }

    @Override
    public HashMap<String,Object> selectOrderByID(Long orderId) {
        HashMap<String,Object> resultMap = new HashMap();
        Orders orders =ordersMapper.selectByPrimaryKey(orderId);
        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();
        //将查询的结果集添加到map集合中
        resultMap.put("orders",orders);
        resultMap.put("departmentList",departmentList);
        return resultMap;
    }

    @Override
    public Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap) {
        Map<String,Object> resultMap = new HashMap<>();
        List<HashMap<String,Object>> orderList = ordersMapper.selectOrdersByCreatorNameAndOptionMap(optionMap);
        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();
        resultMap.put("orderList",orderList);
        resultMap.put("departmentList",departmentList);
        System.out.println(resultMap.entrySet());
        return resultMap;
    }
}
