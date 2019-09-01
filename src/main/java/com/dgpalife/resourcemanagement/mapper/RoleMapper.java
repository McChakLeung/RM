package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryAllRole();

    List<Role> queryRoleListByParams(Map<String, Object> params);

    List<Permission> queryPermissionByRoleID(Integer roleId);

    void deletePermissionByRoleID(Long roleId);

}