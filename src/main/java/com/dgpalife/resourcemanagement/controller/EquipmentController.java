package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.service.EquipmentService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ResponseBody
    @RequestMapping("/setting/showTable")
    public Object showTable(String queryText){

        LayuiVO layuiVO = new LayuiVO();

        try {
            Map<String,Object> params = new HashMap<>();
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            List<Object> equipmentList = equipmentService.selectEquipmentByQueryText(params);
            int count = equipmentService.selectCountByQueryText(params);
            layuiVO.setData(equipmentList);
            layuiVO.setCount(count);
            layuiVO.setCode(0);
        }catch (Exception e){
            e.printStackTrace();
            layuiVO.setCode(1);
            layuiVO.setMsg("查询异常，请联系管理员处理");
        }

        return layuiVO;

    }
}
