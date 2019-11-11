package com.dgpalife.resourcemanagement.service.impl;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomGroupEntityManagerFactory implements SessionFactory {

    @Resource
    private CustomGroupManager customGroupManager;

    @Override
    public Class<?> getSessionType() {
        return null;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return null;
    }
}
