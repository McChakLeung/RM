package com.dgpalife.resourcemanagement.listener;

import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.service.PermissionService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebListener
public class SystemLaunchListener implements ServletContextListener {
    public SystemLaunchListener() {

    }

    /*
    *  监听器初始化操作
    *
    */
    public void contextInitialized(ServletContextEvent sce) {

        //从Tomcat中获取SerletContext对象
        ServletContext application = sce.getServletContext();

        //从Tomcat中获得spring的IOC容器
        ApplicationContext ioc =  WebApplicationContextUtils.getWebApplicationContext(application);
        //从IOC容器中获取PermissionService对象
        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<Permission> permissionList = permissionService.selectAllPermission();
        Set<String> allPermissionUris = new HashSet<>();
        for (Permission permission : permissionList){
            if(StringUtil.isNotEmpty(permission.getUrl())){
                allPermissionUris.add("/"+permission.getUrl());
            }
        }
        application.setAttribute(Const.ALL_PERMISSION_URI,allPermissionUris);
    }



}
