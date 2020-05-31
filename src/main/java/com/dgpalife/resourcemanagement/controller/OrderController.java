package com.dgpalife.resourcemanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.dgpalife.resourcemanagement.common.AjaxResult;
import com.dgpalife.resourcemanagement.common.Const;
import com.dgpalife.resourcemanagement.common.Page;
import com.dgpalife.resourcemanagement.listener.activiti.listener.PassListener;
import com.dgpalife.resourcemanagement.listener.activiti.listener.RefuseListener;
import com.dgpalife.resourcemanagement.model.*;
import com.dgpalife.resourcemanagement.service.*;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ConstructDetailService constructDetailService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EquipmentPurchaseRecordService equipmentPurchaseRecordService;

//    @Autowired
//    private ActUserEntityService actUserEntityService;

    @Autowired
    private ResourceRemovementService resourceRemovementService;

    @Autowired
    private ResourceMigrationService resourceMigrationService;

    @RequestMapping("/myorder/toIndex")
    public String toIndex(){
        return "/order/myorder/index";
    }


    @ResponseBody
    @RequestMapping("/myorder/index")
    public Object index(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        @RequestParam(value = "order_type",required = false) String order_type,
                        @RequestParam(value = "order_staus",required = false) String order_staus,
                        @RequestParam(value = "apply_department_id",required = false) Integer apply_department_id,
//                        @RequestParam(value = "apply_usedepartment_id",required = false) Integer apply_usedepartment_id,
                        @RequestParam(value = "project_id",required = false) Integer project_id,
                        HttpSession session){

        AjaxResult result = new AjaxResult();

        try{
            User user = (User)session.getAttribute(Const.LOGIN_USER);
            Role role = (Role)session.getAttribute(Const.ROLE);


            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            //判断当前登陆的角色是否为管理员或IT岗角色,如果不是，则只能显示当前登陆用户的订单
            if(role.getId()==3){
                params.put("proposer_id",user.getId());
            }
            params.put("order_type",order_type);
            params.put("order_staus",order_staus);
            params.put("apply_department_id",apply_department_id);
//            params.put("apply_usedepartment_id",apply_usedepartment_id);
            params.put("project_id",project_id);

            Page<Object> page = orderService.selectOrderListByUserId(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/myorder/toSelectOrderType")
    public String toSelectOrderType(){

        return "/order/myorder/orderType";
    }

    @RequestMapping("/myorder/dispatchCreateOrderPage/{order_type}")
    public String dispatchCreateOrderPage(@PathVariable String order_type){
//        if(StringUtil.isEmpty(order_type)){
//            return "redirect:/order/myorder/toSelectOrderType";
//        }
        switch (order_type){
            case "construction_order":
                return "redirect:/order/myorder/construction/add/toConstructOrder";
            case "migration_order":
                return "redirect:/order/myorder/migration/add/toMigrationOrder";
            case "removement_order":
                return "redirect:/order/myorder/removement/add/toRemoveOrder";
            case "maintaining_order":
                return "redirect:/order/myorder/maintaining/add/toMaintainingOrder";
        }
        return "redirect:/order/myorder/toSelectOrderType";
    }

    /**
     * 跳转至建设工单基本信息页面
     * @return
     */
    @RequestMapping("/myorder/construction/add/toConstructOrder")
    public String toConstructOrder(){
        return "/order/myorder/construction/add/orderInfo";
    }

    /**
     * 跳转至迁移工单基本信息页面
     * @return
     */
    @RequestMapping("/myorder/migration/add/toMigrationOrder")
    public String toMigrationOrder(){
        return "/order/myorder/migration/add/orderInfo";
    }

    /**
     * 跳转至拆机工单基本信息页面
     * @return
     */
    @RequestMapping("/myorder/removement/add/toRemoveOrder")
    public String toRemoveOrder(){
        return "/order/myorder/removement/add/orderInfo";
    }


    @RequestMapping("/myorder/maintaining/add/toMaintainingOrder")
    public String toMaintainingOrder(){
        return "/order/myorder/maintaining/add/orderInfo";
    }

    /**
     * 将建设工单临时放置在session中存放
     * @param order
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/construction/saveTemporaryConstructionOrder")
    public Object saveTemporaryConstructionOrder(@RequestBody Order order, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("order",order);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    /**
     * 将建设工单临时放置在session中存放
     * @param order
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/migration/saveTemporaryMigrationOrder")
    public Object saveTemporaryMigrationOrder(@RequestBody Order order, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("order",order);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }


    /**
     * 将拆机工单临时放置在session中存放
     * @param order
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/removement/saveTemporaryRemovementOrder")
    public Object saveTemporaryRemovementOrder(@RequestBody Order order, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("order",order);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    /**
     * 将资源维护工单临时放置在session中存放
     * @param order
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/maintaining/saveTemporaryMaintainingOrder")
    public Object saveTemporaryMaintainingOrder(@RequestBody Order order, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("order",order);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/myorder/construction/add/toConstructionDetail")
    public String toConstructionDetail(){
        return "/order/myorder/construction/add/orderDetail";
    }

    @RequestMapping("/myorder/migration/add/toMigrationDetail")
    public String toMigrationDetail(){
        return "/order/myorder/migration/add/orderDetail";
    }

    @RequestMapping("/myorder/removement/add/toRemovementDetail")
    public String toRemovementDetail(){
        return "/order/myorder/removement/add/orderDetail";
    }

    @RequestMapping("/myorder/maintaining/add/toMaintainingDetail")
    public String toMaintainingDetail(){
        return "/order/myorder/maintaining/add/orderDetail";
    }


    /**
     * 将建设工单明细的List集合临时放置在session中存放
     * @param constructDetailList
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/construction/saveTemporaryConstructionDetailList")
    public Object saveTemporaryConstructionDetailList(@RequestBody List<ConstructDetail> constructDetailList, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("constructDetailList",constructDetailList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }


    /**
     * 将拆机工单明细的List集合临时放置在session中存放
     * @param resourceRemovementList
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/removement/saveTemporaryResourceRemovementList")
    public Object saveTemporaryResourceRemovementList(@RequestBody List<ResourceRemovement> resourceRemovementList, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("resourceRemovementList",resourceRemovementList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }


    /**
     * 将拆机工单明细的List集合临时放置在session中存放
     * @param resourceMigrationList
     * @return
     */
    @ResponseBody
    @RequestMapping("myorder/migration/saveTemporaryResourceMigrationList")
    public Object saveTemporaryResourceMigrationList(@RequestBody List<ResourceMigration> resourceMigrationList, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("resourceMigrationList",resourceMigrationList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/myorder/removement/add/toPreviewRemovementOrder")
    public String toPreviewRemovementOrder(){
        return "/order/myorder/removement/add/previewOrder";
    }


    @RequestMapping("/myorder/construction/add/toConstructionEquipment")
    public String toConstructionEquipment(){
        return "/order/myorder/construction/add/orderEquipmentInfo";
    }

    @RequestMapping("/myorder/migration/add/toMigrationEquipment")
    public String toMigrationEquipment(){
        return "/order/myorder/migration/add/orderEquipmentInfo";
    }


    /**
     * 将建设工单明细的List集合临时放置在session中存放
     * @param equipmentPurchaseRecordList
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/saveTemporaryEquipmentPurchaseList")
    public Object saveTemporaryEquipmentPurchaseList(@RequestBody(required = false) List<EquipmentPurchaseRecord> equipmentPurchaseRecordList, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            session.setAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }


    @RequestMapping("/myorder/construction/add/toPreviewConstructionOrder")
    public String toPreviewConstructionOrder(){
        return "/order/myorder/construction/add/previewOrder";
    }

    @ResponseBody
    @RequestMapping("/myorder/construction/add/doAddConstructionOrder")
    public Object doAddConstructionOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ConstructDetail> constructDetailList = (List<ConstructDetail>)session.getAttribute("constructDetailList");
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = (List<EquipmentPurchaseRecord>)session.getAttribute("equipmentPurchaseRecordList");
            if(order == null){
                result.setSuccess(false);
                result.setMessage("暂未填写工单基本信息，请重新填写");
                return result;
            }
            if(constructDetailList==null){
                result.setSuccess(false);
                result.setMessage("暂未填写装机明细信息，请重新填写");
                return result;
            }

            //设置order的其他信息
            order.setStatus("待提交");
            order.setProposerId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(sdf.format(date));

            orderService.saveOrder(order);

            for(ConstructDetail constructDetail: constructDetailList){
                constructDetail.setCreateTime(sdf.format(date));
                constructDetail.setCreatorId(user.getId());
                constructDetail.setOrderId(order.getId());
            }

            //发送给后台处理
            constructDetailService.saveConstructDetailBatch(constructDetailList);

            for(EquipmentPurchaseRecord equipmentPurchaseRecord: equipmentPurchaseRecordList){
                equipmentPurchaseRecord.setCreateTime(sdf.format(date));
                equipmentPurchaseRecord.setCreatorId(user.getId());
                equipmentPurchaseRecord.setOrderId(order.getId());
            }

            //发送给后台处理
            equipmentPurchaseRecordService.saveEquipmentPurchaseRecordByBatch(equipmentPurchaseRecordList);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("constructDetailList");
            session.removeAttribute("equipmentPurchaseRecordList");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/myorder/removement/add/doAddRemovementOrder")
    public Object doAddRemovementOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ResourceRemovement> resourceRemovementList = (List<ResourceRemovement>)session.getAttribute("resourceRemovementList");
            if(order == null){
                result.setSuccess(false);
                result.setMessage("暂未填写工单基本信息，请重新填写");
                return result;
            }
            if(resourceRemovementList==null){
                result.setSuccess(false);
                result.setMessage("暂未填写装机明细信息，请重新填写");
                return result;
            }

            orderService.doAddRemovementOrder(user,order,resourceRemovementList);

            result.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("resourceRemovementList");
        }
        return result;
    }

    @RequestMapping("/myorder/migration/add/toPreviewMigrationOrder")
    public String toPreviewMigrationOrder(){
        return "/order/myorder/migration/add/previewOrder";
    }


    /**
     * 生成迁移工单
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myorder/migration/add/doAddMigrationOrder")
    public Object doAddMigrationOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ResourceMigration> resourceMigrationList = (List<ResourceMigration>)session.getAttribute("resourceMigrationList");
            List<EquipmentPurchaseRecord> equipmnetPurchaseDetailList = (List<EquipmentPurchaseRecord>)session.getAttribute("equipmnetPurchaseDetailList");
            if(order == null){
                result.setSuccess(false);
                result.setMessage("暂未填写工单基本信息，请重新填写");
                return result;
            }
            if(resourceMigrationList==null){
                result.setSuccess(false);
                result.setMessage("暂未填写装机明细信息，请重新填写");
                return result;
            }

            //设置order的其他信息
            order.setStatus("待提交");
            order.setProposerId(user.getId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order.setCreateTime(sdf.format(date));

            orderService.saveOrder(order);

            for(ResourceMigration resourceMigration: resourceMigrationList){
                resourceMigration.setCreate_time(sdf.format(date));
                resourceMigration.setCreator_id(user.getId());
                resourceMigration.setOrder_id(order.getId());
            }

            //发送给后台处理
            resourceMigrationService.saveMigrationResourceList(resourceMigrationList);

            for(EquipmentPurchaseRecord equipmentPurchaseRecord: equipmnetPurchaseDetailList){
                equipmentPurchaseRecord.setCreateTime(sdf.format(date));
                equipmentPurchaseRecord.setCreatorId(user.getId());
                equipmentPurchaseRecord.setOrderId(order.getId());
            }

            //发送给后台处理
            equipmentPurchaseRecordService.saveEquipmentPurchaseRecordByBatch(equipmnetPurchaseDetailList);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("resourceMigrationList");
            session.removeAttribute("equipmnetPurchaseDetailList");

        }
        return result;
    }



    @RequestMapping("/myorder/toDetail/{id}")
    public String toDetail(@PathVariable Long id,Model model){

        Order order = orderService.selectOrderById(id);
        model.addAttribute("order",order);
        if(Const.CONSTRUCTION.equals(order.getType())){
            List<ConstructDetail> constructDetailList = orderService.queryConstructDetailListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            model.addAttribute("constructDetailList",constructDetailList);
            model.addAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            return "/order/myorder/construction/detail/construction_detail";
        }else if (Const.MIGRATION.equals(order.getType())){
            List<ResourceMigration> resourceMigrationList = orderService.queryResourceMigrationListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            model.addAttribute("resourceMigrationList",resourceMigrationList);
            model.addAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            return "/order/myorder/migration/detail/migration_detail";
        }else if(Const.REMOVEMENT.equals(order.getType())){
            List<ResourceRemovement> resourceRemovementList = orderService.queryResourceRemovementListByOrder(order);
            model.addAttribute("resourceRemovementList",resourceRemovementList);
            return "/order/myorder/removement/detail/removement_detail";
        }else if(Const.CHANGE_ITEM.equals(order.getType())){
            return "/order/myorder/maintaining/detail/change_item_detail";
        }

        return "/error";

    }

    @RequestMapping("/myorder/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id,HttpSession session){

        Order order = orderService.selectOrderById(id);
        session.setAttribute("order",order);
        if(Const.CONSTRUCTION.equals(order.getType())){
            List<ConstructDetail> constructDetailList = orderService.queryConstructDetailListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            session.setAttribute("constructDetailList",constructDetailList);
            session.setAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            return "/order/myorder/construction/update/orderInfo";
        }else if (Const.MIGRATION.equals(order.getType())){
            List<ResourceMigration> resourceMigrationList = orderService.queryResourceMigrationListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            session.setAttribute("resourceMigrationList",resourceMigrationList);
            session.setAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            return "/order/myorder/migration/update/orderInfo";
        }else if(Const.REMOVEMENT.equals(order.getType())){
            List<ResourceRemovement> resourceRemovementList = orderService.queryResourceRemovementListByOrder(order);
            session.setAttribute("resourceRemovementList",resourceRemovementList);
            return "/order/myorder/removement/update/orderInfo";
        }else if(Const.CHANGE_ITEM.equals(order.getType())){
            return "/order/myorder/change_item_update";
        }

        return "/error";

    }

    @RequestMapping("/myorder/construction/update/toConstructionUpdateDetail")
    public String toConstructionUpdateDetail(){
        return "/order/myorder/construction/update/orderDetail";
    }

    @RequestMapping("/myorder/migration/update/toMigrationUpdateDetail")
    public String toMigrationUpdateDetail(){
        return "/order/myorder/migration/update/orderDetail";
    }

    @RequestMapping("/myorder/removement/update/toRemovementUpdateDetail")
    public String toRemovementUpdateDetail(){
        return "/order/myorder/removement/update/orderDetail";
    }

    @RequestMapping("/myorder/maintaining/update/toMaintainingUpdateDetail")
    public String toMaintainingUpdateDetail(){
        return "/order/myorder/maintaining/update/orderDetail";
    }

    /**
     * 建设工单跳转至更新设备页面
     * @return
     */
    @RequestMapping("/myorder/construction/update/toConstructionUpdateEquipment")
    public String toConstructionUpdateEquipment(){
        return "/order/myorder/construction/update/orderEquipmentInfo";
    }

    @RequestMapping("/myorder/construction/update/toPreviewConstructionUpdateOrder")
    public String toPreviewConstructionUpdateOrder(){
        return "/order/myorder/construction/update/previewOrder";
    }

    @RequestMapping("/myorder/removement/update/toPreviewUpdateRemovementOrder")
    public String toPreviewUpdateRemovementOrder(){
        return "/order/myorder/removement/update/previewOrder";
    }

    @ResponseBody
    @RequestMapping("/myorder/construction/update/doUpdateConstructionOrder")
    public Object doUpdateConstructionOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ConstructDetail> constructDetailList = (List<ConstructDetail>)session.getAttribute("constructDetailList");
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = (List<EquipmentPurchaseRecord>)session.getAttribute("equipmentPurchaseRecordList");
            orderService.updateConstructionOrderInfo(order,user,constructDetailList,equipmentPurchaseRecordList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("constructDetailList");
            session.removeAttribute("equipmentPurchaseRecordList");
        }
        return result;
    }

    @RequestMapping("/myorder/migration/update/toMigrationUpdateEquipment")
    public String toMigrationUpdateEquipment(){
        return "/order/myorder/migration/update/orderEquipmentInfo";
    }


    @RequestMapping("/myorder/migration/update/toPreviewMigrationUpdateOrder")
    public String toPreviewMigrationUpdateOrder(){
        return "/order/myorder/migration/update/previewOrder";
    }

    @ResponseBody
    @RequestMapping("/myorder/migration/update/doUpdateMigrationOrder")
    public Object doUpdateMigrationOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ResourceMigration> resourceMigrationList = (List<ResourceMigration>)session.getAttribute("resourceMigrationList");
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = (List<EquipmentPurchaseRecord>)session.getAttribute("equipmentPurchaseRecordList");
            orderService.updateMigrationOrderInfo(order,user,resourceMigrationList,equipmentPurchaseRecordList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("创建工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("resourceMigrationList");
            session.removeAttribute("equipmentPurchaseRecordList");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/myorder/removement/update/doUpdateRemovementOrder")
    public Object doUpdateRemovementOrder(HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            User user = (User)session.getAttribute("user");
            Order order = (Order)session.getAttribute("order");
            List<ResourceRemovement> resourceRemovementList = (List<ResourceRemovement>)session.getAttribute("resourceRemovementList");
            orderService.doUpdateRemovementOrder(order,user,resourceRemovementList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("更新工单异常，请联系管理员解决");
        }finally {
            //创建工单后销毁相关的session属性
            session.removeAttribute("order");
            session.removeAttribute("resourceRemovementList");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/myorder/doDelete/{id}")
    public Object doDelete(@PathVariable Long id){
        AjaxResult result = new AjaxResult();
        try {
            orderService.deleteOrderByID(id);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员解决");
        }
        return result;
    }



    @ResponseBody
    @RequestMapping("/myorder/doAuditOrder/{id}")
    public Object doAuditOrder(@PathVariable Long id, HttpSession session){

        Ticket ticket = new Ticket();

        AjaxResult result = new AjaxResult();
        try {

            //1.创建流程定义
            //Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/order_auth.bpmn").deploy();

            //2.查询最新版本的流程定义
            ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("order_auth").latestVersion().singleResult();


            User user = (User) session.getAttribute(Const.LOGIN_USER);
//            Role role = userRoleService.queryRoleByUserId(user.getId());

            //3.创建流程实例
            Map<String,Object> values = new HashMap<>();
		    values.put("passListener",new PassListener());
		    values.put("refuseListener",new RefuseListener());
            values.put("loginacct", user.getLoginacct());
            ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),values);

//            CommandContext commandContext = new CommandContext();
//            ActUserEntityServiceFactory actUserEntityServiceFactory = new ActUserEntityServiceFactory();
//            actUserEntityServiceFactory.openSession(commandContext);




            //4.查询用户任务
//            Map<String,Object> variables = new HashMap<>();
//            variables.put("loginacct",user.getLoginacct());
            TaskService taskService = processEngine.getTaskService();
            Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskAssignee(user.getLoginacct()).singleResult();
            taskService.complete(task.getId());

            //更新order状态
            Order order = orderService.selectOrderById(id);
            order.setStatus("待审核");
            //order.setPiid(processInstance.getId());
            orderService.updateOrder(order);

            //创建ticket对象
            ticket.setOrderId(order.getId());
            ticket.setPiid(processInstance.getId());
            ticket.setStatus("0");
            ticketService.insertTicket(ticket);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("提交审核异常，请联系管理员解决");
        }
        return result;
    }


    @RequestMapping("/preHandleOrder/toPreHandleOrderIndex")
    public String toPreHandleOrderIndex(){
        return "/order/pre_handle_order/index";
    }

    @ResponseBody
    @RequestMapping("/preHandleOrder/PreHandleOrderIndex")
    public Object PreAuditOrderIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        @RequestParam(value = "order_type",required = false) String order_type,
                        @RequestParam(value = "apply_department_id",required = false) Integer apply_department_id){

        AjaxResult result = new AjaxResult();

        try{

            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            params.put("order_type",order_type);
            params.put("apply_department_id",apply_department_id);

            Page<Object> page = orderService.selectPreHandleOrder(params);
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/preHandleOrder/toPreHandleOrderDetail/{id}")
    public String toPreHandleOrderDetail(@PathVariable Long id,HttpSession session){
        Order order = orderService.selectOrderById(id);
        session.setAttribute("order",order);

        if(Const.CONSTRUCTION.equals(order.getType())){
            List<ConstructDetail> constructDetailList = orderService.queryConstructDetailListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            session.setAttribute("constructDetailList",constructDetailList);
            session.setAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            if(equipmentPurchaseRecordList == null || equipmentPurchaseRecordList.isEmpty()){
                return "/order/pre_handle_order/construction/construction_old_equipment";
            }else{
                return "/order/pre_handle_order/construction/construction_new_equipment";
            }
        }else if (Const.MIGRATION.equals(order.getType())){
            List<ResourceMigration> resourceMigrationList = orderService.queryResourceMigrationListByOrder(order);
            List<EquipmentPurchaseRecord> equipmentPurchaseRecordList = orderService.queryEquipmentPurchaseRecordListByOrder(order);
            session.setAttribute("resourceMigrationList",resourceMigrationList);
            session.setAttribute("equipmentPurchaseRecordList",equipmentPurchaseRecordList);
            return "/order/pre_handle_order/migration/migration_detail";
        }else if(Const.REMOVEMENT.equals(order.getType())){
            List<ResourceRemovement> resourceRemovementList = orderService.queryResourceRemovementListByOrder(order);
            session.setAttribute("resourceRemovementList",resourceRemovementList);
            return "/order/pre_handle_order/removement/removement_detail";
        }else if(Const.CHANGE_ITEM.equals(order.getType())){
            return "/order/pre_handle_order/construction/change_item_detail";
        }

        return "/error";

    }



//    /**
//     * 将建设工单临时放置在session中存放
//     * @param resourceList
//     * @param session
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/preHandleOrder/construction/saveResourceList")
//    public Object saveResourceList(@RequestBody List<Resource> resourceList, HttpSession session){
//        AjaxResult result = new AjaxResult();
//        try {
//            session.setAttribute("resourceList",resourceList);
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("生成异常，请联系管理员解决");
//        }
//        return result;
//    }

    /**
     * 将待处理工单的临时数据存放到session中，用于资源配对设备页面
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping("/preHandleOrder/migration/saveResourceMigrationList")
    public Object saveResourceMigrationList(@RequestBody JSONObject jsonObject,HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            List<ResourceMigration> resourceMigrationList = jsonObject.getJSONArray("resourceMigrationList").toJavaList(ResourceMigration.class);
            List<Equipment> equipmentList = jsonObject.getJSONArray("equipmentList").toJavaList(Equipment.class);
            session.setAttribute("resourceMigrationList",resourceMigrationList);
            session.setAttribute("equipmentList",equipmentList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }



    @RequestMapping("/preHandleOrder/construction/toResourceConstruction")
    public String toResourceConstruction(){
        return "construction_old_equipment";
    }

    @RequestMapping("/preHandleOrder/migration/toResourceMigration")
    public String toResourceMigration(){
        return "/order/pre_handle_order/migration/migration_resource";
    }


    /**
     * 将建设工单临时放置在session中存放
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/preHandleOrder/construction/saveEquipmentList")
    public Object saveEquipmentList(@RequestBody JSONObject jsonObject, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            List<Equipment> equipmentList = jsonObject.getJSONArray("equipmentList").toJavaList(Equipment.class);
            session.setAttribute("equipmentList",equipmentList);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("生成异常，请联系管理员解决");
        }
        return result;
    }

    /**
     * 将待处理工单的临时数据存放到session中，用于资源配对设备页面
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping("/preHandleOrder/construction/saveResourceList")
    public Object saveResourceList(@RequestBody JSONObject jsonObject, HttpSession session){
        AjaxResult result = new AjaxResult();
        try {
            if(jsonObject.containsKey("equipmentList")){
                List<Equipment> equipments = jsonObject.getJSONArray("equipmentList").toJavaList(Equipment.class);
                List<Equipment> equipmentList = orderService.queryEquipmentDetailList(equipments);
                session.setAttribute("equipmentList",equipmentList);
            }

            List<Resource> resources = jsonObject.getJSONArray("resourceList").toJavaList(Resource.class);
            List<Resource> resourceList = orderService.queryResourceDetailList(resources);

            session.setAttribute("resourceList",resourceList);

            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("跳转异常，请联系管理员解决");
        }
        return result;
    }

    @RequestMapping("/preHandleOrder/construction/toPreviewConstruction")
    public String toPreviewConstruction(){
        return "/order/pre_handle_order/construction/construction_preview";
    }

    @RequestMapping("/preHandleOrder/migration/toPreviewMigration")
    public String toPreviewMigration(){
        return "/order/pre_handle_order/migration/migration_preview";
    }




}
