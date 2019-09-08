package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ConstructDetail;

public interface ConstructDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConstructDetail record);

    int insertSelective(ConstructDetail record);

    ConstructDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConstructDetail record);

    int updateByPrimaryKey(ConstructDetail record);
}