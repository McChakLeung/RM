package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Template;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);
}