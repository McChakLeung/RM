package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    List<Permission> selectAllPermission();

    int insert(Permission permission);

    Permission selectPermissionById(Integer id);

    int updateByPrimaryKeySelective(Permission permission);

    int deletePermission(Integer id);

    List<Permission> queryPermissionByUserIDAndRoleID(Map<String, Object> params);
}
