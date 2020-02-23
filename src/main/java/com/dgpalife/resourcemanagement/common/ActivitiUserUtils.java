//package com.dgpalife.resourcemanagement.common;
//
//import com.dgpalife.resourcemanagement.model.Role;
//import org.activiti.engine.identity.Group;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.impl.persistence.entity.GroupEntity;
//import org.activiti.engine.impl.persistence.entity.UserEntity;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 将业务中自己定义的用户、角色转化为activiti中使用的user、group
// */
//
//public class ActivitiUserUtils {
//    public static User toActivitiUser(com.dgpalife.resourcemanagement.model.User user){
//        User userEntity = new UserEntity();
//        userEntity.setId(user.getId().toString());
//        userEntity.setFirstName(user.getLoginacct());
//        userEntity.setLastName(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        return userEntity;
//    }
//    public static GroupEntity toActivitiGroup(Role role){
//        GroupEntity groupEntity=new GroupEntity();
//        groupEntity.setRevision(1);
//        groupEntity.setType("assignment");
//        groupEntity.setId(role.getId().toString());
//        groupEntity.setName(role.getName());
//        return groupEntity;
//    }
//    public static List<Group> toActivitiGroups(List<Role> sysRoles){
//        List<Group> groups=new ArrayList<Group>();
//        for (Role role:sysRoles) {
//            GroupEntity groupEntity=toActivitiGroup(role);
//            groups.add(groupEntity);
//        }
//        return groups;
//    }
//}
