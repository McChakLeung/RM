package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.User;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Department> selectAllDepartment();

    Long deleteDepartmentById(Long id);

    Department selectDepartmentById(Long id);

    int saveDepartment(Department department);

    int updateDepartment(Department department);

    List<Department> selectAllDepartmentExceptNullPID();

    List<Department> selectDepartmentByPID(Long id);
}
