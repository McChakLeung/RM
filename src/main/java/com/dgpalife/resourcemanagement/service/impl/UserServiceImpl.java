package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.MD5Util;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.RoleMapper;
import com.dgpalife.resourcemanagement.mapper.UserMapper;
import com.dgpalife.resourcemanagement.mapper.UserRoleMapper;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;
import com.dgpalife.resourcemanagement.service.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
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

    @Autowired
    private RoleMapper roleMapper;

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
    public User queryUserByDistrictID(Long district_id) {
        return userMapper.queryUserByDistrictID(district_id);
    }

//    @Override
//    public void saveToActiviti(User user, Long orgId, List<Long> roleIds, boolean synToActiviti) throws Exception {
//        //从变量user中查询数据出数据库中的userid，并转换成string类型，用于Activiti Identify同步
//        String userId = userMapper.selectByPrimaryKey(user.getId()).getId().toString();
//        System.out.println(userId);
//
//        //保存系统用户
//        userMapper.insert(user);
//
//        // 同步数据到Activiti Identify模块
//        if(synToActiviti) {
//            UserQuery userQuery = identityService.createUserQuery();
//            List<org.activiti.engine.identity.User> activitiUsers = userQuery.userId(userId).list();
//
//            if(activitiUsers.size() == 1) {
//                updateActivitiData(user, roleIds, activitiUsers.get(0));
//            }else if (activitiUsers.size() > 1) {
//                String errorMsg = "发现重复用户：id="+ userId;
//                throw new RuntimeException(errorMsg);
//            }else{
//                new ActivitiUser(user, roleIds);
//            }
//        }
//    }
//
//    @Override
//    public void delete(Long userId, boolean synToActiviti) throws Exception {
//        User user = userMapper.selectByPrimaryKey(userId);
//        if(user == null) {
//            throw new Exception("删除用户时，找不到ID为"+ userId + "的用户");
//        }
//
//        /**
//         * 同步删除Activiti User Group
//         */
//        if(synToActiviti) {
//            // 同步删除Activiti User
//            List<Role> roleList = user.getRoleList();
//            for(Role role : roleList) {
//                identityService.deleteMembership(userId.toString(), role.getEnName());
//            }
//
//            // 同步删除Activiti User
//            identityService.deleteUser(userId.toString());
//        }
//
//        // 删除本系统用户
//        accountManager.deleteUser(userId);
//
//        // 删除考勤机用户
//        if(synToChecking) {
//            checkingAccountManager.deleteEntity(userId);
//        }
//    }
//
//    @Override
//    public void synAllUserAndRoleToActiviti() throws Exception {
//
//    }
//
//    @Override
//    public void deleteAllActivitiIdentifyData() throws Exception {
//
//    }
//
//    /**
//     * 添加工作流用户以及角色
//     * @param user      用户对象{@link User}
//     * @param roleIds   用户拥有的角色ID集合
//     */
//    private void newActivitiUser(User user, List<Long> roleIds) {
//        String userId = user.getId().toString();
//
//        // 添加用户
//        saveActivitiUser(user);
//
//        // 添加membership
//        addMembershipToIdentify(roleIds, userId);
//    }
//
//    /**
//     * 添加一个用户到Activiti {@link org.activiti.engine.identity.User}
//     * @param user  用户对象, {@link User}
//     */
//    private void saveActivitiUser(User user) {
//        String userId = user.getId().toString();
//        org.activiti.engine.identity.User activitiUser = identityService.newUser(userId);
//        cloneAndSaveActivitiUser(user, activitiUser);
//    }
//
//    /**
//     * 添加Activiti Identify的用户于组关系
//     * @param roleIds   角色ID集合
//     * @param userId    用户ID
//     */
//    private void addMembershipToIdentify(List<Long> roleIds, String userId) {
//        for(Long roleId : roleIds) {
//            Role role = roleManager.getEntity(roleId);
//            identityService.createMembership(userId, role.getEnName());
//        }
//    }
//
//    /**
//     * 更新工作流用户以及角色
//     * @param user          用户对象{@link User}
//     * @param roleIds       用户拥有的角色ID集合
//     * @param activitiUser  Activiti引擎的用户对象，{@link org.activiti.engine.identity.User}
//     */
//    private void updateActivitiData(User user, List<Long> roleIds, org.activiti.engine.identity.User activitiUser) {
//
//        String userId = user.getId().toString();
//
//        // 更新用户主体信息
//        cloneAndSaveActivitiUser(user, activitiUser);
//
//        // 删除用户的membership
//        List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
//        for(Group group : activitiGroups) {
////            logger.debug("delete group from activit: {}", ToStringBuilder.reflectionToString(group));
//            identityService.deleteMembership(userId, group.getId());
//        }
//
//        // 添加membership
//        addMembershipToIdentify(roleIds, userId);
//    }
//
//    /**
//     * 使用系统用户对象属性设置到Activiti User对象中
//     * @param user          系统用户对象
//     * @param activitiUser  Activiti User
//     */
//    private void cloneAndSaveActivitiUser(User user, org.activiti.engine.identity.User activitiUser) {
//        activitiUser.setFirstName(user.getName());
//        activitiUser.setLastName(StringUtils.EMPTY);
//        activitiUser.setPassword(StringUtils.EMPTY);
//        activitiUser.setEmail(user.getEmail());
//        identityService.saveUser(activitiUser);
//    }

}
