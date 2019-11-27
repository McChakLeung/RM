package com.dgpalife.resourcemanagement.service.activiti;

import com.dgpalife.resourcemanagement.common.ActivitiUserUtils;
import com.dgpalife.resourcemanagement.service.UserService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component
public class ActUserEntityService implements UserEntityManager,Session {

    @Autowired
    private UserService userService;

//    ServletContextEvent sce;
//
//    public UserService getUserService() {
//        if (userService == null){
//            //从Tomcat中获取SerletContext对象
//            ServletContext application = sce.getServletContext();
//
//            //从Tomcat中获得spring的IOC容器
//            ApplicationContext ioc =  WebApplicationContextUtils.getWebApplicationContext(application);
//            //从IOC容器中获取PermissionService对象
//            UserService userService = ioc.getBean(UserService.class);
//        }
//        return userService;
//    }

    @Override
    public User createNewUser(String userId) {

        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl userQuery, Page page) {
        return null;
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl userQuery) {
        return 0;
    }

    @Override
    public List<Group> findGroupsByUser(String s) {
        return null;
    }

    @Override
    public UserQuery createNewUserQuery() {
        return null;
    }

    @Override
    public Boolean checkPassword(String s, String s1) {
        return null;
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> map, int i, int i1) {
        return null;
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewUser(User user) {
        return false;
    }

    @Override
    public Picture getUserPicture(String s) {
        return null;
    }

    @Override
    public void setUserPicture(String s, Picture picture) {

    }

    @Override
    public void deletePicture(User user) {

    }

    @Override
    public UserEntity create() {
        return null;
    }

    @Override
    public UserEntity findById(String userId) {
        return ActivitiUserUtils.toActivitiUser(userService.selectUserByID(Long.parseLong(userId)));
    }

    @Override
    public void insert(UserEntity entity) {

    }

    @Override
    public void insert(UserEntity entity, boolean b) {

    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity, boolean b) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void delete(UserEntity entity, boolean b) {

    }

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }
}
