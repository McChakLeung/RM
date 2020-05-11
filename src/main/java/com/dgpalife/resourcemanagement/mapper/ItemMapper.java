package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Item;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);
}