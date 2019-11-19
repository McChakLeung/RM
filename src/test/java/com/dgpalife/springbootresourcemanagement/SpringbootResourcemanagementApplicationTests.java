//package com.dgpalife.springbootresourcemanagement;
//
//import com.dgpalife.resourcemanagement.SpringbootResourcemanagementApplication;
//import com.dgpalife.resourcemanagement.listener.activiti.listener.NoListener;
//import com.dgpalife.resourcemanagement.listener.activiti.listener.YesListener;
//import org.activiti.engine.*;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricProcessInstanceQuery;
//import org.activiti.engine.identity.User;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.repository.ProcessDefinition;
//import org.activiti.engine.repository.ProcessDefinitionQuery;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.engine.task.TaskQuery;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringbootResourcemanagementApplication.class)
//@EnableScheduling
////@WebAppConfiguration
//public class SpringbootResourcemanagementApplicationTests {
//
//
//	@Autowired
//	private ProcessEngine processEngine;
//
//	@Test
//	public void contextLoads() {
//	}
//
//	/**
//	 * 创建部署流程
//	 */
//	@Test
//	public void test01(){
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		//Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/one-task-process.bpmn").deploy();
//		Deployment deployment = repositoryService.createDeployment().deploy();
//		System.out.println(deployment);
//	}
//
//	/**
//	 * 查询部署的流程定义
//	 */
//	@Test
//	public void test02(){
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
//		//查询所有已部署的流程定义
//		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
//		for (ProcessDefinition processDefinition:processDefinitionList){
//			System.out.println("DeploymentId = " + processDefinition.getDeploymentId());
//			System.out.println("Category = " + processDefinition.getCategory());
//			System.out.println("Id = " + processDefinition.getId());
//			System.out.println("ResourceName = " + processDefinition.getResourceName());
//		}
//		System.out.println("==============================================");
////		//查询某个已部署的流程定义
////		processDefinitionQuery = processDefinitionQuery.deploymentId("8");
////		System.out.println(processDefinitionQuery.singleResult().getId());
//	}
//
//	/**
//	 * 创建（启动）流程实例
//	 */
//	@Test
//	public void test03(){
//		//创建流程查询对象，找到需要启动的流程
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_2").latestVersion().singleResult();
//
//		//根据查询的流程，创建流程实例并启动
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
//		System.out.println(processInstance);
//
//	}
//
//	@Test
//	public void test04(){
//		//创建流程查询对象，找到需要启动的流程
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_2").latestVersion().singleResult();
//
//		//根据查询的流程，创建流程实例并启动
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
//		System.out.println(processInstance);
//
//
//		//获取流程任务
//		TaskService taskService = processEngine.getTaskService();
//		TaskQuery taskQuery = taskService.createTaskQuery();
//		List<Task> taskList1 = taskQuery.taskAssignee("zhangsan").list();
//		List<Task> taskList2 = taskQuery.taskAssignee("lisi").list();
//
//		//查询用户完成前的任务
//		System.out.println("zhangsan的任务");
//		for (Task task : taskList1) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//		System.out.println("lisi的任务");
//		for (Task task : taskList2) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//		//重新查询
//		//taskQuery = taskService.createTaskQuery();
//		taskList1 = taskQuery.taskAssignee("zhangsan").list();
//		taskList2 = taskQuery.taskAssignee("lisi").list();
//
//		//假定张三完成任务，看看是否流转至李四
//		System.out.println("zhangsan的任务");
//		for (Task task : taskList1) {
//			System.out.println(task.getName());
//			taskService.complete(task.getId());
//		}
//		System.out.println("lisi的任务");
//		for (Task task : taskList2) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//		//重新查询
//		//taskQuery = taskService.createTaskQuery();
//		taskList1 = taskQuery.taskAssignee("zhangsan").list();
//		taskList2 = taskQuery.taskAssignee("lisi").list();
//
//		//假定lisi完成任务，看看最终结果
//		System.out.println("zhangsan的任务");
//		for (Task task : taskList1) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//
//		System.out.println("lisi的任务");
//		for (Task task : taskList2) {
//			System.out.println(task.getName());
//			taskService.complete(task.getId());
//		}
//
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//		//重新查询
//		//taskQuery = taskService.createTaskQuery();
//		taskList1 = taskQuery.taskAssignee("zhangsan").list();
//		taskList2 = taskQuery.taskAssignee("lisi").list();
//
//		System.out.println("zhangsan的任务");
//		for (Task task : taskList1) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//
//		System.out.println("lisi的任务");
//		for (Task task : taskList2) {
//			System.out.println(task.getName());
//			//taskService.complete(task.getId());
//		}
//
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//	}
//
//
//	/**
//	 * 获取历史服务对象，查看历史流程
//	 */
//	@Test
//	public void test5(){
//		HistoryService historyService = processEngine.getHistoryService();
//		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId("7501").finished().singleResult();
//		System.out.println(historicProcessInstance);
//		System.out.println(historicProcessInstance.getId());
//		System.out.println(historicProcessInstance.getDeploymentId());
//		System.out.println(historicProcessInstance.getEndTime());
//	}
//
//	/**
//	 * Activiti流程框架-任务领取
//	 */
//	@Test
//	public void test6(){
//		//1.创建流程定义
//		Deployment deploy = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test06.bpmn").deploy();
//		System.out.println(deploy);
//
//		System.out.println("---------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_3").latestVersion().singleResult();
//		System.out.println(processDefinition);
//
//		System.out.println("---------------------------");
//
//		//3.根据查询的流程定义，启动流程实例
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId());
//		System.out.println(processInstance);
//
//		System.out.println("---------------------------");
//
//		//4.查询张三所分配的任务
//
//		List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();
//
//		for (Task task : taskList){
//			System.out.println("zhangsan领取之前的任务名称："+task.getName());
//		}
//
//		System.out.println("---------------------------");
//
//		//5.查询当前的任务，并将当前的任务分配给张三
//		List<Task> groupTaskList = processEngine.getTaskService().createTaskQuery().taskCandidateGroup("tl").list();
//		for (Task task: groupTaskList){
//			processEngine.getTaskService().claim(task.getId(),"zhangsan");
//		}
//
//		System.out.println("---------------------------");
//
//		//6.再次查询张三所分配的任务
//
//		taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();
//
//		for (Task task : taskList){
//			System.out.println("zhangsan领取之后的任务名称："+task.getName());
//			processEngine.getTaskService().complete(task.getId());
//			System.out.println("zhangsan完成之后的任务名称："+task.getName());
//		}
//	}
//
//	/**
//	 * Activiti流程框架-流程变量
//	 */
//	@Test
//	public void test7(){
//		//1.创建流程定义
//		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test07.bpmn").deploy();
//		System.out.println(deployment);
//
//		System.out.println("--------------------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_7").latestVersion().singleResult();
//		System.out.println(processDefinition);
//
//		System.out.println("--------------------------------------");
//
//		//3.根据查询的流程定义，启动流程实例(会存在报错,原因：未对${tl}进行赋值）
//		//org.activiti.engine.ActivitiException: Unknown property used in expression: ${tl}
////		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId());
////		System.out.println(processInstance);
//
//
//		//需传值，封装为一个map集合
//		Map<String,Object> value = new HashMap<>();
//		value.put("tl","zhangsan");
//		value.put("pm","lisi");
//
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),value);
//		System.out.println(processInstance);
//
//		System.out.println("--------------------------------------");
//
//		//4.查询张三及李四分配的任务
//		List<Task> zhangsan = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();
//		for(Task task: zhangsan){
//			System.out.println("zhangsan的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		List<Task> lisi = processEngine.getTaskService().createTaskQuery().taskAssignee("lisi").processInstanceId(processInstance.getId()).list();
//		for(Task task: lisi){
//			System.out.println("lisi的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		System.out.println("--------------------------------------");
//	}
//
//	/**
//	 * Activiti流程框架-排他网关-决策
//	 */
//	@Test
//	public void test8(){
//		//1.创建流程定义
//		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test08.bpmn").deploy();
//		System.out.println(deployment);
//
//		System.out.println("--------------------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_8").latestVersion().singleResult();
//		System.out.println(processDefinition);
//
//		System.out.println("--------------------------------------");
//
//		//3.创建流程实例,并传入条件参数
//		Map<String,Object> value = new HashMap<>();
//		value.put("days","3");
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),value);
//		System.out.println(processInstance);
//
//		System.out.println("--------------------------------------");
//
//		//4.查询两个任务节点的状态
//		List<Task> zhangsan = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();
//		for(Task task: zhangsan){
//			System.out.println("zhangsan的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		List<Task> lisi = processEngine.getTaskService().createTaskQuery().taskAssignee("lisi").processInstanceId(processInstance.getId()).list();
//		for(Task task: lisi){
//			System.out.println("lisi的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		System.out.println("--------------------------------------");
//
//		HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().singleResult();
//		System.out.println(historicProcessInstance.toString());
//	}
//
//	/**
//	 * Activiti流程框架-并行网关-会签
//	 */
//	@Test
//	public void test9(){
//		//1.创建流程定义
//		Deployment deploy = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test09.bpmn").deploy();
//		System.out.println(deploy.toString());
//
//		System.out.println("--------------------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_9").latestVersion().singleResult();
//		System.out.println(processDefinition.toString());
//
//		System.out.println("--------------------------------------");
//
//		//3.创建流程实例
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId());
//		System.out.println(processInstance.toString());
//
//		System.out.println("--------------------------------------");
//
//		//4-1.pm完成审批
//		List<Task> zhangsan = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processDefinitionId(processDefinition.getId()).list();
//		for(Task task:zhangsan){
//			System.out.println("zhangsan的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		System.out.println("--------------------------------------");
//
//		//查询流程历史记录
//		HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().singleResult();
//		System.out.println(historicProcessInstance!=null);
//
//		System.out.println("--------------------------------------");
//
//		//4-2.fm完成审判
//		List<Task> lisi = processEngine.getTaskService().createTaskQuery().taskAssignee("lisi").processDefinitionId(processDefinition.getId()).list();
//		for(Task task: lisi){
//			System.out.println("lisi的任务：" + task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//		System.out.println("--------------------------------------");
//
//		//5.查询流程历史记录
//		historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().singleResult();
//		System.out.println(historicProcessInstance!=null);
//
//		System.out.println("--------------------------------------");
//	}
//
//	/**
//	 * Activiti流程框架-包含网关（排他+并行）
//	 * 如果当前条件只有一个成立则执行后续流程-相当于排他网关，比如出差5天，费用申请2000，则只需要pm审批通过即可
//	 * 如果当前所有条件成立，则必须所有条件成立的任务完成才会执行后续流程-相当于并行网关，比如出差5天，费用申请8000，则两个流程都需要审批
//	 * 如果当前所有条件都不成立，则无需审批该流程（还会报错），比如出差2天，费用申请1000块，不需要审批
//	 */
//	@Test
//	public void test10(){
//		//1.创建流程定义
//		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test10.bpmn").deploy();
//		System.out.println(deployment.toString());
//
//		System.out.println("--------------------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_10").latestVersion().singleResult();
//		System.out.println(processDefinition.toString());
//
//		System.out.println("--------------------------------------");
//
//		//3.创建流程实例
//		Map<String, Object> value = new HashMap<>();
//		value.put("days",3);
//		value.put("cost",6000);
//
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),value);
//		System.out.println(processInstance.toString());
//
//		System.out.println("--------------------------------------");
//
//		//4-1.查询pm用户任务
//		List<Task> zhangsan = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processDefinitionId(processDefinition.getId()).list();
//		for(Task task:zhangsan){
//			System.out.println("zhangsan的任务："+ task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//
//		//查询实例流程历史
//		HistoricProcessInstance historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().singleResult();
//		System.out.println(historicProcessInstance!=null);
//
//		System.out.println("--------------------------------------");
//
//		//4-2.查询fm用户任务
//		List<Task> lisi = processEngine.getTaskService().createTaskQuery().taskAssignee("lisi").processDefinitionId(processDefinition.getId()).list();
//		for(Task task:lisi){
//			System.out.println("lisi的任务："+task.getName());
//			processEngine.getTaskService().complete(task.getId());
//		}
//		//查询实例流程历史
//		historicProcessInstance = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().singleResult();
//		System.out.println(historicProcessInstance!=null);
//	}
//
//	/**
//	 * 完成自动发送邮件功能
//	 */
//	@Test
//	public void test11(){
//		//1.创建流程定义
//		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test11.bpmn").deploy();
//		System.out.println(deployment);
//
//		System.out.println("--------------------------------------");
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_11").latestVersion().singleResult();
//		System.out.println(processDefinition);
//
//		System.out.println("--------------------------------------");
//
//		//3.对参数进行赋值，并启动流程实例
//		Map<String,Object> value = new HashMap<>();
//		value.put("to","test@atguigu.com");
//
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),value);
//		System.out.println(processInstance);
//	}
//
//
//	/**
//	 * 邮件任务流程监听器
//	 */
//	@Test
//	public void test12(){
//		//1.创建流程定义
//		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test12.bpmn").deploy();
//		System.out.println(deployment.toString());
//
//		//2.查询流程定义
//		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_12").latestVersion().singleResult();
//		System.out.println(processDefinition.toString());
//
//		//3.创建流程实例
//		Map<String,Object> values = new HashMap<>();
//		values.put("yesListener",new YesListener());
//		values.put("noListener",new NoListener());
//
//		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId(),values);
//		System.out.println(processInstance);
//
//		//4.查询用户任务并完成任务，查看控制台输出结果
//
//
//		TaskService taskService = processEngine.getTaskService();
//		List<Task> taskList = taskService.createTaskQuery().taskAssignee("zhangsan").processDefinitionId(processDefinition.getId()).list();
//		for(Task task:taskList){
//			taskService.setVariable(task.getId(),"flag",false);
//			taskService.complete(task.getId());
//		}
//
//	}
//
////	@Test
////	public void test13(){
////		User user = (User)new com.dgpalife.resourcemanagement.model.User();
////		System.out.println(user);
////	}
//
//}
