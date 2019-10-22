package com.dgpalife.resourcemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setting/process")
public class ProcessController {

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/setting/process/index";
    }
}
