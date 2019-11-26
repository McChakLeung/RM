package com.dgpalife.resourcemanagement.service.activiti;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActUserEntityServiceFactory implements SessionFactory{

    @Autowired
    private ActUserEntityService actUserEntityService;

    @Override
    public Class<?> getSessionType() {
        return UserEntityManager.class;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return actUserEntityService;
    }
}
