package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EquipmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

    int queryByEquipmentSn(String equipmentSn);

    List<Object> selectEquipmentByQueryText(Map<String, Object> params);

    int selectCountByQueryText(Map<String, Object> params);

    Equipment selectByEquipmenSN(String equipment_sn);
}