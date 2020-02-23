//package com.dgpalife.resourcemanagement.service.activiti;
//
//import org.activiti.engine.impl.interceptor.Session;
//import org.activiti.engine.impl.interceptor.SessionFactory;
//import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.annotation.Resource;
//
///**
// * 自定义user的管理工厂类
// */
//public class CustomUserEntityManagerFactory implements SessionFactory {
//
//    @Resource
//    private CustomUserEntityManager customUserEntityManager;
//
//    @Override
//    public Class<?> getSessionType() {
//        //此处也必须为activiti原生类
//        return UserIdentityManager.class;
//    }
//
//    @Override
//    public Session openSession() {
//        return customUserEntityManager;
//    }
//
//    @Autowired
//    public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager){
//        this.customUserEntityManager = customUserEntityManager;
//    }
//}
