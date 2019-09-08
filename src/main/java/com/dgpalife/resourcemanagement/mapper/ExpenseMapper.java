package com.dgpalife.resourcemanagement.mapper;

import com.dgpalife.resourcemanagement.model.Expense;

public interface ExpenseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expense record);

    int insertSelective(Expense record);

    Expense selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Expense record);

    int updateByPrimaryKey(Expense record);
}