package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Equipment_type;

public interface Equipment_typeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Equipment_type record);

    int insertSelective(Equipment_type record);

    Equipment_type selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Equipment_type record);

    int updateByPrimaryKey(Equipment_type record);
}