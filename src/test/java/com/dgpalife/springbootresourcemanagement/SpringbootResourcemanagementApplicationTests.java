package com.dgpalife.springbootresourcemanagement;

import com.dgpalife.resourcemanagement.SpringbootResourcemanagementApplication;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootResourcemanagementApplication.class)

//@WebAppConfiguration
public class SpringbootResourcemanagementApplicationTests {


	@Autowired
	private ProcessEngine processEngine;

	@Test
	public void contextLoads() {
	}

	/**
	 * 创建部署流程
	 */
	@Test
	public void test01(){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/one-task-process.bpmn").deploy();
		Deployment deployment = repositoryService.createDeployment().deploy();
		System.out.println(deployment);
	}

	/**
	 * 查询部署的流程定义
	 */
	@Test
	public void test02(){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		//查询所有已部署的流程定义
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.list();
		for (ProcessDefinition processDefinition:processDefinitionList){
			System.out.println("DeploymentId = " + processDefinition.getDeploymentId());
			System.out.println("Category = " + processDefinition.getCategory());
			System.out.println("Id = " + processDefinition.getId());
			System.out.println("ResourceName = " + processDefinition.getResourceName());
		}
		System.out.println("==============================================");
//		//查询某个已部署的流程定义
//		processDefinitionQuery = processDefinitionQuery.deploymentId("8");
//		System.out.println(processDefinitionQuery.singleResult().getId());
	}

	/**
	 * 创建（启动）流程实例
	 */
	@Test
	public void test03(){
		//创建流程查询对象，找到需要启动的流程
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_2").latestVersion().singleResult();

		//根据查询的流程，创建流程实例并启动
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		System.out.println(processInstance);

	}

	@Test
	public void test04(){
		//创建流程查询对象，找到需要启动的流程
		RepositoryService repositoryService = processEngine.getRepositoryService();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_2").latestVersion().singleResult();

		//根据查询的流程，创建流程实例并启动
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		System.out.println(processInstance);


		//获取流程任务
		TaskService taskService = processEngine.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> taskList1 = taskQuery.taskAssignee("zhangsan").list();
		List<Task> taskList2 = taskQuery.taskAssignee("lisi").list();

		//查询用户完成前的任务
		System.out.println("zhangsan的任务");
		for (Task task : taskList1) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}
		System.out.println("lisi的任务");
		for (Task task : taskList2) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//重新查询
		//taskQuery = taskService.createTaskQuery();
		taskList1 = taskQuery.taskAssignee("zhangsan").list();
		taskList2 = taskQuery.taskAssignee("lisi").list();

		//假定张三完成任务，看看是否流转至李四
		System.out.println("zhangsan的任务");
		for (Task task : taskList1) {
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}
		System.out.println("lisi的任务");
		for (Task task : taskList2) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//重新查询
		//taskQuery = taskService.createTaskQuery();
		taskList1 = taskQuery.taskAssignee("zhangsan").list();
		taskList2 = taskQuery.taskAssignee("lisi").list();

		//假定lisi完成任务，看看最终结果
		System.out.println("zhangsan的任务");
		for (Task task : taskList1) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}

		System.out.println("lisi的任务");
		for (Task task : taskList2) {
			System.out.println(task.getName());
			taskService.complete(task.getId());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//重新查询
		//taskQuery = taskService.createTaskQuery();
		taskList1 = taskQuery.taskAssignee("zhangsan").list();
		taskList2 = taskQuery.taskAssignee("lisi").list();

		System.out.println("zhangsan的任务");
		for (Task task : taskList1) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}

		System.out.println("lisi的任务");
		for (Task task : taskList2) {
			System.out.println(task.getName());
			//taskService.complete(task.getId());
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}


	/**
	 * 获取历史服务对象，查看历史流程
	 */
	@Test
	public void test5(){
		HistoryService historyService = processEngine.getHistoryService();
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId("7501").finished().singleResult();
		System.out.println(historicProcessInstance);
		System.out.println(historicProcessInstance.getId());
		System.out.println(historicProcessInstance.getDeploymentId());
		System.out.println(historicProcessInstance.getEndTime());
	}

	/**
	 * Activiti流程框架-任务领取
	 */
	@Test
	public void test6(){
		//1.创建流程定义
		Deployment deploy = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/test06.bpmn").deploy();
		System.out.println(deploy);

		System.out.println("---------------------------");

		//2.查询流程定义
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("myProcess_3").latestVersion().singleResult();
		System.out.println(processDefinition);

		System.out.println("---------------------------");

		//3.根据查询的流程定义，启动流程实例
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId());
		System.out.println(processInstance);

		System.out.println("---------------------------");

		//4.查询张三所分配的任务

		List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();

		for (Task task : taskList){
			System.out.println("zhangsan领取之前的任务名称："+task.getName());
		}

		System.out.println("---------------------------");

		//5.查询当前的任务，并将当前的任务分配给张三
		List<Task> groupTaskList = processEngine.getTaskService().createTaskQuery().taskCandidateGroup("tl").list();
		for (Task task: groupTaskList){
			processEngine.getTaskService().claim(task.getId(),"zhangsan");
		}

		System.out.println("---------------------------");

		//6.再次查询张三所分配的任务

		taskList = processEngine.getTaskService().createTaskQuery().taskAssignee("zhangsan").processInstanceId(processInstance.getId()).list();

		for (Task task : taskList){
			System.out.println("zhangsan领取之后的任务名称："+task.getName());
			processEngine.getTaskService().complete(task.getId());
			System.out.println("zhangsan完成之后的任务名称："+task.getName());
		}
	}
}
