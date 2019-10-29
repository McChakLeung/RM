package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.User;

import java.util.List;
import java.util.Map;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectDepartmentList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);
}