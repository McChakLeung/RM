package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.*;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ConstructDetailMapper constructDetailMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private WorkplaceMapper workplaceMapper;

    @Autowired
    private NetworkRoomMapper networkRoomMapper;

//    @Override
//    public Integer createOrder(Order order) {
//        try{
//            orderMapper.insertOrder(order);
//
//            List<ConstructDetail> constructDetailList = order.getConstructDetailList();
//            for (ConstructDetail constructDetail: constructDetailList) {
//                constructDetail.setOrderId(order.getId());
//            }
//            constructDetailMapper.insertByBatch(constructDetailList);
//        }catch (Exception e){
//            e.printStackTrace();
//            return 0;
//        }
//        return 1;
//    }

    /**
     * 根据projectID查询关联的工单清单
     * @param id
     * @return
     */
    @Override
    public List<Map<String,Object>> selectOrderListByProjectId(Long id) {
        return orderMapper.selectOrderListByProjectId(id);
    }

    @Override
    public Page<Object> selectOrderListByUserId(Map<String, Object> params) {
        Page<Object> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询项目列表数据
        params.put("startline",page.getStartline());
        List<Map<String,Object>> datas = orderMapper.selectOrderListByUserId(params);
        page.setDatalist(datas);

        //查询项目总数
        Integer totalsize = orderMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public Long saveOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public Order selectOrderById(Long id) {
        //组装order
        Order order = orderMapper.selectByPrimaryKey(id);
        Department applyDepartment = departmentMapper.selectByPrimaryKey(order.getApplyDepartmentId());
        User user = userMapper.selectByPrimaryKey(order.getProposerId());
        Project project = projectMapper.selectByPrimaryKey(order.getProjectId());
        List<ConstructDetail> constructDetailList = constructDetailMapper.selectByOrderID(order.getId());
        for(ConstructDetail constructDetail: constructDetailList){
            constructDetail.setUsedepartment(departmentMapper.selectByPrimaryKey(constructDetail.getUsedepartment_id()));
            constructDetail.setExpense(expenseMapper.selectByPrimaryKey(constructDetail.getExpenseId()));
            constructDetail.setWorkplace(workplaceMapper.selectByPrimaryKey(constructDetail.getWorkplaceId()));
            constructDetail.setNetworkRoom(networkRoomMapper.selectByPrimaryKey(constructDetail.getNetworkRoomId()));
        }
        order.setApplyDepartment(applyDepartment);
        order.setProposer(user);
        order.setProject(project);
        order.setConstructDetailList(constructDetailList);
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order queryOrderByPiid(String processInstanceId) {
        return orderMapper.queryOrderByPiid(processInstanceId);
    }

    @Override
    public void passAuth(Long order_id,String comment) {
        orderMapper.passAuth(order_id,comment);
    }

    @Override
    public void refuseAuth(Long order_id, String comment) {
        orderMapper.refuseAuth(order_id,comment);
    }
}
