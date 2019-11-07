package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.Expense;
import com.dgpalife.resourcemanagement.model.Project;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.ExpenseService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/setting/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/setting/expense/index";
    }

    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){

        AjaxResult result = new AjaxResult();

        try{

            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);

            Page<Object> page = expenseService.selectExpenseList(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/setting/expense/add";
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Expense expense, HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User) session.getAttribute(Const.LOGIN_USER);
            expense.setCreatorId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            expense.setCreateTime(sdf.format(date));
            int count = expenseService.saveExpense(expense);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("创建交费方式异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){
        Expense expense = expenseService.selectExpenseByID(id);
        model.addAttribute("expense",expense);
        return "/setting/expense/update";
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Expense expense){

        AjaxResult result = new AjaxResult();

        try{
            int count = expenseService.updateExpense(expense);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新交费方式异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Long id){

        AjaxResult result = new AjaxResult();

        try{
            expenseService.deleteExpenseByID(id);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新交费方式异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/showExpenseTable")
    public Object showExpenseTable(){

        LayuiVO layuiVO = new LayuiVO();

        try {
//            Map<String,Object> params = new HashMap<>();
//            if(StringUtil.isNotEmpty(queryText)){
//                if(queryText.contains("%")){
//                    queryText = queryText.replaceAll("%", "\\\\%");
//                }
//                params.put("queryText", queryText); //   \%
//            }
            List<Object> expenseList = expenseService.selectExpense();
            int count = expenseService.selectCount();
            layuiVO.setData(expenseList);
            layuiVO.setCount(count);
            layuiVO.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            layuiVO.setCode(1);
            layuiVO.setMsg("查询异常，请联系管理员处理");
        }

        return layuiVO;

    }
}
