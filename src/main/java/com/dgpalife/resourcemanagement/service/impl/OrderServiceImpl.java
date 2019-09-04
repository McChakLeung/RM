package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.DepartmentMapper;
import com.dgpalife.resourcemanagement.mapper.OrderMapper;
import com.dgpalife.resourcemanagement.mapper.ResourceMapper;
import com.dgpalife.resourcemanagement.mapper.UserMapper;
import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Resource;
import com.dgpalife.resourcemanagement.model.User;
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
    private OrderMapper orderMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Page<Order> selectOrderListByUserID(Map<String, Object> params) {
        Page<Order> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询工单列表数据
        params.put("startline",page.getStartline());
        User user = (User)params.get("user");
        List<Order> datas = orderMapper.selectOrderListByUserID(user.getId());
        //查询用户实体类


        //遍历datas，组装查询数据
        for(Order order : datas){
            //将查询的用户放进order中
            order.setCreator((User)params.get("user"));

            //将符合条件的资源号码和在用号码对象查询出来，组装到order中
            order.setResource(resourceMapper.selectByPrimaryKey(order.getResourceid()));
            order.setUsingnumber(resourceMapper.selectByPrimaryKey(order.getResourceid()));

            //将符合条件的挂账部门及使用部门查询出来，组装到order中
            order.setAccountdept(departmentMapper.selectByPrimaryKey(order.getAccountdeptid()));
            order.setUsedept(departmentMapper.selectByPrimaryKey(order.getUsedeptid()));


        }
        page.setDatalist(datas);

        //查询工单总数
        Integer totalsize = orderMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    /**
     * 根据id查询工单信息
     * @param id 工单id
     * @return
     */
    @Override
    public Order selectOrderByID(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        return getRelateObject(order);
    }


//    @Override
//    public Map selectOrdersByCreatorName(String creatorName) {
//        System.out.println(creatorName);
//        Map optionMap = new HashMap();
//        optionMap.put("creatorName",creatorName);
//        //Map resultMap = new HashMap();
//        //查询订单数据
//        List<HashMap> orderList = orderMapper.selectOrdersByCreatorName(optionMap);
//        //查询部门信息
//        List<HashMap> departmentList = departmentMapper.selectParentDept();
//
//        resultMap.put("orderList",orderList);
//        resultMap.put("departmentList",departmentList);
//        System.out.println(resultMap.get(orderList));
//
//        return resultMap;
//    }
//
//    @Override
//    public void insertOrder(Orders orders) {
//        ordersMapper.insertSelective(orders);
//    }
//
//    @Override
//    public Map selectVerifyOrdersNotIncludeOption(String creatorName) {
//        //HashMap<String,Object> resultMap = new HashMap();
//
//        //查询资源信息数据
//        List<HashMap> orderList = ordersMapper.selectVerifyOrdersNotIncludeOption(creatorName);
//
//        //查询部门信息
//        List<HashMap> departmentList = departmentMapper.selectParentDept();
//
//        //将查询的结果集添加到map集合中
//        resultMap.put("orderList",orderList);
//        resultMap.put("departmentList",departmentList);
//        return resultMap;
//    }
//
//    @Override
//    public Map selectOrderByID(Long orderId) {
//        //HashMap<String,Object> resultMap = new HashMap();
//        Orders orders =ordersMapper.selectByPrimaryKey(orderId);
//        //查询部门信息
//        List<HashMap> departmentList = departmentMapper.selectParentDept();
//        //将查询的结果集添加到map集合中
//        resultMap.put("orders",orders);
//        resultMap.put("departmentList",departmentList);
//        return resultMap;
//    }
//
//    /**
//     * 功能：我的工单
//     * 通过筛选条件查询筛选结果
//     *
//     * @param optionMap 筛选项及session用户名的集合作为查询条件
//     * @return 查询结果集
//     */
//    @Override
//    public Map selectOrdersByOptionAndCreatorName(Map<String, Object> optionMap) {
//        //Map<String,Object> resultMap = new HashMap<>();
//        List<HashMap> orderList = ordersMapper.selectOrdersByCreatorNameAndOptionMap(optionMap);
//        //查询部门信息
//        List<HashMap> departmentList = departmentMapper.selectParentDept();
//        resultMap.put("orderList",orderList);
//        resultMap.put("departmentList",departmentList);
//        return resultMap;
//    }
//
//    /**
//     * 功能：待生成资源工单
//     * 通过筛选条件查询筛选结果
//     *
//     * @param optionMap 筛选项及session用户名的集合作为查询条件
//     * @return 查询结果集
//     */
//
//    @Override
//    public Map selectVerifyOrdersByOption(Map<String, Object> optionMap) {
//
//        //查询部门信息
//        resultMap.put("orderList",ordersMapper.selectVerifyOrdersByOption(optionMap));
//        resultMap.put("departmentList",departmentMapper.selectParentDept());
//        return resultMap;
//    }

    /**
     * 公共方法，便于组装order对象所关联的其他对象
     * @param order：工单对象
     * @return order
     */
    private Order getRelateObject(Order order){
        order.setCreator(userMapper.selectByPrimaryKey(order.getCreatorId()));
        order.setResource(resourceMapper.selectByPrimaryKey(order.getResourceid()));
        order.setAccountdept(departmentMapper.selectByPrimaryKey(order.getAccountdeptid()));
        order.setUsedept(departmentMapper.selectByPrimaryKey(order.getUsedeptid()));
        order.setUsingnumber(resourceMapper.selectByPrimaryKey(order.getResourceid()));
        return order;
    }
}
