package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.TicketMapper;
import com.dgpalife.resourcemanagement.model.Ticket;
import com.dgpalife.resourcemanagement.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public void insertTicket(Ticket ticket) {
        ticketMapper.insertSelective(ticket);
    }

    @Override
    public Ticket queryTicketByPiid(String processInstanceId) {
        return ticketMapper.queryTicketByPiid(processInstanceId);
    }

    @Override
    public Ticket queryTicketByOrderID(Long id) {
        return ticketMapper.queryTicketByOrderID(id);
    }
}
