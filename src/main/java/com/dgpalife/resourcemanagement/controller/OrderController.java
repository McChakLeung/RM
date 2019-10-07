package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/construction/toConstructionAdd")
    public String toConstructionAdd(){
        return "/order/construction/add";
    }

    @ResponseBody
    @RequestMapping("/construction/doConstructionAdd")
    public Object createOrder(@RequestBody Order order){
        AjaxResult result = new AjaxResult();
        try{
            Integer count = orderService.createOrder(order);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单失败");
        }

        return result;
    }

    @RequestMapping("/remove/toRemoveOrderAdd")
    public String toRemoveOrderAdd(){
        return "/order/remove/add";
    }

    @RequestMapping("/maintaining/toMaintainingAdd")
    public String toMaintainingAdd(){
        return "/order/maintaining/add";
    }

    @RequestMapping("/migration/toMigrationAdd")
    public String toMigrationAdd(){
        return "/order/migration/add";
    }



}
