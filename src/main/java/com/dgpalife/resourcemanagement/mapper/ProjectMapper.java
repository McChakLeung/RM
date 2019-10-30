package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Map<String,Object>> selectProjectList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);
}