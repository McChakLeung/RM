package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.UserMapper;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUserNameAndPassword(String loginacct, String password) {
        User user = userMapper.selectUserByUserNameAndPassword(loginacct,password);
        if(user == null){
            return null;
        }
        user.setLastlogin(new Date());
        userMapper.updateLastLoginTime(user);
        return user;
    }

    @Override
    public Page<User> selectUserList(Map<String, Object> params) {
        Page<User> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询用户列表数据
        params.put("startline",page.getStartline());
        List<User> datas = userMapper.selectUserList(params);

        page.setDatalist(datas);

        //查询用户总数
        Integer totalsize = userMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }
}
