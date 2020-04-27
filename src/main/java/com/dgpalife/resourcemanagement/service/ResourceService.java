package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Resource;
import com.dgpalife.resourcemanagement.model.User;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    int queryByResourceNo(String resource_no);

    Page<Object> queryResourceNo(Map<String, Object> params);

    Resource queryByResourceID(Long id);

    Map<String,Object> queryPortInfoByResourceID(Long id);

    void insertResourceList(List<Resource> resourceList,Order order, User user);
}
