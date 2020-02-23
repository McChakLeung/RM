//package com.dgpalife.resourcemanagement.service.activiti;
//
//import com.dgpalife.resourcemanagement.mapper.RoleMapper;
//import com.dgpalife.resourcemanagement.mapper.UserMapper;
//import com.dgpalife.resourcemanagement.mapper.UserRoleMapper;
//import com.dgpalife.resourcemanagement.model.Role;
//import com.dgpalife.resourcemanagement.model.UserRole;
//import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.GroupQuery;
//import org.activiti.engine.impl.GroupQueryImpl;
//import org.activiti.engine.impl.Page;
//import org.activiti.engine.impl.persistence.entity.GroupEntity;
//import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 自定义角色管理
// * 具体方法进入GroupEntityManager中查看
// */
//@Component
//public class CustomGroupEntityManager extends GroupEntityManager {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private UserRoleMapper userRoleMapper;
//
//    @Autowired
//    private RoleMapper roleMapper;
//
//
//    public CustomGroupEntityManager() {
//        super();
//    }
//
//    @Override
//    public Group createNewGroup(String groupId) {
//        return super.createNewGroup(groupId);
//    }
//
//    @Override
//    public void insertGroup(Group group) {
//        super.insertGroup(group);
//    }
//
//    @Override
//    public void updateGroup(Group updatedGroup) {
//        super.updateGroup(updatedGroup);
//    }
//
//    @Override
//    public void deleteGroup(String groupId) {
//        super.deleteGroup(groupId);
//    }
//
//    @Override
//    public GroupQuery createNewGroupQuery() {
//        return super.createNewGroupQuery();
//    }
//
//    @Override
//    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
//        return super.findGroupByQueryCriteria(query, page);
//    }
//
//    @Override
//    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
//        return super.findGroupCountByQueryCriteria(query);
//    }
//
//    @Override
//    public List<Group> findGroupsByUser(String userId) {
//        if(userId==null){
//            return null;
//        }
//        System.out.println("userId:"+userId);
//        com.dgpalife.resourcemanagement.model.User user = userMapper.selectByPrimaryKey(Long.parseLong(userId));
//        List<UserRole> userRoleList= userRoleMapper.queryRoleListByUserId(user.getId());
//        System.out.println("userRoleList size:"+userRoleList.size());
//        List<Group> gs=new ArrayList<Group>();
//        GroupEntity groupEntity;
//        Long roleId;
//        String activitiRole;
//        for (UserRole userRole:userRoleList) {
//            groupEntity=new GroupEntity();
//            groupEntity.setRevision(1);
//            groupEntity.setType("assignment");
//            roleId=userRole.getRoleId();
//            Role role = roleMapper.selectByPrimaryKey(roleId);
//            groupEntity.setId(role.getId().toString());
//            groupEntity.setName(role.getName());
//            gs.add(groupEntity);
//        }
//        return gs;
//    }
//
//    @Override
//    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
//        return super.findGroupsByNativeQuery(parameterMap, firstResult, maxResults);
//    }
//
//    @Override
//    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
//        return super.findGroupCountByNativeQuery(parameterMap);
//    }
//
//    @Override
//    public boolean isNewGroup(Group group) {
//        return super.isNewGroup(group);
//    }
//}
