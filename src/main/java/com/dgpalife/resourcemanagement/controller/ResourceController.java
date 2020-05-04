package com.dgpalife.resourcemanagement.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.EquipmentService;
import com.dgpalife.resourcemanagement.service.OrderService;
import com.dgpalife.resourcemanagement.service.Order_resourceService;
import com.dgpalife.resourcemanagement.service.ResourceService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private Order_resourceService order_resourceService;

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
                        @RequestParam(value = "operator",required = false) String operator){

        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
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
        List<Map<String,Object>> orderList = (List<Map<String,Object>>)order_resourceService.queryOrderlistByResourceID(resource.getId());
        model.addAttribute("resource",resource);
        model.addAttribute("portInfo",portInfo);
        model.addAttribute("orderList",orderList);
        return "/resource/detail";
    }

    /**
     * 建设工单转换成资源
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/generateResource")
    public Object generateResource(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            List<Resource> resourceList = (List<Resource>) session.getAttribute("resourceList");
            List<Equipment> equipmentList = (List<Equipment>)session.getAttribute("equipmentList");
            Order order = (Order)session.getAttribute("order");

            //判断获取的数据是否为空
            if(resourceList.isEmpty()){
                result.setMessage("未添加资源号码，请重新输入");
                result.setSuccess(false);
            }

//            if(equipmentList.isEmpty()){
//                result.setMessage("未添加设备，请重新输入");
//                result.setSuccess(false);
//            }

            //生成资源

            //第一步：在资源表中添加资源记录
            resourceService.insertResourceList(resourceList,order,user);

            //第二步：在设备表中添加设备记录
            if(equipmentList != null) {
                equipmentService.insertEquipmentList(equipmentList,user);
            }

            //第三步：更新order状态为已完成
            order.setStatus("已完成");
            order.setFinish(true);
            orderService.updateOrder(order);

            result.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("生成资源异常，请联系管理员解决");
        }finally {
            //释放session空间，否则占用服务器资源
            session.removeAttribute("resourceList");
            session.removeAttribute("equipmentList");
            session.removeAttribute("order");
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


    /**
     * 搜索查询
     * @param queryText
     * @return
     */
    @ResponseBody
    @RequestMapping("/showTable")
    public Object showTable(String queryText){

        LayuiVO layuiVO = new LayuiVO();

        try {
            Map<String,Object> params = new HashMap<>();
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            List<Object> resourceList = resourceService.selectResourceByQueryText(params);
            int count = resourceService.selectCount(params);
            layuiVO.setData(resourceList);
            layuiVO.setCount(count);
            layuiVO.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            layuiVO.setCode(1);
            layuiVO.setMsg("查询异常，请联系管理员处理");
        }

        return layuiVO;

    }

    @ResponseBody
    @RequestMapping("/queryResoureDetailByID")
    public Object queryResoureDetailByID(@RequestParam(value = "resource_id",required = false) Long resource_id){
        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> resource = resourceService.queryResoureDetailByID(resource_id);
            result.setDatas(resource);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 拆除资源
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/removeResource")
    public Object removeResource(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            List<ResourceRemovement> resourceRemovementList = (List<ResourceRemovement>) session.getAttribute("resourceRemovementList");
            Order order = (Order)session.getAttribute("order");

            //判断获取的数据是否为空
            if(resourceRemovementList.isEmpty()){
                result.setMessage("未添加资源号码，请重新输入");
                result.setSuccess(false);
            }

            //拆除资源
            //第一步：在资源表中添加资源记录
            resourceService.deleteResourceList(resourceRemovementList);

            //第二步：更新order状态为已完成
            order.setStatus("已完成");
            order.setFinish(true);
            orderService.updateOrder(order);

            result.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("生成资源异常，请联系管理员解决");
        }finally {
            //释放session空间，否则占用服务器资源
            session.removeAttribute("resourceRemovementList");
            session.removeAttribute("order");
        }
        return result;

    }
}
