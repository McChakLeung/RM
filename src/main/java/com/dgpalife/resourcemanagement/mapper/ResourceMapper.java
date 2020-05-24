package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    Long insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    int queryByResourceNo(String resource_no);

    List<Map<String,Object>> queryResourceNo(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    Map<String,Object> queryPortInfoByResourceID(Long id);

    List<Object> selectResourceByQueryText(Map<String, Object> params);

    int selectSearchResult(Map<String, Object> params);

    //Resource queryResoureDetailByID(Long resource_id);

    void updateResource(Long resourceId);
}