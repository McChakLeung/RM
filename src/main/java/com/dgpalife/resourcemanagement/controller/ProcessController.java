package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Page;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.transformer.LongToInteger;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.ui.Model;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 通过异步请求方式上传流程定义文件
     * @return
     */
    @ResponseBody
    @RequestMapping("/doUpload")
    public Object doUpload(MultipartFile attachFile){

        AjaxResult result = new AjaxResult();

        try{
            //从请求域对象中，通过input的name属性获取文件
            //MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //MultipartFile attachFile = multipartHttpServletRequest.getFile("attachFile");

            // 部署流程定义文件
            repositoryService.createDeployment().addInputStream(attachFile.getOriginalFilename(), attachFile.getInputStream()).deploy();

            //将属性设置到result对象中
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("上传异常，请联系管理员处理");
        }
        return result;
    }

    @RequestMapping("/toShowImg/{id}")
    public String toShowImg(@PathVariable String id, Model model){
        model.addAttribute("id",id);
        return "/setting/process/showImg";
    }

    @ResponseBody
    @RequestMapping("/doShowImg/{id}")
    public void doShowImg(@PathVariable String id, HttpServletResponse response) throws IOException {
        //1.根据传入的id，从数据库中表act_re_procdef获取流程定义数据
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();

        //2.根据查询出的流程定义对象，获取其deploymentID和DGRM_RESOURCE_NAME_，通过这两个字段查询出act_ge_bytearray表中的BYTES_数据
        //这些数据即为流程定义的图片数据，并作为输入流对象存放在虚拟机内存中
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        ServletOutputStream outputStream = response.getOutputStream();

        //3.将数据转成为输出流,并输出给客户端
//        int i = -1;
//        while((i = inputStream.read()) != -1){
//            outputStream.write(i);
//        }

        IOUtils.copy(inputStream,outputStream);


    }

}
