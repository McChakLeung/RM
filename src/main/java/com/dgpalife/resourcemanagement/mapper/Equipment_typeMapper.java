package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Equipment_type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface Equipment_typeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Equipment_type record);

    int insertSelective(Equipment_type record);

    Equipment_type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Equipment_type record);

    int updateByPrimaryKey(Equipment_type record);

    List<Object> queryEquipmentType(Map<String, Object> params);

    int selectCount(Map<String, Object> params);
}