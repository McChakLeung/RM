package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> queryAllRole();

    Page<Role> queryRoleListByParams(Map<String, Object> params);

    List<Permission> queryPermissionByRoleID(Integer roleId);

    Integer processAssignPermission(Long roleId, List<Long> ids);

    Integer saveRole(Role role);

    Role queryRoleByRoleId(Long roleId);

    Integer updateRole(Role role);

    void deleteRoleByKey(Long roleId);

    List queryRoleInfo(Long id);
}
