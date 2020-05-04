package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.ResourceRemovement;

import java.util.List;

public interface ResourceRemovementService {
    int saveResourceRemovementListByBatch(List<ResourceRemovement> rmList);
}
