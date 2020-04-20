package com.dgpalife.resourcemanagement.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.OrderService;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/resource/index";
    }


    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        @RequestParam(value = "resource_no",required = false) String resource_no,
                        @RequestParam(value = "resourc_type",required = false) String resourc_type,
                        @RequestParam(value = "department_id",required = false) Long department_id,
                        @RequestParam(value = "usedepartment_id",required = false) Long usedepartment_id,
                        @RequestParam(value = "usedepartment_type",required = false) String usedepartment_type,
                        @RequestParam(value = "operator",required = false) String operator,
                        HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            Role role = (Role)session.getAttribute(Const.ROLE);


            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            //判断当前登陆的角色是否为管理员或IT岗角色,如果不是，则只能显示当前登陆用户的
//            if(role.getId()==3){
//                params.put("department_id",user.getDepartment_id());
//            }
            params.put("resource_no",resource_no);
            params.put("resourc_type",resourc_type);
            params.put("department_id",department_id);
            params.put("usedepartment_id",usedepartment_id);
            params.put("usedepartment_type",usedepartment_type);
            params.put("operator",operator);

            Page<Object> page = resourceService.queryResourceNo(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model){
        Resource resource = resourceService.queryByResourceID(id);
        Map<String,Object> portInfo = resourceService.queryPortInfoByResourceID(resource.getId());
        List<Order> orderList = orderService.queryOrderlistByResourceID(resource.getId());
        model.addAttribute("resource",resource);
        model.addAttribute("portInfo",portInfo);
        model.addAttribute("orderList",orderList);
        return "/resource/detail";
    }


    @ResponseBody
    @RequestMapping("/generateResource")
    public Object generateResource(@RequestBody JSONObject jsonObject, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            List<Resource> resourceList = jsonObject.getJSONArray("resourceDetailList").toJavaList(Resource.class);
            List<Equipment> equipmentList = (List<Equipment>)session.getAttribute("equipmentList");
            Long order_id = jsonObject.getLong("order_id");

            //判断获取的数据是否为空
            if(resourceList.isEmpty()){
                result.setMessage("未添加资源号码，请重新输入");
                result.setSuccess(false);
            }

            if(equipmentList.isEmpty()){
                result.setMessage("未添加设备，请重新输入");
                result.setSuccess(false);
            }

            //生成资源


            //

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("生成资源异常，请联系管理员解决");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/verify_resourceNo")
    public Object verify_resourceNo(String resource_no) throws Exception{
        AjaxResult result = new AjaxResult();

        int count = resourceService.queryByResourceNo(resource_no);

        if(count>0){
            result.setSuccess(true);
        }

        return result;
    }
}
