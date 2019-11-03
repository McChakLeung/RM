package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.NetworkRoomMapper;
import com.dgpalife.resourcemanagement.mapper.WorkplaceMapper;
import com.dgpalife.resourcemanagement.model.NetworkRoom;
import com.dgpalife.resourcemanagement.model.Workplace;
import com.dgpalife.resourcemanagement.service.NetworkRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkRoomServiceImpl implements NetworkRoomService {

    @Autowired
    private NetworkRoomMapper networkRoomMapper;

    @Autowired
    private WorkplaceMapper workplaceMapper;

    @Override
    public List<NetworkRoom> selectNetworkRoomListByWorkplaceId(Long workplaceId) {
        return networkRoomMapper.selectNetworkRoomListByWorkplaceId(workplaceId);
    }

    @Override
    public int saveNetworkRoom(NetworkRoom networkRoom) {
        return networkRoomMapper.insertSelective(networkRoom);
    }

    @Override
    public NetworkRoom selectNetworkRoomById(Long id) {
        NetworkRoom networkRoom = networkRoomMapper.selectByPrimaryKey(id);
        Workplace workplace = workplaceMapper.selectByPrimaryKey(networkRoom.getWorkplaceId());
        networkRoom.setWorkplace(workplace);
        return networkRoom;
    }

    @Override
    public int updateNetworkRoom(NetworkRoom networkRoom) {
        return networkRoomMapper.updateByPrimaryKeySelective(networkRoom);
    }

    @Override
    public void deleteNetworkRoomById(Long id) {
        networkRoomMapper.deleteByPrimaryKey(id);
    }
}
