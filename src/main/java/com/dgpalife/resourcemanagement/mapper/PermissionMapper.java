package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectAllPermission();

    List<Permission> queryPermissionByUserIDAndRoleID(Map<String, Object> params);
}