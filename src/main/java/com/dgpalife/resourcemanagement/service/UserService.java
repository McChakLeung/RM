package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;

import java.util.List;
import java.util.Map;

public interface UserService {
    User selectUserByUserNameAndPassword(String loginacct, String password);

    Page<User> selectUserList(Map<String, Object> params);

    Integer saveUser(User user);

    User selectUserByID(Long id);

    Integer updateUser(User user);

    void deleteUserById(Long id);

    void saveUserRoleByBatch(List<UserRole> userRoleList);

    void deleteUserRoleByBatch(List<UserRole> userRoleList);

    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);
}
