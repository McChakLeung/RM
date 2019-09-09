package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/construction/toConstructionAdd")
    public String toConstructionAdd(){
        return "/order/construction/add";
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
