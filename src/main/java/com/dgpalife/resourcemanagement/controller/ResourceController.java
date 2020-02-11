package com.dgpalife.resourcemanagement.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.model.Equipment;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Resource;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/resource/index";
    }

    @ResponseBody
    @RequestMapping("/generateResource")
    public Object generateResource(@RequestBody JSONObject jsonObject, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            List<Resource> resourceList = jsonObject.getJSONArray("resourceDetailList").toJavaList(Resource.class);
            List<Equipment> equipmentList = jsonObject.getJSONArray("equipmentList").toJavaList(Equipment.class);
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
