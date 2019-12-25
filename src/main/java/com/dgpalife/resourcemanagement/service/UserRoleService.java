package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.UserRole;

import java.util.List;

public interface UserRoleService {

    List<Integer> queryRoleByUserId(Long id);

    List<UserRole> queryRoleByUserIdAndRoleId(Long user_id,Long role_id);
}
