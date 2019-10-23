package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Page;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.transformer.LongToInteger;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/setting/process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/setting/process/index";
    }

    /**
     * 通过异步请求方式查询流程定义数据
     * @param pageno
     * @param pagesize
     * @return
     */
    @ResponseBody
    @RequestMapping("/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){

        AjaxResult result = new AjaxResult();

        try{
            //定义page对象

            //通过repositoryService查询所有流程定义集合
            List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().listPage((pageno-1)*pagesize, pagesize);
            //对象之间的相互依赖导致json数据结构出错，需要使用以下集合重新接收
            List<Map<String,Object>> dataList = new ArrayList<>();

            for(ProcessDefinition processDefinition:processDefinitionList){
                Map<String,Object> pd = new HashMap<>();
                pd.put("id",processDefinition.getId());
                pd.put("key",processDefinition.getKey());
                pd.put("version",processDefinition.getVersion());
                pd.put("name",processDefinition.getName());
                dataList.add(pd);
            }
            //获取流程定义的总数，作为属性用于分页类Page
            Long totalsize = repositoryService.createProcessDefinitionQuery().count();
            //定义和设置page对象的属性
            Page<Map<String, Object>>  page = new Page<Map<String, Object>> (pageno,pagesize);
            page.setDatalist(dataList);
            page.setTotalsize(totalsize.intValue());
            if(page == null){
                result.setSuccess(false);
                result.setMessage("无相关记录");
                return result;
            }
            //将属性设置到result对象中
            result.setSuccess(true);
            result.setPage(page);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
        }
        return result;
    }


    /**
     * 通过异步请求方式删除流程定义数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(String id){

        AjaxResult result = new AjaxResult();

        try{
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
            //根据流程定义的外键deploymentid来删除流程定义，true表示级联删除
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);

            //将属性设置到result对象中
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
        }
        return result;
    }
}
