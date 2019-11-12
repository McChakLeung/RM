package com.dgpalife.resourcemanagement.service.activiti;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomUserEntityManagerFactory implements SessionFactory {

    @Resource
    private CustomUserManager customUserManager;

    @Override
    public Class<?> getSessionType() {
        return null;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return null;
    }
}
