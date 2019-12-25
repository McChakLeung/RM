package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.model.Role;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.model.UserRole;
import com.dgpalife.resourcemanagement.service.RoleService;
import com.dgpalife.resourcemanagement.service.UserRoleService;
import com.dgpalife.resourcemanagement.service.UserService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("/orders/toIndex")
    public String toOrderIndex(){
        return "/audit/orders/index";
    }

    @ResponseBody
    @RequestMapping("/orders/pageQuery")
    public Object pageQuery(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
                            @RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize,
                            HttpSession session){

        AjaxResult result = new AjaxResult();

        try{

            Long role_id = 2L;

//            User user = (User) session.getAttribute(Const.LOGIN_USER);
//
//            UserRole userRole = (UserRole)userRoleService.queryRoleByUserIdAndRoleId(user.getId(),role_id);
//
//            Role role = roleService.queryRoleByRoleId(userRole.getRoleId());

            Role role = roleService.queryRoleByRoleId(role_id);

            TaskQuery query = processEngine.getTaskService().createTaskQuery();
            List<Task> taskList = query.processDefinitionKey("order_auth").taskCandidateGroup(role.getName()).listPage((pageno-1)*pagesize, pagesize);

            List<Map<String, Object>> taskMapList = new ArrayList<Map<String, Object>>();//避免JSON数据转换出错

            for(Task task:taskList){
                Map<String, Object> taskMap = new HashMap<String, Object>();
                taskMap.put("id", task.getId());
                taskMap.put("name", task.getName());

                //通过任务表的流程定义id查询流程定义

                ProcessDefinition pd = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
                taskMap.put("procDefName", pd.getName());
                taskMap.put("procDefVersion", pd.getVersion());

                //通过流程查找待审批的工单



                taskMapList.add(taskMap);

            }



        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员");
            e.printStackTrace();
        }

        return result;
    }
}
