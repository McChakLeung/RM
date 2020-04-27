package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.mapper.EquipmentMapper;
import com.dgpalife.resourcemanagement.model.Equipment;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService{

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public int queryByEquipmentSn(String equipmentSn) {
        return equipmentMapper.queryByEquipmentSn(equipmentSn);
    }

    @Override
    public List<Object> selectEquipmentByQueryText(Map<String, Object> params) {
        return equipmentMapper.selectEquipmentByQueryText(params);
    }

    @Override
    public int selectCountByQueryText(Map<String, Object> params) {
        return equipmentMapper.selectCountByQueryText(params);
    }

    @Override
    public void insertEquipmentList(List<Equipment> equipmentList,User user) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(Equipment equipment:equipmentList){
            equipment.setCreateTime(sdf.format(date));
            equipment.setCreatorId(user.getId());
            equipmentMapper.insertSelective(equipment);
        }
    }
}
