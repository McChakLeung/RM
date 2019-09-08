package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Workplace;

public interface WorkplaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Workplace record);

    int insertSelective(Workplace record);

    Workplace selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Workplace record);

    int updateByPrimaryKey(Workplace record);
}