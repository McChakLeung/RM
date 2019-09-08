package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ResourceMigration;

public interface ResourceMigrationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceMigration record);

    int insertSelective(ResourceMigration record);

    ResourceMigration selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceMigration record);

    int updateByPrimaryKey(ResourceMigration record);
}