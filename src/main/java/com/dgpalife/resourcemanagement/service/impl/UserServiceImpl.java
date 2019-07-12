package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.UserMapper;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUserNameAndPassword(String loginacc, String password) {
        User user = userMapper.selectUserByUserNameAndPassword(loginacc,password);
        if(user == null){
            return null;
        }
        user.setLastlogin(new Date());
        userMapper.updateLastLoginTime(user);
        return user;
    }
}
