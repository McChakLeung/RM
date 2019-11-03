package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    Long insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKeyWithBLOBs(Order record);

    int updateByPrimaryKey(Order record);

    List<Map<String,Object>> selectOrderListByProjectId(Long id);

    List<Map<String,Object>> selectOrderListByUserId(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);
}