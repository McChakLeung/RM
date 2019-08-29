package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.UserRoleMapper;
import com.dgpalife.resourcemanagement.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public List<Integer> queryRoleByUserId(Long id) {
        return userRoleMapper.queryRoleByUserId(id);
    }
}
