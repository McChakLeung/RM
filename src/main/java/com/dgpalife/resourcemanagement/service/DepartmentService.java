package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.User;

import java.util.Map;

public interface DepartmentService {
    Page<User> selectDepartmemtList(Map<String, Object> params);
}
