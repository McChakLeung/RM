package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.NetworkRoom;

import java.util.List;

public interface NetworkRoomService {
    List<NetworkRoom> selectNetworkRoomListByWorkplaceId(Long workplaceId);

    int saveNetworkRoom(NetworkRoom networkRoom);

    NetworkRoom selectNetworkRoomById(Long id);

    int updateNetworkRoom(NetworkRoom networkRoom);

    void deleteNetworkRoomById(Long id);
}
