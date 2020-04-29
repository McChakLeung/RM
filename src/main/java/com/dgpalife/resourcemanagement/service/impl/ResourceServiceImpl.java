package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.*;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.activiti.engine.impl.transformer.LongToInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private WorkplaceMapper workplaceMapper;

    @Autowired
    private NetworkRoomMapper networkRoomMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private Order_resourceMapper order_resourceMapper;

    @Override
    public int queryByResourceNo(String resource_no) {
        return resourceMapper.queryByResourceNo(resource_no);
    }

    @Override
    public Page<Object> queryResourceNo(Map<String, Object> params) {
        Page<Object> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询项目列表数据
        params.put("startline",page.getStartline());
        List<Map<String,Object>> datas = resourceMapper.queryResourceNo(params);
        page.setDatalist(datas);

        //查询项目总数
        Integer totalsize = resourceMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public Resource queryByResourceID(Long id) {
        Resource resource = resourceMapper.selectByPrimaryKey(id);
        Workplace workplace = workplaceMapper.selectByPrimaryKey(resource.getWorkplace_id());
        NetworkRoom networkRoom = networkRoomMapper.selectByPrimaryKey(resource.getNetwork_room_id());
        Department department = departmentMapper.selectByPrimaryKey(resource.getDepartment_id());
        Department useDepartment = departmentMapper.selectByPrimaryKey(resource.getUsedepartment_id());
        Expense expense = expenseMapper.selectByPrimaryKey(resource.getExpense_id());

        resource.setWorkplace(workplace);
        resource.setNetworkRoom(networkRoom);
        resource.setDepartment(department);
        resource.setUsedepartment(useDepartment);
        resource.setExpense(expense);

        return resource;
    }

    @Override
    public Map<String, Object> queryPortInfoByResourceID(Long id) {
        return resourceMapper.queryPortInfoByResourceID(id);
    }

    @Override
    public void insertResourceList(List<Resource> resourceList, Order order, User user) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Resource resource: resourceList) {
            //设置resource信息
            resource.setCreate_time(sdf.format(date));
            resource.setCreator_id(user.getId());

            //返回自动生成的主键ID
            resourceMapper.insertSelective(resource);

            //插入中间表
            Order_resource order_resource = new Order_resource();
            order_resource.setOrderId(order.getId());
            order_resource.setResourceId(resource.getId());
            order_resourceMapper.insertSelective(order_resource);

        }
    }
}
