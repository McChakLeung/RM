package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.*;

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

    List<Order> queryOrderlistByResourceID(Long id);

    List<ConstructDetail> queryConstructDetailListByOrder(Order order);

    List<EquipmentPurchaseRecord> queryEquipmentPurchaseRecordListByOrder(Order order);

    List<ResourceRemovement> queryResourceRemovementListByOrder(Order order);

    List<ResourceMigration> queryResourceMigrationListByOrder(Order order);

    void deleteOrderByID(Long id);

    void updateConstructionOrderInfo(Order order, User user, List<ConstructDetail> constructDetailList, List<EquipmentPurchaseRecord> equipmentPurchaseRecordList);

    void updateMigrationOrderInfo(Order order, User user, List<ResourceMigration> resourceMigrationList, List<EquipmentPurchaseRecord> equipmentPurchaseRecordList);

    void doAddRemovementOrder(User user, Order order, List<ResourceRemovement> resourceRemovementList);

    //Order selectOrderByIdAndStatus(Long id, String Status);
}
