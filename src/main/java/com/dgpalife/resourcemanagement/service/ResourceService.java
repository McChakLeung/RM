package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.*;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    int queryByResourceNo(String resource_no);

    Page<Object> queryResourceNo(Map<String, Object> params);

    Resource queryByResourceID(Long id);

    Map<String,Object> queryPortInfoByResourceID(Long id);

    void generateResource(List<Resource> resourceList,List<Equipment> equipmentList,Order order, User user);

    List<Object> selectResourceByQueryText(Map<String, Object> params);

    int selectCount(Map<String, Object> params);

    Resource queryResoureDetailByID(Long resource_id);

    void deleteResourceList(List<ResourceRemovement> resourceRemovementList);

    void updateMigrationResourceList(List<ResourceMigration> resourceMigrationList, List<Equipment> equipmentList, User user,Order order);
}
