package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Order_resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Order_resourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order_resource record);

    int insertSelective(Order_resource record);

    Order_resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order_resource record);

    int updateByPrimaryKey(Order_resource record);

    List<Order_resource> queryOrderlistByResourceID(Long id);
}