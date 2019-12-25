package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<UserRole> queryRoleByUserIdAndRoleId(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

    void deleteByRoleId(Long roleId);

    void deleteByUserId(Long id);

    List<UserRole> queryRoleListByUserId(Long user_id);

    //void saveUserRoleByBatch(List<UserRole> userRoleList);
}