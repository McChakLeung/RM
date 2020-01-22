package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.Equipment_typeMapper;
import com.dgpalife.resourcemanagement.service.EquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipmentTypeServiceImpl implements EquipmentTypeService{

    @Autowired
    private Equipment_typeMapper equipment_typeMapper;

    @Override
    public List<Object> queryEquipmentType(Map<String,Object> params) {
        return equipment_typeMapper.queryEquipmentType(params);
    }

    @Override
    public int selectCount(Map<String,Object> params) {
        return equipment_typeMapper.selectCount(params);
    }
}
