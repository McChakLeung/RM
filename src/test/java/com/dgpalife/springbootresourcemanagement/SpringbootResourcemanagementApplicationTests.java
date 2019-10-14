package com.dgpalife.springbootresourcemanagement;

import com.dgpalife.resourcemanagement.SpringbootResourcemanagementApplication;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
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
}
