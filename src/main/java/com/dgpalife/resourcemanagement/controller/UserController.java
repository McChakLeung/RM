package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.*;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;
import com.dgpalife.resourcemanagement.service.RoleService;
import com.dgpalife.resourcemanagement.service.UserRoleService;
import com.dgpalife.resourcemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/auth/user/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/auth/user/add";
    }

    /**
     * 接收update前台的数据，根据id查询user，并通过map传回到前端
     * @param id 用户id
     * @param model  返回前台数据
     * @return
     */
    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        User user = userService.selectUserByID(id);
        model.addAttribute("user",user);
        return "/auth/user/update";
    }

    /**
     * 以异步的方式查询用户页面数据，并将结果集放在一个自定义的page类中，该类封装成分页类
     * @param pageno 查询的当前页
     * @param pagesize  查询的每页包含的记录
     * @return 返回查询页面
     */
    @ResponseBody
    @RequestMapping("/index")
    public Object toIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                          @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                          String queryText){

        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            Page<User> page = userService.selectUserList(params);
            if(page == null){
                result.setSuccess(false);
                result.setMessage("无相关记录");
                return result;
            }
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行添加
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.saveUser(user);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("保存异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行更新
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.updateUser(user);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 单条记录删除
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Long id){

        AjaxResult result = new AjaxResult();

        try{
            userService.deleteUserById(id);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 跳转页面并查询相关权限信息
     * @param id
     * @return
     */
    @RequestMapping("/assignRole/{id}")
    public String assignRole(@PathVariable Long id,Model model){
        //查询所有角色
        List<Role> roleList = roleService.queryAllRole();
        //查询当前用户所拥有的角色
        List<Integer> ids = userRoleService.queryRoleByUserId(id);
        //创建两个集合，分别存放已分配角色和未分配角色
        List<Role> unAssignList = new ArrayList(); //未分配角色集合
        List<Role> assignList = new ArrayList();  //已分配角色集合
        //循环遍历
        for(Role role:roleList){
            //判断当前遍历出来的元素id是否包含在ids中，
            // 如果包含，则将其放在assignList，
            // 反之放在unassignList
            if(ids.contains(role.getId())){
                assignList.add(role);
            }else{
                unAssignList.add(role);
            }
        }
        //将两个值存放在map中
        model.addAttribute("assignList",assignList);
        model.addAttribute("unAssignList",unAssignList);
        return "/auth/user/assignRole";
    }

    @ResponseBody
    @RequestMapping("/doAssignRole")
    public Object doAssignRole(@RequestBody List<UserRole> userRoleList){
        AjaxResult result = new AjaxResult();
        try{
            userService.saveUserRoleByBatch(userRoleList);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("分配角色错误，请联系管理员");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doUnAssignRole")
    public Object doUnAssignRole(@RequestBody List<UserRole> userRoleList){
        AjaxResult result = new AjaxResult();
        try{
            userService.deleteUserRoleByBatch(userRoleList);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("分配角色错误，重新分配");
            e.printStackTrace();
        }
        return result;
    }

}
