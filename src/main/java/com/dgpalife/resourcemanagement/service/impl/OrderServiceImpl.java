package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private Map<String,Object> resultMap = new HashMap<>();

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Map selectOrdersByCreatorName(String creatorName) {
        System.out.println(creatorName);
        Map optionMap = new HashMap();
        optionMap.put("creatorName",creatorName);
        //Map resultMap = new HashMap();
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
    public Map selectVerifyOrdersNotIncludeOption(String creatorName) {
        //HashMap<String,Object> resultMap = new HashMap();

        //查询资源信息数据
        List<HashMap> orderList = ordersMapper.selectVerifyOrdersNotIncludeOption(creatorName);

        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();

        //将查询的结果集添加到map集合中
        resultMap.put("orderList",orderList);
        resultMap.put("departmentList",departmentList);
        return resultMap;
    }

    @Override
    public Map selectOrderByID(Long orderId) {
        //HashMap<String,Object> resultMap = new HashMap();
        Orders orders =ordersMapper.selectByPrimaryKey(orderId);
        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();
        //将查询的结果集添加到map集合中
        resultMap.put("orders",orders);
        resultMap.put("departmentList",departmentList);
        return resultMap;
    }

    /**
     * 功能：我的工单
     * 通过筛选条件查询筛选结果
     *
     * @param optionMap 筛选项及session用户名的集合作为查询条件
     * @return 查询结果集
     */
    @Override
    public Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap) {
        //Map<String,Object> resultMap = new HashMap<>();
        List<HashMap> orderList = ordersMapper.selectOrdersByCreatorNameAndOptionMap(optionMap);
        //查询部门信息
        List<HashMap> departmentList = departmentMapper.selectParentDept();
        resultMap.put("orderList",orderList);
        resultMap.put("departmentList",departmentList);
        return resultMap;
    }

    /**
     * 功能：待生成资源工单
     * 通过筛选条件查询筛选结果
     *
     * @param optionMap 筛选项及session用户名的集合作为查询条件
     * @return 查询结果集
     */

    @Override
    public Map selectVerifyOrdersByOption(Map<String, Object> optionMap) {

        //查询部门信息
        resultMap.put("orderList",ordersMapper.selectVerifyOrdersByOption(optionMap));
        resultMap.put("departmentList",departmentMapper.selectParentDept());
        return resultMap;
    }
}
