package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Workplace;

import java.util.List;
import java.util.Map;

public interface WorkplaceService {
    Page<Workplace> selectWorkplaceList(Map<String, Object> params);

    int saveWorkplace(Workplace workplace);

    Workplace selectWorkplaceById(Long id);

    int updateWorkplace(Workplace workplace);

    void deleteWorkplaceAndNetworkRoomByWorkplaceId(Long id);

    List<Object> selectWorkplace();

    int selectCount();
}
