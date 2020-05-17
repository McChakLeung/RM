package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Item;

public interface ItemMapper {
    int insert(Item record);

    int insertSelective(Item record);
}