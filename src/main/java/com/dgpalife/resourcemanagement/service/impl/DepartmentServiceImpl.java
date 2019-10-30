package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.DepartmentMapper;
import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public List<Department> selectAllDepartment() {
        return departmentMapper.selectDepartmentList();
    }

    @Override
    public Long deleteDepartmentById(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department selectDepartmentById(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveDepartment(Department department) {
        return departmentMapper.insertSelective(department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }
}
