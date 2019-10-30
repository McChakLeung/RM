package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.Department;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("setting/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/setting/department/index";
    }

    /**
     * 异步加载部门树
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){

        AjaxResult result = new AjaxResult();

        try{
            //一次性从数据库中查询所有的数据
            List<Department> departmentList = departmentService.selectAllDepartment();

            //设置父节点list
            List<Department> root = new ArrayList();

            //创建一个map集合
            Map<Long,Department> map = new HashMap<>();

            //提前遍历内层循环
            for(Department innerDepartment : departmentList){
                map.put(innerDepartment.getId(),innerDepartment);
            }

            //循环遍历

            for(Department department : departmentList){

                //判断当前对象是否为根节点
                if(department.getPid() == null){
                    root.add(department);
                }else{
                    Department parent = map.get(department.getPid());
                    parent.getChildren().add(department);
                }
            }

            result.setDatas(root);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 异步删除部门
     * @return
     */
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Long id){
        AjaxResult result = new AjaxResult();
        try{
            Long count = departmentService.deleteDepartmentById(id);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){
        Department department = departmentService.selectDepartmentById(id);
        model.addAttribute("department",department);
        return "/setting/department/update";
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Department department){
        AjaxResult result = new AjaxResult();
        try{
            int count = departmentService.updateDepartment(department);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 跳转至添加页面
     * @param pid 从前台获取父节点id
     * @param model
     * @return
     */
    @RequestMapping("/toAdd/{pid}")
    public String toAdd(@PathVariable Long pid, Model model){
        model.addAttribute("pid",pid);
        return "/setting/department/add";
    }


    /**
     * 异步添加部门
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Long pid, Department department, HttpSession session){
        AjaxResult result = new AjaxResult();
        try{
            //将当前登录的用户id和创建时间传入到department中
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            department.setPid(pid);
            department.setCreatorId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            department.setCreateTime(sdf.format(date));
            int count = departmentService.saveDepartment(department);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }
}
