package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.ConstructDetailService;
import com.dgpalife.resourcemanagement.service.DepartmentService;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.activiti.engine.ProcessEngine;
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
@RequestMapping("/order/myorder")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ConstructDetailService constructDetailService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProcessEngine processEngine;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/order/myorder/index";
    }


    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        @RequestParam(value = "order_type",required = false) String order_type,
                        @RequestParam(value = "order_staus",required = false) String order_staus,
                        @RequestParam(value = "apply_department_id",required = false) Integer apply_department_id,
//                        @RequestParam(value = "apply_usedepartment_id",required = false) Integer apply_usedepartment_id,
                        @RequestParam(value = "project_id",required = false) Integer project_id,
                        HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            Role role = (Role)session.getAttribute(Const.ROLE);


            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            //判断当前登陆的角色是否为管理员或IT岗角色,如果不是，则只能显示当前登陆用户的订单
            if(role.getId()==3){
                params.put("proposer_id",user.getId());
            }
            params.put("order_type",order_type);
            params.put("order_staus",order_staus);
            params.put("apply_department_id",apply_department_id);
//            params.put("apply_usedepartment_id",apply_usedepartment_id);
            params.put("project_id",project_id);

            Page<Object> page = orderService.selectOrderListByUserId(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toSelectOrderType")
    public String toSelectOrderType(){

        return "/order/myorder/orderType";
    }

    @RequestMapping("/dispatchCreateOrderPage/{order_type}")
    public String dispatchCreateOrderPage(@PathVariable String order_type){
//        if(StringUtil.isEmpty(order_type)){
//            return "redirect:/order/myorder/toSelectOrderType";
//        }
        switch (order_type){
            case "construction_order":
                return "redirect:/order/myorder/construction/toOrderInfo";
            case "migration_order":
                return "/order/myorder/maintaining";

        }
        return "redirect:/order/myorder/toSelectOrderType";
    }

    /**
     * 跳转至建设工单基本信息页面
     * @return
     */
    @RequestMapping("/construction/toOrderInfo")
    public String toOrderInfo(){
        return "/order/myorder/construction/orderInfo";
    }

    /**
     * 将建设工单临时放置在session中存放
     * @param order
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/construction/saveTemporaryConstructionOrder")
    public Object saveTemporaryConstructionOrder(@RequestBody Order order, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("order",order);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/construction/toConstructionDetail")
    public String toConstructionDetail(){
        return "/order/myorder/construction/orderDetail";
    }

    /**
     * 将建设工单明细的List集合临时放置在session中存放
     * @param constructDetailList
     * @return
     */
    @ResponseBody
    @RequestMapping("/construction/saveTemporaryConstructionDetailList")
    public Object saveTemporaryConstructionDetailList(@RequestBody List<ConstructDetail> constructDetailList, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("constructDetailList",constructDetailList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/construction/toPreviewOrder")
    public String toPreviewOrder(){
        return "/order/myorder/construction/previewOrder";
    }

    @ResponseBody
    @RequestMapping("/construction/doAddConstructionOrder")
    public Object doAddConstructionOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ConstructDetail> constructDetailList = (List<ConstructDetail>)session.getAttribute("constructDetailList");
            if(order == null){
                result.setSuccess(false);
                result.setMessage("暂未填写工单基本信息，请重新填写");
                return result;
            }
            if(constructDetailList==null){
                result.setSuccess(false);
                result.setMessage("暂未填写工单明细信息，请重新填写");
                return result;
            }

            //设置order的其他信息
            order.setStatus("待提交");
            order.setProposerId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(sdf.format(date));

            orderService.saveOrder(order);

            for(ConstructDetail constructDetail: constructDetailList){
                constructDetail.setCreateTime(sdf.format(date));
                constructDetail.setCreatorId(user.getId());
                constructDetail.setOrderId(order.getId());
            }

            //发送给后台处理
            constructDetailService.saveConstructDetailBatch(constructDetailList);

            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("constructDetailList");

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){
        Order order = orderService.selectOrderById(id);
        model.addAttribute("order",order);
        return "/order/myorder/detail";
    }


    @ResponseBody
    @RequestMapping("/doAuditOrder/{id}")
    public Object doAuditOrder(@PathVariable Long id){
        AjaxResult result = new AjaxResult();
        try {
            Order order = orderService.selectOrderById(id);
            order.setStatus("待审核");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("提交审核异常，请联系管理员解决");
        }
        return result;
    }

//    @RequestMapping("/construction/toConstructionAdd")
//    public String toConstructionAdd(){
//        return "/order/myorder/construction/add";
//    }
//
//    @ResponseBody
//    @RequestMapping("/construction/doConstructionAdd")
//    public Object createOrder(@RequestBody Order order){
//        AjaxResult result = new AjaxResult();
//        try{
//            Integer count = orderService.createOrder(order);
//            result.setSuccess(count>0);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("创建工单失败");
//        }
//
//        return result;
//    }
//
//    @RequestMapping("/remove/toRemoveOrderAdd")
//    public String toRemoveOrderAdd(){
//        return "/order/remove/add";
//    }
//
//    @RequestMapping("/maintaining/toMaintainingAdd")
//    public String toMaintainingAdd(){
//        return "/order/maintaining/add";
//    }
//
//    @RequestMapping("/migration/toMigrationAdd")
//    public String toMigrationAdd(){
//        return "/order/migration/add";
//    }



}
