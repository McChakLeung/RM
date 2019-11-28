package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audit")
public class AuditController {

    @RequestMapping("/orders/toIndex")
    public String toOrderIndex(){
        return "/audit/orders/index";
    }
}
