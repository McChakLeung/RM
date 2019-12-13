package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/audit")
public class AuditController {

    @RequestMapping("/orders/toIndex")
    public String toOrderIndex(){
        return "/audit/orders/index";
    }

    @ResponseBody
    @RequestMapping("/orders/pageQuery")
    public Object pageQuery(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
                            @RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize){

        AjaxResult result = new AjaxResult();

        return result;
    }
}
