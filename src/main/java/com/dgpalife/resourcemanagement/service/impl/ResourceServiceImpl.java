package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.*;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
