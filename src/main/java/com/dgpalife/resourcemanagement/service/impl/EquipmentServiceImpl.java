package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.EquipmentMapper;
import com.dgpalife.resourcemanagement.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService{

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public int queryByEquipmentSn(String equipmentSn) {
        return equipmentMapper.queryByEquipmentSn(equipmentSn);
    }
}
