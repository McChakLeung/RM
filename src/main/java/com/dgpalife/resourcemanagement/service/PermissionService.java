package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> selectAllPermission();

    int insert(Permission permission);

    Permission selectPermissionById(Integer id);

    int updateByPrimaryKeySelective(Permission permission);

    int deletePermission(Integer id);
}
