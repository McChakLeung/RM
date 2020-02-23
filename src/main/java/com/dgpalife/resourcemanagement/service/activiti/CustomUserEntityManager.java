//package com.dgpalife.resourcemanagement.service.activiti;
//
//import com.dgpalife.resourcemanagement.common.ActivitiUserUtils;
//import com.dgpalife.resourcemanagement.mapper.RoleMapper;
//import com.dgpalife.resourcemanagement.mapper.UserMapper;
//import com.dgpalife.resourcemanagement.mapper.UserRoleMapper;
//import com.dgpalife.resourcemanagement.model.Role;
//import com.dgpalife.resourcemanagement.model.UserRole;
//import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.Picture;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.identity.UserQuery;
//import org.activiti.engine.impl.Page;
//import org.activiti.engine.impl.UserQueryImpl;
//import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
//import org.activiti.engine.impl.persistence.entity.UserEntity;
//import org.activiti.engine.impl.persistence.entity.UserEntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 自定义用户管理类，管理用户方法
// * 添加其他方法
// */
//@Component
//public class CustomUserEntityManager extends UserEntityManager {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    @Autowired
//    private RoleMapper roleMapper;
//
//    public CustomUserEntityManager() {
//        super();
//    }
//
//    @Override
//    public User createNewUser(String userId) {
//        return super.createNewUser(userId);
//    }
//
//    @Override
//    public void insertUser(User user) {
//        super.insertUser(user);
//    }
//
//    @Override
//    public void updateUser(User updatedUser) {
//        super.updateUser(updatedUser);
//    }
//
//    /**
//     * 查询自定义用户模块，并转换成activiti的用户实体
//     * @param userId 自定义用户模块id
//     * @return
//     */
//    @Override
//    public User findUserById(String userId) {
//        User userEntity= new UserEntity();
//        com.dgpalife.resourcemanagement.model.User user= userMapper.selectByPrimaryKey(Long.parseLong(userId));
//        //将自定义的user转化为activiti的类
//        userEntity= ActivitiUserUtils.toActivitiUser(user);
//        //返回activiti的实体类
//        return userEntity;
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        super.deleteUser(userId);
//    }
//
//    @Override
//    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
//        return super.findUserByQueryCriteria(query, page);
//    }
//
//    @Override
//    public long findUserCountByQueryCriteria(UserQueryImpl query) {
//        return super.findUserCountByQueryCriteria(query);
//    }
//
//    @Override
//    public List<Group> findGroupsByUser(String userId) {
//        if(userId==null){
//            return null;
//        }
//        List<Role> roleList=new ArrayList<Role>();
//        List<UserRole> userRoleList = userRoleMapper.queryRoleListByUserId(Long.parseLong(userId));
//        for (UserRole userrole:userRoleList) {
//            Long roleId = userrole.getRoleId();
//            Role role = roleMapper.selectByPrimaryKey(roleId);
//            roleList.add(role);
//        }
//        List<Group> gs=null;
//        gs=ActivitiUserUtils.toActivitiGroups(roleList);
//        return gs;
//    }
//
//    @Override
//    public UserQuery createNewUserQuery() {
//        return super.createNewUserQuery();
//    }
//
//    @Override
//    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
//        return super.findUserInfoByUserIdAndKey(userId, key);
//    }
//
//    @Override
//    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
//        return super.findUserInfoKeysByUserIdAndType(userId, type);
//    }
//
//    @Override
//    public Boolean checkPassword(String userId, String password) {
//        return super.checkPassword(userId, password);
//    }
//
//    @Override
//    public List<User> findPotentialStarterUsers(String proceDefId) {
//        return super.findPotentialStarterUsers(proceDefId);
//    }
//
//    @Override
//    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//        return super.findUsersByNativeQuery(parameterMap, firstResult, maxResults);
//    }
//
//    @Override
//    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
//        return super.findUserCountByNativeQuery(parameterMap);
//    }
//
//    @Override
//    public boolean isNewUser(User user) {
//        return super.isNewUser(user);
//    }
//
//    @Override
//    public Picture getUserPicture(String userId) {
//        return super.getUserPicture(userId);
//    }
//
//    @Override
//    public void setUserPicture(String userId, Picture picture) {
//        super.setUserPicture(userId, picture);
//    }
//}
