package com.dgpalife.resourcemanagement.listener.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class NoListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("审批不通过");
    }
}
