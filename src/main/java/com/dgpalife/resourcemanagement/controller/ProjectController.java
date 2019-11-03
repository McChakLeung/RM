package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.common.StringUtil;
import com.dgpalife.resourcemanagement.model.Project;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.OrderService;
import com.dgpalife.resourcemanagement.service.ProjectService;
import com.dgpalife.resourcemanagement.vo.LayuiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/project/index";
    }

    /**
     * 异步加载项目列表
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
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            Page<Object> page = projectService.selectProjectList(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/project/add";
    }

    /**
     * 异步创建项目
     * @param project
     * @return
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Project project, HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User) session.getAttribute(Const.LOGIN_USER);
            project.setCreatorId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            project.setCreateTime(sdf.format(date));
            int count = projectService.saveProject(project);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("创建项目异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model){
        Project project = projectService.selectProjectById(id);
        model.addAttribute("project",project);
        return "/project/update";
    }

    /**
     * 异步执行更新操作
     * @param project
     * @return
     */
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Project project){

        AjaxResult result = new AjaxResult();

        try{
            int count = projectService.updateProjectById(project);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新项目异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Long id){

        AjaxResult result = new AjaxResult();

        try{
            int count = projectService.deleteProjectById(id);
            result.setSuccess(count>0);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("更新项目异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 显示明细页面，并关联order订单信息
     * @param id
     * @return
     */
    @RequestMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model){
        //查询单个project对象
        Project project = projectService.selectProjectById(id);

        //根据project对象查询出关联的工单列表
        List<Map<String,Object>> orderList = orderService.selectOrderListByProjectId(project.getId());

        //将查询的工单列表存放在project对象中
        project.setOrderList(orderList);

        //将project对象通过model传回后台
        model.addAttribute("project",project);

        return "/project/detail";
    }


    @ResponseBody
    @RequestMapping("/showTable")
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
            List<Object> projectList = projectService.selectProjectByQueryText(params);
            int count = projectService.selectCountByQueryText(params);
            layuiVO.setData(projectList);
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
