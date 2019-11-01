package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Workplace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkplaceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Workplace record);

    int insertSelective(Workplace record);

    Workplace selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Workplace record);

    int updateByPrimaryKey(Workplace record);

    List<Workplace> selectWorkplaceList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    List<Object> selectAll();

    int selectNum();
}