package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.ResourceRemovementMapper;
import com.dgpalife.resourcemanagement.model.ResourceRemovement;
import com.dgpalife.resourcemanagement.service.ResourceRemovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRemovementServiceImpl implements ResourceRemovementService {

    @Autowired
    private ResourceRemovementMapper resourceRemovementMapper;

    @Override
    public int saveResourceRemovementListByBatch(List<ResourceRemovement> rmList) {
        return resourceRemovementMapper.saveResourceRemovementListByBatch(rmList);
    }
}
