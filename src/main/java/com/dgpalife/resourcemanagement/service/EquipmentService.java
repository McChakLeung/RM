package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.model.Equipment;
import com.dgpalife.resourcemanagement.model.User;

import java.util.List;
import java.util.Map;

public interface EquipmentService {
    int queryByEquipmentSn(String equipmentSn);

    List<Object> selectEquipmentByQueryText(Map<String, Object> params);

    int selectCountByQueryText(Map<String, Object> params);

    void insertEquipmentList(List<Equipment> equipmentList,User user);
}
