package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.ResourceMigration;

import java.util.List;

public interface ResourceMigrationService {
    void saveMigrationResourceList(List<ResourceMigration> resourceMigrationList);
}
