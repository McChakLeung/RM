package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/order/index";
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
                          HttpSession session){

        AjaxResult result = new AjaxResult();

        //User user = (User)session.getAttribute(Const.LOGIN_USER);

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            params.put("user",session.getAttribute(Const.LOGIN_USER));

            Page<Order> page = orderService.selectOrderListByUserID(params);
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

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){
        Order order = orderService.selectOrderByID(id);
        model.addAttribute("order",order);
        return "/order/update";
    }

//    /**
//     * 筛选查询
//     *
//     * @return  String
//     */
//    @PostMapping("/selectByOption")
//    public String selectByOption(@RequestParam Map<String,Object> optionMap,Model model, HttpSession session){
//        String creatorName = (String)session.getAttribute("userName");
//        optionMap.put("creatorName",creatorName);
//        resultMap = orderService.selectOrdersByOptionAndCreatorName(optionMap);
//        model.addAttribute("resultMap",resultMap);
//
//        return "/orders/orderlist";
//    }

//    @RequestMapping("/toCreateOrder")
//    public String toCreateOrder(){
//
//        return "/orders/createOrder";
//    }
//
//    @RequestMapping("/createOrder")
//    public String CreateOrder(Order order){
//        //orders.setStatus("待提交");
//        //orderService.insertOrder(orders);
//        return "redirect:/orders/selectOrders";
//    }
//
////    @RequestMapping("/selectVerifyOrders")
////    public String selectVerifyOrders(Model model, HttpSession session){
////        String creatorName = (String)session.getAttribute("userName");
////        resultMap = orderService.selectVerifyOrdersNotIncludeOption(creatorName);
////        model.addAttribute("resultMap",resultMap);
////        return "/orders/verifyorderlist";
////    }
//
////    @PostMapping("/selectVerifyOrdersByOption")
////    public String selectVerifyOrdersByOption(@RequestParam Map<String,Object> optionMap,Model model, HttpSession session){
////        String creatorName = (String)session.getAttribute("userName");
////        optionMap.put("creatorName",creatorName);
////        model.addAttribute("resultMap",orderService.selectVerifyOrdersByOption(optionMap));
////        return "/orders/verifyorderlist";
////    }
//
//    @RequestMapping("/toUpdateOrder/{orderId}")
//    public String toUpdateOrder(@PathVariable Long orderId,Model model){
//        resultMap = orderService.selectOrderByID(orderId);
//        model.addAttribute("resultMap",resultMap);
//        return "/orders/updateOrder";
//    }
//
//    @RequestMapping("/verifyOrder/{orderId}")
//    public String verifyOrder(@PathVariable Long orderId,Model model){
////        resultMap = orderService.selectOrderByID(orderId);
////        model.addAttribute("orders",resultMap);
//        return "/orders/verifyorder";
//    }

}
