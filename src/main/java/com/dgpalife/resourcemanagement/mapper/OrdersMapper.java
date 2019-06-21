package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<HashMap> selectOrdersByCreatorName(Map<String, Object> optionMap);

    List<HashMap> selectOrdersNotIncludeOption();

    List<HashMap<String,Object>> selectOrdersByCreatorNameAndOptionMap(Map<String, Object> optionMap);
}