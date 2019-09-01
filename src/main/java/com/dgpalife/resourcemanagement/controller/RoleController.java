package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.service.PermissionService;
import com.dgpalife.resourcemanagement.service.RoleService;
import com.dgpalife.resourcemanagement.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/auth/role/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/auth/role/add";
    }

    @RequestMapping("/toUpdate/{roleId}")
    public String toUpdate(@PathVariable Long roleId, Model model){

        Role role = roleService.queryRoleByRoleId(roleId);
        model.addAttribute("role",role);
        return "/auth/role/update";
    }


    /**
     * 跳转至分配权限界面
     * @return
     */
    @RequestMapping("/toAssignPermission/{roleId}")
    public String toAssignPermission(@PathVariable Integer roleId, Model model){
        model.addAttribute("roleId",roleId);
        return "/auth/role/assignPermission";
    }

    @ResponseBody
    @RequestMapping("/queryPage")
    public Object queryPage(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                            @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){
        AjaxResult result = new AjaxResult();
        try{
            //定义一个map集合存放传入的参数
            Map<String,Object> params = new HashMap();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            Page<Role> page = roleService.queryRoleListByParams(params);

            //判断返回的集合是否为空，如果为null就没有必要在前端拼接数据
            if(page.getDatalist() == null){
                result.setMessage("无查询结果");
                result.setSuccess(false);
                return result;
            }
            //将page数据封装到result中
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("查询失败，请联系管理员处理");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/asyncLoadData")
    public Object asyncLoadData(Integer roleId){

        AjaxResult result = new AjaxResult();

        try{

            //一次性从数据库中查询所有的数据
            List<Permission> permissionList = permissionService.selectAllPermission();

            //从数据库中查询roleId对应的Permission
            List<Permission> permissionByRoleID = roleService.queryPermissionByRoleID(roleId);

            //设置父节点list
            List<Permission> root = new ArrayList();

            //创建一个map集合
            Map<Integer,Permission> map = new HashMap<>();

            //提前遍历内层循环
            for(Permission innerPermission : permissionList){
                if(permissionByRoleID.contains(innerPermission.getId())){
                    innerPermission.setChecked(true);
                }
                map.put(innerPermission.getId(),innerPermission);
            }

            //循环遍历
            for(Permission permission : permissionList){
                //判断当前对象是否为根节点
                if(permission.getPid() == null){
                    root.add(permission);
                }else{
                    Permission parent = map.get(permission.getPid());
                    parent.getChildren().add(permission);
                }
            }

            result.setDatas(root);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("加载许可树失败");
        }
        return result;
    }

    /**
     * 分配许可
     * @param roleId
     * @param data 通过VO的方式传递数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAssignPermission")
    public Object doAssignPermission(Long roleId, Data data){
        AjaxResult result = new AjaxResult();
        try{
            Integer count = roleService.processAssignPermission(roleId,data.getIds());
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("加载许可树失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Role role){
        AjaxResult result = new AjaxResult();
        try{
            Integer count = roleService.saveRole(role);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("添加角色失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Role role){
        AjaxResult result = new AjaxResult();
        try{
            Integer count = roleService.updateRole(role);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("修改角色失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Long id){
        AjaxResult result = new AjaxResult();
        try{
            roleService.deleteRoleByKey(id);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("修改角色失败");
        }
        return result;
    }



}
