package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.NetworkRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NetworkRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NetworkRoom record);

    int insertSelective(NetworkRoom record);

    NetworkRoom selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NetworkRoom record);

    int updateByPrimaryKey(NetworkRoom record);

    List<NetworkRoom> selectNetworkRoomListByWorkplaceId(Long workplaceId);
}