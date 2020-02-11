package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @ResponseBody
    @RequestMapping("/verify_equipmentSn")
    public Object verify_resourceNo(String equipmentSn) throws Exception{
        AjaxResult result = new AjaxResult();

        int count = equipmentService.queryByEquipmentSn(equipmentSn);

        if(count>0){
            result.setSuccess(true);
        }

        return result;
    }
}
