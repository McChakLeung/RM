package com.dgpalife.resourcemanagement.controller;

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

    private Map resultMap = new HashMap();

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toIndex")
    public String toIndex(Model model, HttpSession session){
//        String creatorName = (String)session.getAttribute("userName");
//        resultMap = orderService.selectOrdersByCreatorName(creatorName);
//        model.addAttribute("resultMap",resultMap);
        return "/orders/index";
    }

    /**
     * 筛选查询
     *
     * @return  String
     */
    @PostMapping("/selectByOption")
    public String selectByOption(@RequestParam Map<String,Object> optionMap,Model model, HttpSession session){
        String creatorName = (String)session.getAttribute("userName");
        optionMap.put("creatorName",creatorName);
        resultMap = orderService.selectOrdersByOptionAndCreatorName(optionMap);
        model.addAttribute("resultMap",resultMap);

        return "/orders/orderlist";
    }

    @RequestMapping("/toCreateOrder")
    public String toCreateOrder(){

        return "/orders/createOrder";
    }

    @RequestMapping("/createOrder")
    public String CreateOrder(Orders orders){
        //orders.setStatus("待提交");
        orderService.insertOrder(orders);
        return "redirect:/orders/selectOrders";
    }

//    @RequestMapping("/selectVerifyOrders")
//    public String selectVerifyOrders(Model model, HttpSession session){
//        String creatorName = (String)session.getAttribute("userName");
//        resultMap = orderService.selectVerifyOrdersNotIncludeOption(creatorName);
//        model.addAttribute("resultMap",resultMap);
//        return "/orders/verifyorderlist";
//    }

//    @PostMapping("/selectVerifyOrdersByOption")
//    public String selectVerifyOrdersByOption(@RequestParam Map<String,Object> optionMap,Model model, HttpSession session){
//        String creatorName = (String)session.getAttribute("userName");
//        optionMap.put("creatorName",creatorName);
//        model.addAttribute("resultMap",orderService.selectVerifyOrdersByOption(optionMap));
//        return "/orders/verifyorderlist";
//    }

    @RequestMapping("/toUpdateOrder/{orderId}")
    public String toUpdateOrder(@PathVariable Long orderId,Model model){
        resultMap = orderService.selectOrderByID(orderId);
        model.addAttribute("resultMap",resultMap);
        return "/orders/updateOrder";
    }

    @RequestMapping("/verifyOrder/{orderId}")
    public String verifyOrder(@PathVariable Long orderId,Model model){
//        resultMap = orderService.selectOrderByID(orderId);
//        model.addAttribute("orders",resultMap);
        return "/orders/verifyorder";
    }

}
