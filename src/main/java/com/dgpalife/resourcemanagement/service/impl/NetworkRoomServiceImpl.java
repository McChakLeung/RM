package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.NetworkRoomMapper;
import com.dgpalife.resourcemanagement.model.NetworkRoom;
import com.dgpalife.resourcemanagement.service.NetworkRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkRoomServiceImpl implements NetworkRoomService {

    @Autowired
    private NetworkRoomMapper networkRoomMapper;

    @Override
    public List<NetworkRoom> selectNetworkRoomListByWorkplaceId(Long workplaceId) {
        return networkRoomMapper.selectNetworkRoomListByWorkplaceId(workplaceId);
    }
}
