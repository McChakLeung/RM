package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.service.ItemService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping("/setting/showTable/{change_type}")
    public Object showTable(@PathVariable String change_type, String queryText){

        LayuiVO layuiVO = new LayuiVO();

        try {
            Map<String,Object> params = new HashMap<>();
            params.put("change_type",change_type);
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            List<Object> itemList = itemService.selectItemByQueryText(params);
            int count = itemService.selectCountByQueryText(params);
            layuiVO.setData(itemList);
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
