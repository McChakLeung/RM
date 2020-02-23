package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Resource;

import java.util.Map;

public interface ResourceService {
    int queryByResourceNo(String resource_no);

    Page<Object> queryResourceNo(Map<String, Object> params);

    Resource queryByResourceID(Long id);

    Map<String,Object> queryPortInfoByResourceID(Long id);
}
