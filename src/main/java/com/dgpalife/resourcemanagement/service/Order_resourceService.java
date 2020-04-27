package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Order;

import java.util.List;
import java.util.Map;

public interface Order_resourceService {

    List<Map<String,Object>> queryOrderlistByResourceID(Long id);
}
