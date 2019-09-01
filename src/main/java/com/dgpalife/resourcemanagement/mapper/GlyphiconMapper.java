package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Glyphicon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GlyphiconMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Glyphicon record);

    int insertSelective(Glyphicon record);

    Glyphicon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Glyphicon record);

    int updateByPrimaryKey(Glyphicon record);

    List<Glyphicon> queryAllGlyphicon();
}