package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.ResourceMigrationMapper;
import com.dgpalife.resourcemanagement.model.ResourceMigration;
import com.dgpalife.resourcemanagement.service.ResourceMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceMigrationServiceImpl implements ResourceMigrationService {

    @Autowired
    private ResourceMigrationMapper resourceMigrationMapper;

    @Override
    public void saveMigrationResourceList(List<ResourceMigration> resourceMigrationList) {
        resourceMigrationMapper.saveMigrationResourceList(resourceMigrationList);
    }
}
