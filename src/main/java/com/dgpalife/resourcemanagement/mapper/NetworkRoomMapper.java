package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.NetworkRoom;

public interface NetworkRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NetworkRoom record);

    int insertSelective(NetworkRoom record);

    NetworkRoom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NetworkRoom record);

    int updateByPrimaryKey(NetworkRoom record);
}