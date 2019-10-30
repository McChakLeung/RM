package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.model.Permission;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/auth/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/auth/permission/index";
    }

    @RequestMapping("/toAdd/{pid}")
    public String toAdd(@PathVariable Long pid, Model model){
        model.addAttribute("pid",pid);
        return "/auth/permission/add";
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model){
        Permission permission = permissionService.selectPermissionById(id);
        model.addAttribute("permission",permission);
        return "/auth/permission/update";
    }


    /**
     * map与list的区别：map的key键是可以任意指定的，可以指定对象的id值，
     * 而list集合的get()方法是按集合的先后顺序查找数据，值相对固定，所以无法使用list集合实现该功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){

        AjaxResult result = new AjaxResult();

        try{

            //一次性从数据库中查询所有的数据
            List<Permission> permissionList = permissionService.selectAllPermission();

            //设置父节点list
            List<Permission> root = new ArrayList();

            //创建一个map集合
            Map<Integer,Permission> map = new HashMap<>();

            //提前遍历内层循环
            for(Permission innerPermission : permissionList){
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

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Integer pid, Permission permission){
        AjaxResult result = new AjaxResult();

        try{
            permission.setPid(pid);
            int count = permissionService.insert(permission);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("添加许可树失败");
        }

        return result;

    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Permission permission){
        AjaxResult result = new AjaxResult();

        try{
            int count = permissionService.updateByPrimaryKeySelective(permission);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("更新许可树失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/deletePermission")
    public Object deletePermission(Integer id){
        AjaxResult result = new AjaxResult();

        try{
            int count = permissionService.deletePermission(id);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除许可树失败");
        }

        return result;

    }

}
