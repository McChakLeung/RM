package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.*;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.activiti.engine.impl.transformer.LongToInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private ResourceMigrationMapper resourceMigrationMapper;

    @Autowired
    private OrderMapper orderMapper;

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
    public void generateResource(List<Resource> resourceList,List<Equipment>equipmentList, Order order, User user) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //第一步：判断传入的equipmentList是否为空，如果不为空则在设备表中添加设备记录
        if(!equipmentList.isEmpty()){
            for(Equipment equipment:equipmentList){
                equipment.setCreatorId(user.getId());
                equipment.setCreateTime(sdf.format(date));
                equipmentMapper.insertSelective(equipment);
            }
        }

        //第二步：遍历resourceList
        for (Resource resource: resourceList) {
            //查询设备表中与resourceList中EquipmentSN一致的数据，并更新Migration对象中equipment_id的字段
            //Equipment equipment = equipmentMapper.selectByEquipmenSN(resource.getEquipment().getEquipmentSn());
            //resource.setEquipment_id(equipment.getId());

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

        //第三步：更新order状态为已完成
        order.setStatus("已完成");
        order.setFinish(true);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public List<Object> selectResourceByQueryText(Map<String, Object> params) {
        return resourceMapper.selectResourceByQueryText(params);
    }

    @Override
    public int selectCount(Map<String, Object> params) {
        return resourceMapper.selectSearchResult(params);
    }

    @Override
    public Resource queryResoureDetailByID(Long resource_id) {
        //return resourceMapper.queryResoureDetailByID(resource_id);
        Resource resource = resourceMapper.selectByPrimaryKey(resource_id);
        Workplace workplace = workplaceMapper.selectByPrimaryKey(resource.getWorkplace_id());
        NetworkRoom networkRoom  = networkRoomMapper.selectByPrimaryKey(resource.getNetwork_room_id());
        Department department = departmentMapper.selectByPrimaryKey(resource.getDepartment_id());
        Department usedepartment = departmentMapper.selectByPrimaryKey(resource.getUsedepartment_id());
        resource.setWorkplace(workplace);
        resource.setNetworkRoom(networkRoom);
        resource.setDepartment(department);
        resource.setUsedepartment(usedepartment);
        return  resource;
    }

    @Override
    public void deleteResourceList(List<ResourceRemovement> resourceRemovementList) {
        for(ResourceRemovement resourceRemovement: resourceRemovementList){
            resourceMapper.updateResource(resourceRemovement.getResource_id());
        }
    }

    @Override
    public void updateMigrationResourceList(List<ResourceMigration> resourceMigrationList, List<Equipment> equipmentList, User user, Order order) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //判断传入的equipmentList是否为空，如果不为空则在设备表中添加设备记录
        if(!equipmentList.isEmpty()){
            for(Equipment equipment:equipmentList){
                equipment.setCreatorId(user.getId());
                equipment.setCreateTime(sdf.format(date));
                equipmentMapper.insertSelective(equipment);
            }
        }

        //第二步：遍历ResourceMigrationList
        for(ResourceMigration resourceMigration: resourceMigrationList){
            //查询设备表中与migrationList中EquipmentSN一致的数据，并更新Migration对象中equipment_id的字段
            Equipment equipment = equipmentMapper.selectByEquipmenSN(resourceMigration.getEquipment_sn());
            resourceMigration.setEquipment_id(equipment.getId());

            //然后根据migratio对象中查询出resource_id，查询resource对象，并更新Resource表数据
            Resource resource = resourceMapper.selectByPrimaryKey(resourceMigration.getResource_id());
            resource.setWorkplace_id(resourceMigration.getNew_workplace_id());
            resource.setNetwork_room_id(resourceMigration.getNew_network_room_id());
            resource.setDepartment_id(resourceMigration.getNew_department_id());
            resource.setUsedepartment_id(resourceMigration.getNew_usedepartment_id());
            resource.setUsedepartment_type(resourceMigration.getNew_usedepartment_type());
            resource.setEquipment_id(resourceMigration.getEquipment_id());
            resource.setEquipment_port_num(resourceMigration.getEquipment_port_num());
            resourceMapper.updateByPrimaryKeySelective(resource);

            //更新migration表
            resourceMigrationMapper.updateByPrimaryKeySelective(resourceMigration);

            //插入中间表
            Order_resource order_resource = new Order_resource();
            order_resource.setOrderId(order.getId());
            order_resource.setResourceId(resource.getId());
            order_resourceMapper.insertSelective(order_resource);
        }


        //第四步：更新order状态为已完成
        order.setStatus("已完成");
        order.setFinish(true);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void removeResource(List<ResourceRemovement> resourceRemovementList, Order order) {
        //拆除资源
        //第一步：在资源表中添加资源记录
        for(ResourceRemovement resourceRemovement: resourceRemovementList){
            resourceMapper.deleteByPrimaryKey(resourceRemovement.getResource_id());
        }
        //resourceService.deleteResourceList(resourceRemovementList);

        //第二步：更新order状态为已完成
        order.setStatus("已完成");
        order.setFinish(true);
        orderMapper.updateByPrimaryKeySelective(order);
    }

}
