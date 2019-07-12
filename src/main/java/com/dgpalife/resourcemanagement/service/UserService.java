package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.User;

public interface UserService {
    User selectUserByUserNameAndPassword(String loginacc, String password);
}
