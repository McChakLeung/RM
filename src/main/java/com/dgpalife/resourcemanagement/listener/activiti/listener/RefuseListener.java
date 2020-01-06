package com.dgpalife.resourcemanagement.listener.activiti.listener;

import com.dgpalife.resourcemanagement.common.ApplicationContextUtils;
import com.dgpalife.resourcemanagement.model.Ticket;
import com.dgpalife.resourcemanagement.service.OrderService;
import com.dgpalife.resourcemanagement.service.TicketService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

public class RefuseListener implements ExecutionListener {


    @Override
    public void notify(DelegateExecution delegateExecution) {
        // 获取监听器传入的参数
        Long order_id = (Long)delegateExecution.getVariable("order_id");
        String comment = (String)delegateExecution.getVariable("comment");

        // 获取Spring容器
        ApplicationContext context = ApplicationContextUtils.applicationContext;

        // 获取service
        OrderService orderService = context.getBean(OrderService.class);
        TicketService ticketService = context.getBean(TicketService.class);

        // 更新工单的审核状态
        orderService.refuseAuth(order_id,comment);

        // 获取ticket
        Ticket ticket = ticketService.queryTicketByOrderID(order_id);

        // 改变流程审批单的状态
        ticket.setStatus("1");
        ticketService.updateTicket(ticket);

    }
}
