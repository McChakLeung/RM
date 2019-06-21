package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

    List<HashMap> selectAllResource();

    List<HashMap> selectByOption(Map<String, Object> optionMap);
}