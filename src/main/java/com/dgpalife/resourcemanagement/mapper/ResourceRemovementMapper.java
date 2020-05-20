package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ResourceRemovement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResourceRemovementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceRemovement record);

    int insertSelective(ResourceRemovement record);

    ResourceRemovement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceRemovement record);

    int updateByPrimaryKey(ResourceRemovement record);

    int saveResourceRemovementListByBatch(List<ResourceRemovement> rmList);

    List<ResourceRemovement> selectResourceRemovementListByOrderId(Long id);

    void deleteByOrderId(Long id);
}