package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.MD5Util;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.UserMapper;
import com.dgpalife.resourcemanagement.mapper.UserRoleMapper;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;
import com.dgpalife.resourcemanagement.service.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IdentityService identityService;

    @Override
    public User selectUserByUserNameAndPassword(String loginacct, String password) {
        User user = userMapper.selectUserByUserNameAndPassword(loginacct,password);
        if(user == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String lastlogin = simpleDateFormat.format(new Date());
        user.setLastlogin(lastlogin);
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

    @Override
    public Integer saveUser(User user) {
        //将日期类转换成字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createtime = simpleDateFormat.format(new Date());
        user.setCreatetime(createtime);
        user.setStatus(true);
        user.setPassword(MD5Util.digest(Const.PASSWORD));
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectUserByID(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUserById(Long id) {
         userMapper.deleteByPrimaryKey(id);
         userRoleMapper.deleteByUserId(id);
    }

    @Override
    public void saveUserRoleByBatch(List<UserRole> userRoleList) {
        for(UserRole userRole : userRoleList){
            userRoleMapper.insertSelective(userRole);
        }

    }

    @Override
    public void deleteUserRoleByBatch(List<UserRole> userRoleList) {
        for(UserRole userRole : userRoleList){
            userRoleMapper.deleteByRoleId(userRole.getRoleId());
        }
    }

    @Override
    public User selectUserByLoginAccAndUserPassword(Map<String, Object> params) {
        User user = userMapper.selectUserByLoginAccAndUserPassword(params);
        return user;
    }

    @Override
    public void saveToActiviti(User user, Long orgId, List<Long> roleIds, boolean synToActiviti) throws Exception {
        //从变量user中查询数据出数据库中的userid，并转换成string类型，用于Activiti Identify同步
        String userId = userMapper.selectByPrimaryKey(user.getId()).getId().toString();
        System.out.println(userId);

        //保存系统用户
        userMapper.insert(user);

        // 同步数据到Activiti Identify模块
        if(synToActiviti) {
            UserQuery userQuery = identityService.createUserQuery();
            List<org.activiti.engine.identity.User> activitiUsers = userQuery.userId(userId).list();

            if(activitiUsers.size() == 1) {
                updateActivitiData(user, roleIds, activitiUsers.get(0));
            }else if (activitiUsers.size() > 1) {
                String errorMsg = "发现重复用户：id="+ userId;
                throw new RuntimeException(errorMsg);
            }else{
                new ActivitiUser(user, roleIds);
            }
        }
    }

    @Override
    public void delete(Long userId, boolean synToActiviti) throws Exception {

    }

    @Override
    public void synAllUserAndRoleToActiviti() throws Exception {

    }

    @Override
    public void deleteAllActivitiIdentifyData() throws Exception {

    }


}
