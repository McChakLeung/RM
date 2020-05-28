package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.*;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
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

    @Autowired
    private EquipmentPurchaseRecordMapper equipmentPurchaseRecordMapper;

    @Autowired
    private ResourceRemovementMapper resourceRemovementMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceMigrationMapper resourceMigrationMapper;


    //@Autowired
    //private Equipment_typeMapper equipment_typeMapper;

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

        order.setApplyDepartment(applyDepartment);
        order.setProposer(user);
        order.setProject(project);
        //order.setConstructDetailList(constructDetailList);
        //order.setEquipmentPurchaseRecordList(equipmentPurchaseRecordList);
        return order;
    }

    @Override
    public List<ConstructDetail> queryConstructDetailListByOrder(Order order) {
        List<ConstructDetail> constructDetailList = constructDetailMapper.selectByOrderID(order.getId());
        for(ConstructDetail constructDetail: constructDetailList){
            constructDetail.setApply_department(departmentMapper.selectByPrimaryKey(constructDetail.getApply_department_id()));
            constructDetail.setUsedepartment(departmentMapper.selectByPrimaryKey(constructDetail.getUsedepartment_id()));
            constructDetail.setExpense(expenseMapper.selectByPrimaryKey(constructDetail.getExpenseId()));
            constructDetail.setWorkplace(workplaceMapper.selectByPrimaryKey(constructDetail.getWorkplaceId()));
            constructDetail.setNetworkRoom(networkRoomMapper.selectByPrimaryKey(constructDetail.getNetworkRoomId()));
        }
        return constructDetailList;
    }

    @Override
    public List<EquipmentPurchaseRecord> queryEquipmentPurchaseRecordListByOrder(Order order) {
        List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = equipmentPurchaseRecordMapper.selectEquipmentPurchaseRecordByOrderID(order.getId());
        for(EquipmentPurchaseRecord equipmentPurchaseRecord: equipmentPurchaseRecordList){
            //equipmentPurchaseRecord.setEquipment_type(equipment_typeMapper.selectByPrimaryKey(equipmentPurchaseRecord.getEquipment_type()));
            equipmentPurchaseRecord.setExpense(expenseMapper.selectByPrimaryKey(equipmentPurchaseRecord.getExpenseId()));
//            constructDetail.setWorkplace(workplaceMapper.selectByPrimaryKey(constructDetail.getWorkplaceId()));
//            constructDetail.setNetworkRoom(networkRoomMapper.selectByPrimaryKey(constructDetail.getNetworkRoomId()));
        }
        return equipmentPurchaseRecordList;
    }

    @Override
    public List<ResourceRemovement> queryResourceRemovementListByOrder(Order order) {
        List<ResourceRemovement> resourceRemovementList = resourceRemovementMapper.selectResourceRemovementListByOrderId(order.getId());
        for(ResourceRemovement resourceRemovement:resourceRemovementList){

            Resource resource = resourceMapper.selectByPrimaryKey(resourceRemovement.getResource_id());
            if(resource == null){
                resourceRemovement.setResource(null);
            }else {
                resource.setDepartment(departmentMapper.selectByPrimaryKey(resource.getDepartment_id()));
                resource.setUsedepartment(departmentMapper.selectByPrimaryKey(resource.getUsedepartment_id()));
                resource.setWorkplace(workplaceMapper.selectByPrimaryKey(resource.getWorkplace_id()));
                resource.setNetworkRoom(networkRoomMapper.selectByPrimaryKey(resource.getNetwork_room_id()));
                resourceRemovement.setResource(resource);
            }
        }
        return resourceRemovementList;
    }

    @Override
    public List<ResourceMigration> queryResourceMigrationListByOrder(Order order) {
        return resourceMigrationMapper.selectByOrderID(order.getId());
    }

    @Override
    public void deleteOrderByID(Long id) {

        //删除以下几张关联表的记录
        constructDetailMapper.deleteByOrderId(id);
        equipmentPurchaseRecordMapper.deleteByOrderId(id);
        resourceMigrationMapper.deleteByOrderId(id);
        resourceRemovementMapper.deleteByOrderId(id);

        //删除t_order字段
        orderMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void updateConstructionOrderInfo(Order order, User user, List<ConstructDetail> constructDetailList, List<EquipmentPurchaseRecord> equipmentPurchaseRecordList) {
        //设置order的其他信息
        order.setStatus("待提交");
        order.setProposerId(user.getId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(sdf.format(date));

        //更新工单信息
        orderMapper.updateByPrimaryKeySelective(order);

        //处理建设明细
        //1) 删除明细表中与工单相关联的数据
        constructDetailMapper.deleteByOrderId(order.getId());
        //2) 更新建设明细表
        for(ConstructDetail constructDetail: constructDetailList){
            constructDetail.setCreateTime(sdf.format(date));
            constructDetail.setCreatorId(user.getId());
            constructDetail.setOrderId(order.getId());
            constructDetailMapper.insertSelective(constructDetail);
        }

        //更新设备信息
        //1) 删除明细表中与工单相关的数据
        equipmentPurchaseRecordMapper.deleteByOrderId(order.getId());
        //2) 更新明细表
        for(EquipmentPurchaseRecord equipmentPurchaseRecord: equipmentPurchaseRecordList){
            equipmentPurchaseRecord.setCreateTime(sdf.format(date));
            equipmentPurchaseRecord.setCreatorId(user.getId());
            equipmentPurchaseRecord.setOrderId(order.getId());
            equipmentPurchaseRecordMapper.insertSelective(equipmentPurchaseRecord);
        }

    }

    @Override
    public void updateMigrationOrderInfo(Order order, User user, List<ResourceMigration> resourceMigrationList, List<EquipmentPurchaseRecord> equipmentPurchaseRecordList) {
        //设置order的其他信息
        order.setStatus("待提交");
        order.setProposerId(user.getId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(sdf.format(date));

        //更新工单信息
        orderMapper.updateByPrimaryKeySelective(order);

        //处理建设明细
        //1) 删除明细表中与工单相关联的数据
        resourceMigrationMapper.deleteByOrderId(order.getId());
        //2) 更新建设明细表
        for(ResourceMigration resourceMigration: resourceMigrationList){
            resourceMigration.setCreate_time(sdf.format(date));
            resourceMigration.setCreator_id(user.getId());
            resourceMigration.setOrder_id(order.getId());
            resourceMigrationMapper.insertSelective(resourceMigration);
        }

        //更新设备信息
        //1) 删除明细表中与工单相关的数据
        equipmentPurchaseRecordMapper.deleteByOrderId(order.getId());
        //2) 更新明细表
        for(EquipmentPurchaseRecord equipmentPurchaseRecord: equipmentPurchaseRecordList){
            equipmentPurchaseRecord.setCreateTime(sdf.format(date));
            equipmentPurchaseRecord.setCreatorId(user.getId());
            equipmentPurchaseRecord.setOrderId(order.getId());
            equipmentPurchaseRecordMapper.insertSelective(equipmentPurchaseRecord);
        }

    }

    @Override
    public void doAddRemovementOrder(User user, Order order, List<ResourceRemovement> resourceRemovementList) {
        //设置order的其他信息
        order.setStatus("待提交");
        order.setProposerId(user.getId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(sdf.format(date));
        //更新工单
        orderMapper.insertSelective(order);
        //更新t_resource_removement
        for(ResourceRemovement resourceRemovement:resourceRemovementList){
            resourceRemovement.setResource_no(resourceRemovement.getResource().getResource_no());
            resourceRemovement.setResourc_type(resourceRemovement.getResource().getResourc_type());
            resourceRemovement.setOperator(resourceRemovement.getResource().getOperator());
            resourceRemovement.setWorkplaceName(resourceRemovement.getResource().getWorkplace().getWorkplaceName());
            resourceRemovement.setNetworkroom_name(resourceRemovement.getResource().getNetworkRoom().getNetworkroom_name());
            resourceRemovement.setDepartmentName(resourceRemovement.getResource().getDepartment().getDepartmentName());
            resourceRemovement.setUsedepartmentName(resourceRemovement.getResource().getUsedepartment().getDepartmentName());
            resourceRemovement.setUsername(resourceRemovement.getResource().getUsername());
            resourceRemovement.setCreate_time(sdf.format(date));
            resourceRemovement.setCreator_id(user.getId());
            resourceRemovement.setOrder_id(order.getId());
            resourceRemovementMapper.insertSelective(resourceRemovement);
        }

    }

    @Override
    public void doUpdateRemovementOrder(Order order, User user, List<ResourceRemovement> resourceRemovementList) {
        //设置order的其他信息
        order.setStatus("待提交");
        order.setProposerId(user.getId());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreateTime(sdf.format(date));

        //更新工单信息
        orderMapper.updateByPrimaryKeySelective(order);

        //处理建设明细
        //1) 删除明细表中与工单相关联的数据
        resourceRemovementMapper.deleteByOrderId(order.getId());
        //2) 更新建设明细表
        for(ResourceRemovement resourceRemovement: resourceRemovementList){
            resourceRemovement.setCreate_time(sdf.format(date));
            resourceRemovement.setCreator_id(user.getId());
            resourceRemovement.setOrder_id(order.getId());
            resourceRemovementMapper.insertSelective(resourceRemovement);
        }

    }

    @Override
    public List<Resource> queryResourceDetailList(List<Resource> resources) {
        List<Resource> resourceList = new ArrayList<>();
        for(Resource resource: resources){
            resource.setExpense(expenseMapper.selectByPrimaryKey(resource.getExpense_id()));
            resource.setWorkplace(workplaceMapper.selectByPrimaryKey(resource.getWorkplace_id()));
            resource.setNetworkRoom(networkRoomMapper.selectByPrimaryKey(resource.getNetwork_room_id()));
            resource.setDepartment(departmentMapper.selectByPrimaryKey(resource.getDepartment_id()));
            resource.setUsedepartment(departmentMapper.selectByPrimaryKey(resource.getUsedepartment_id()));
            resourceList.add(resource);
        }
        return resourceList;
    }

    @Override
    public List<Equipment> queryEquipmentDetailList(List<Equipment> equipments) {
        List<Equipment> equipmentList = new ArrayList<>();
        for(Equipment equipment: equipments){
            equipment.setExpense(expenseMapper.selectByPrimaryKey(equipment.getExpense_id()));
            equipmentList.add(equipment);
        }
        return equipmentList;
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

    @Override
    public Page<Object> selectPreHandleOrder(Map<String, Object> params) {
        Page<Object> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询项目列表数据
        params.put("startline",page.getStartline());
        params.put("status", Const.AUDIT_SUCCESS);
        List<Map<String,Object>> datas = orderMapper.selectPreHandleOrder(params);
        page.setDatalist(datas);

        //查询项目总数
        Integer totalsize = datas.size();
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public List<Order> queryOrderlistByResourceID(Long id) {
        List<Order> orderList = orderMapper.queryOrderlistByResourceID(id);
        for(Order order : orderList){
            Department department = departmentMapper.selectByPrimaryKey(order.getApplyDepartmentId());
            User proposer = userMapper.selectByPrimaryKey(order.getProposerId());
            Project project = projectMapper.selectByPrimaryKey(order.getProposerId());
            order.setApplyDepartment(department);
            order.setProposer(proposer);
            order.setProject(project);
        }
        return orderList;
    }

}
