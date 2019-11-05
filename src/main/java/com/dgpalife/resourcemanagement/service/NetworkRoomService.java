package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.NetworkRoom;

import java.util.List;
import java.util.Map;

public interface NetworkRoomService {
    List<NetworkRoom> selectNetworkRoomListByWorkplaceId(Long workplaceId);

    int saveNetworkRoom(NetworkRoom networkRoom);

    NetworkRoom selectNetworkRoomById(Long id);

    int updateNetworkRoom(NetworkRoom networkRoom);

    void deleteNetworkRoomById(Long id);

    List<Object> selectNetworkRoomListByParams(Map<String,Object> params);

    int selectNetworkroomCount(Map<String,Object> params);
}
