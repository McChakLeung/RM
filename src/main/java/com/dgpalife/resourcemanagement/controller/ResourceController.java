package com.dgpalife.resourcemanagement.controller;

import com.alibaba.fastjson.JSON;
import com.dgpalife.resourcemanagement.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    public Map resourceMap;

    @Autowired
    private ResourceService resourceService;

//    @Autowired
//    private DepartmentService departmentService;

//    @RequestMapping("/selectResource")
//    public String selectResource(Model model){
//        resourceMap = resourceService.selectAllResource();
//        System.out.println(resourceMap);
//        model.addAttribute("resourceMap",resourceMap);
//        return "/resource/basic";
//    }
//
//    /**
//     * 使用ajax异步请求查询子部门信息
//     * @param parentDeptNo 父部门编号
//     * @return
//     */
//    @RequestMapping("/selectSubDept/{parentDeptNo}")
//    public @ResponseBody String selectSubDept(@PathVariable String parentDeptNo){
//        List<Department> departmentList = resourceService.selectSubDept(parentDeptNo);
//        String jsonString = JSON.toJSONString(departmentList);
//        return jsonString;
//    }
//
//    /**
//     * 根据传入的条件进行筛选资源数据
//     * @param optionMap 筛选条件集合，从前台form表单中获取
//     * @param model 将结果集传会前台
//     * @return
//     */
//
//    @RequestMapping("/selectByOption")
//    public String selectByOption(@RequestParam Map<String,Object> optionMap, Model model){
//        System.out.println(optionMap);
//        resourceMap = resourceService.selectByOption(optionMap);
//        model.addAttribute("resourceMap", resourceMap);
//        System.out.println(resourceMap);
//        return "/resource/basic";
//    }


}
