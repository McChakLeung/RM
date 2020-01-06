package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    Ticket queryTicketByPiid(String processInstanceId);

    Ticket queryTicketByOrderID(Long id);

}