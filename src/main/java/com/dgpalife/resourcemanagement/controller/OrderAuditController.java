package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audit/orders")
public class OrderAuditController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/audit/orders/index";
    }
}
