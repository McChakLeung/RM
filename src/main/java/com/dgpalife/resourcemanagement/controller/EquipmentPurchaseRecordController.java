package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.service.EquipmentPurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/equipmnetPurchaseRecord")
public class EquipmentPurchaseRecordController {

    @Autowired
    private EquipmentPurchaseRecordService equipmentPurchaseRecordService;

//    @ResponseBody
//    @RequestMapping("/getPurchaseNum/{order_id}")
//    public Object getPurchaseNum(@PathVariable Long order_id){
//        AjaxResult result = new AjaxResult();
//        try {
//            int count = equipmentPurchaseRecordService.getPurchaseNumByOrderId(order_id);
//            result.setData(count);
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("查询异常异常，请联系管理员解决");
//        }
//        return result;
//    }
}
