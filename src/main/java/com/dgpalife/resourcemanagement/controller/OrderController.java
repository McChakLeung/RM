package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.model.Orders;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/selectOrders")
    public String selectOrders(Model model, HttpSession session){
        String creatorName = (String)session.getAttribute("userName");
        Map resultMap = orderService.selectOrdersByCreatorName(creatorName);
        model.addAttribute("resultMap",resultMap);
        return "/orders/orderlist";
    }

    /**
     * 筛选查询
     *
     * @return
     */
    @PostMapping("/selectByOption")
    public String selectByOption(@RequestParam Map<String,Object> optionMap,Model model, HttpSession session){
        System.out.println(optionMap);
        String creatorName = (String)session.getAttribute("userName");
        optionMap.put("creatorName",creatorName);
        Map resultMap = orderService.selectOrdersByOptionAndCreatorName(optionMap);
        model.addAttribute("resultMap",resultMap);

        return "/orders/orderlist";
    }

    @RequestMapping("/toCreateOrder")
    public String toCreateOrder(){

        return "/orders/createOrder";
    }

    @RequestMapping("/createOrder")
    public String CreateOrder(Orders orders){
        orders.setStatus("待提交");
        orderService.insertOrder(orders);

        return "redirect:/orders/selectOrders";
        //return  null;
    }

    @RequestMapping("/todoUpdateResource")
    public String TodoUpdateResource(Model model){
        HashMap resultMap = orderService.selectOrdersNotIncludeOption();
        System.out.println(resultMap);
        model.addAttribute("resultMap",resultMap);
        return "/orders/handelorders";
    }

    @RequestMapping("/toUpdateOrder/{orderId}")
    public String toUpdateOrder(@PathVariable Long orderId,Model model){
        HashMap<String,Object> resultMap = orderService.selectOrderByID(orderId);
        model.addAttribute("resultMap",resultMap);
        return "/orders/updateOrder";
    }

    @RequestMapping("/confirmPrehandleOrder/{orderId}")
    public String confirmPrehandleOrder(@PathVariable Long orderId,Model model){
        HashMap<String,Object> resultMap = orderService.selectOrderByID(orderId);
        model.addAttribute("orders",resultMap);
        return "/orders/prehandleOrderConfirm";
    }

}
