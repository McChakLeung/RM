package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ResourceMigration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceMigrationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceMigration record);

    int insertSelective(ResourceMigration record);

    ResourceMigration selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceMigration record);

    int updateByPrimaryKey(ResourceMigration record);

    void saveMigrationResourceList(List<ResourceMigration> resourceMigrationList);

    List<ResourceMigration> selectByOrderID(Long id);
}