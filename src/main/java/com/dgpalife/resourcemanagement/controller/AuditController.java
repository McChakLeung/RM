package com.dgpalife.resourcemanagement.controller;

import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.model.Order;
import com.dgpalife.resourcemanagement.model.Ticket;
import com.dgpalife.resourcemanagement.model.User;
import com.dgpalife.resourcemanagement.service.*;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private TicketService ticketService;


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
            //建立查询请求
            TaskQuery query = processEngine.getTaskService().createTaskQuery();
            //根据流程定义文件查询分配给admin的任务列表
            List<Task> taskList = query.processDefinitionKey("order_auth").taskCandidateGroup("admin").listPage((pageno-1)*pagesize, pagesize);

            List<Map<String, Object>> taskMapList = new ArrayList<Map<String, Object>>();//避免JSON数据转换出错

            //遍历任务，并将任务信息存放在taskMap中展示到前端
            for(Task task:taskList){
                Map<String, Object> taskMap = new HashMap<String, Object>();
                taskMap.put("id", task.getId());
//                taskMap.put("name", task.getName());

                //通过任务表的流程定义id查询流程定义

                ProcessDefinition pd = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
                taskMap.put("procDefName", pd.getName());
//                taskMap.put("procDefVersion", pd.getVersion());

                //通过流程查找待审批的工单
                Ticket ticket = ticketService.queryTicketByPiid(task.getProcessInstanceId());
                Order order = orderService.selectOrderById(ticket.getOrderId());
                User user = userService.selectUserByID(order.getProposerId());
                taskMap.put("order_id",order.getId());
                taskMap.put("piid",task.getProcessInstanceId());
                taskMap.put("type",order.getType());
                taskMap.put("status",order.getStatus());
                taskMap.put("title",order.getTitle());
                taskMap.put("create_time",order.getCreateTime());
                taskMap.put("proposer",user.getUsername());
                taskMapList.add(taskMap);

            }
            // 获取数据的总条数

            int count = (int)query.count(); //同一个query 对象,查询条件是一样的
            Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageno,pagesize);
            page.setDatalist(taskMapList);
            page.setTotalsize(count );
            result.setPage(page);
            result.setSuccess(true);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员");
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/orders/toAudit/{id}/{piid}")
    public String toAudit(@PathVariable Long id, @PathVariable String piid, Model model){

        Order order = orderService.selectOrderById(id);
        //Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(order.getPiid()).singleResult();
        Ticket ticket = ticketService.queryTicketByPiid(piid);
        model.addAttribute("ticket",ticket);
        model.addAttribute("order",order);

        if(Const.CONSTRUCTION.equals(order.getType())){
            return "/audit/orders/detail/construction_detail";
        }else if (Const.MIGRATION.equals(order.getType())){
            return "/audit/orders/detail/migration_detail";
        }else if(Const.REMOVEMENT.equals(order.getType())){
            return "/audit/orders/detail/removement_detail";
        }else if(Const.CHANGE_ITEM.equals(order.getType())){
            return "/audit/orders/detail/change_item_detail";
        }

        return "/error";
    }

    @ResponseBody
    @RequestMapping("/orders/agree")
    public Object agree(Long order_id, String piid, String comment){

        AjaxResult result = new AjaxResult();

        try{
            Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(piid).singleResult();
            processEngine.getTaskService().setVariable(task.getId(), "flag", true);
            processEngine.getTaskService().setVariable(task.getId(), "order_id", order_id);
            processEngine.getTaskService().setVariable(task.getId(), "comment", comment);
            processEngine.getTaskService().setVariable(task.getId(), "piid", piid);
            processEngine.getTaskService().complete(task.getId());
            result.setSuccess(true);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("审核异常，请联系管理员");
            e.printStackTrace();
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/orders/refuse")
    public Object refuse(Long order_id, String piid, String comment){

        AjaxResult result = new AjaxResult();

        try{
            Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(piid).singleResult();
            processEngine.getTaskService().setVariable(task.getId(), "flag", false);
            processEngine.getTaskService().setVariable(task.getId(), "order_id", order_id);
            processEngine.getTaskService().setVariable(task.getId(), "comment", comment);
            processEngine.getTaskService().setVariable(task.getId(), "piid", piid);
            processEngine.getTaskService().complete(task.getId());
            result.setSuccess(true);

        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("审核异常，请联系管理员");
            e.printStackTrace();
        }

        return result;
    }

}
