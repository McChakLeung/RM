package com.dgpalife.springbootresourcemanagement;

import com.dgpalife.resourcemanagement.SpringbootResourcemanagementApplication;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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
}
