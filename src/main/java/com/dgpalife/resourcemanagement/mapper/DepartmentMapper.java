package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper {
    Long deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectDepartmentList();

    List<Department> selectAllDepartmentExceptNullPID();

    List<Department> selectDepartmentByPID(Long id);
}