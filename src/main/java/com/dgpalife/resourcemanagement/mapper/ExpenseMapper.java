package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Expense;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expense record);

    int insertSelective(Expense record);

    Expense selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Expense record);

    int updateByPrimaryKey(Expense record);

    List<Map<String,Object>> selectExpenseList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    List<Object> selectExpense();

    int selectNum();
}