package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.ConstructDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConstructDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConstructDetail record);

    int insertSelective(ConstructDetail record);

    ConstructDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConstructDetail record);

    int updateByPrimaryKey(ConstructDetail record);

    void insertByBatch(@Param("constructDetailList") List<ConstructDetail> constructDetailList);

    List<ConstructDetail> selectByOrderID(Long id);

    int getConstructDetailNumByOrderId(Long order_id);

    void deleteByOrderId(Long id);
}