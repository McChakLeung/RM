package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.ConstructDetailMapper;
import com.dgpalife.resourcemanagement.mapper.OrderMapper;
import com.dgpalife.resourcemanagement.model.ConstructDetail;
import com.dgpalife.resourcemanagement.model.Order;
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

    @Override
    public Integer createOrder(Order order) {
        try{
            Long orderId = orderMapper.insertSelective(order);
            List<ConstructDetail> constructDetailList = order.getConstructDetailList();
            for (ConstructDetail constructDetail: constructDetailList) {
                constructDetail.setOrderId(orderId);
            }
            constructDetailMapper.insertByBatch(constructDetailList);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

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
        return orderMapper.insertSelective(order);
    }
}
