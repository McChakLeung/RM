package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}