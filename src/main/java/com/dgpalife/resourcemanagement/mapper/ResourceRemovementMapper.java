package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ResourceRemovement;

public interface ResourceRemovementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceRemovement record);

    int insertSelective(ResourceRemovement record);

    ResourceRemovement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceRemovement record);

    int updateByPrimaryKey(ResourceRemovement record);
}