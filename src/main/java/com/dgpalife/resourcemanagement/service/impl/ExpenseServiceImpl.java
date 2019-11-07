package com.dgpalife.resourcemanagement.service.impl;

import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.mapper.ExpenseMapper;
import com.dgpalife.resourcemanagement.model.Expense;
import com.dgpalife.resourcemanagement.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseMapper expenseMapper;

    @Override
    public Page<Object> selectExpenseList(Map<String, Object> params) {
        Page<Object> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询项目列表数据
        params.put("startline",page.getStartline());
        List<Map<String,Object>> datas = expenseMapper.selectExpenseList(params);

        page.setDatalist(datas);

        //查询项目总数
        Integer totalsize = expenseMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public int saveExpense(Expense expense) {
        return expenseMapper.insertSelective(expense);
    }

    @Override
    public Expense selectExpenseByID(Long id) {
        return expenseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateExpense(Expense expense) {
        return expenseMapper.updateByPrimaryKeySelective(expense);
    }

    @Override
    public void deleteExpenseByID(Long id) {
        expenseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Object> selectExpense() {
        return expenseMapper.selectExpense();
    }

    @Override
    public int selectCount() {
        return expenseMapper.selectNum();
    }
}
