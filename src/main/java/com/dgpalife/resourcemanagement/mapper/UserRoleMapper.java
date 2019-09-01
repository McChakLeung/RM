package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<Integer> queryRoleByUserId(Long id);

    void deleteByRoleId(Long roleId);

    void deleteByUserId(Long id);

    //void saveUserRoleByBatch(List<UserRole> userRoleList);
}