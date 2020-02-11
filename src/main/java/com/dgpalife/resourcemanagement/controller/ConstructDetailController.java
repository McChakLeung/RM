package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.service.ConstructDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/constructDetail")
public class ConstructDetailController {

    @Autowired
    private ConstructDetailService constructDetailService;

//    @ResponseBody
//    @RequestMapping("/getConstructDetailNum/{order_id}")
//    public Object getConstructDetailNum(@PathVariable Long order_id){
//        AjaxResult result = new AjaxResult();
//        try {
//
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("查询异常异常，请联系管理员解决");
//        }
//        return result;
//    }
}
