package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemMapper {
    int insert(Item record);

    int insertSelective(Item record);

    List<Object> selectItemByQueryText(Map<String, Object> params);

    int selectCountByQueryText(Map<String, Object> params);
}