package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Ticket;

public interface TicketService {
    void insertTicket(Ticket ticket);

    Ticket queryTicketByPiid(String processInstanceId);
}
