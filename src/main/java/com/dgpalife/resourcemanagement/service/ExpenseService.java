package com.dgpalife.resourcemanagement.service;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Expense;

import java.util.List;
import java.util.Map;

public interface ExpenseService {
    Page<Object> selectExpenseList(Map<String, Object> params);

    int saveExpense(Expense expense);

    Expense selectExpenseByID(Long id);

    int updateExpense(Expense expense);

    void deleteExpenseByID(Long id);

    List<Object> selectExpense();

    int selectCount();
}
