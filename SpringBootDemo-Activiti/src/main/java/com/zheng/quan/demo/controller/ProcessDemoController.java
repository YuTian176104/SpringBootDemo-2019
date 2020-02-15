package com.zheng.quan.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessDemoController {

	private static final Logger logger = LoggerFactory.getLogger(ProcessDemoController.class);
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "test2", method = RequestMethod.GET)
	public Map<String, Object> test2() {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "Success");
		result.put("code", "200");
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("process_test2");
		String processId = pi.getId();
		logger.info(">>>流程创建成功，当前流程实例ID：" + processId);
		
		return result;
	}
	
	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public Map<String, Object> demo() {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "Success");
		result.put("code", "200");
		// 根据bpmn文件部署流程
		Deployment deployment = this.repositoryService.createDeployment().addClasspathResource("processes/test.bpmn").deploy();
		// 获取流程部署信息（数据库表act_re_deployment）
		DeploymentEntityImpl deploymentEntity = (DeploymentEntityImpl) this.repositoryService.createDeploymentQuery().deploymentId(deployment.getId()).singleResult();
		logger.info(">>>获取流程部署版本ID：" + deploymentEntity.getId());
		
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentEntity.getId()).singleResult();
		logger.info(">>>获取流程定义ID：" + processDefinition.getId() + ", 流程定义Key：" + processDefinition.getKey());
		
		// 启动流程定义，返回流程实例
		ProcessInstance pi = this.runtimeService.startProcessInstanceById(processDefinition.getId());
		String processInstanceId = pi.getId();
		logger.info(">>>流程创建成功，当前流程实例ID：" + processInstanceId);
		
		// 流程实例创建成功，使用实例ID获取任务信息
		Task task = this.taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		logger.info(">>>第一次执行前，任务名称：" + task.getName());
		this.taskService.complete(task.getId());
		
		// 使用流程定义ID（最新版本流程ID）获取任务信息
		task = this.taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).singleResult();
		logger.info(">>>第二次执行前，任务名称：" + task.getName());
		this.taskService.complete(task.getId());
		
		task = this.taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).singleResult();
		logger.info(">>>task为null，任务执行完毕：" + task);
		
		return result;
	}
	
	@RequestMapping(value = "demo2", method = RequestMethod.GET)
	public Map<String, Object> demo2() {
		Map<String, Object> result = new HashMap<>();
		// 启动流程定义，返回流程实例，流程定义数据库表act_re_procdef
		// startProcessInstanceByKey 根据Key获取最新版本的流程定义
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("myProcess");
		// startProcessInstanceById	因为每次部署同一个流程定义文件，其版本号会自增，导致流程定义ID发生改变。可用来获取某个版本的流程定义
		pi = this.runtimeService.startProcessInstanceById("myProcess:1:4");
		String processId = pi.getId();
		logger.info(">>>流程创建成功，当前流程实例ID：" + processId);
		result.put("msg", "success");
		result.put("code", "200");
		return result;
	}
	
	@RequestMapping(value = "demo3", method = RequestMethod.GET)
	public Map<String, Object> demo3() {
		Map<String, Object> result = new HashMap<>();
		
		// 启动流程定义，返回流程实例
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey("myProcess");
		String processId = pi.getId();
		logger.info(">>>流程创建成功，当前流程实例ID：" + processId);
		
		Task task = this.taskService.createTaskQuery().processInstanceId(processId).singleResult();
		logger.info(">>>第一次执行前，任务名称：" + task.getName());
		this.taskService.complete(task.getId());
		
		task = this.taskService.createTaskQuery().processInstanceId(processId).singleResult();
		logger.info(">>>第二次执行前，任务名称：" + task.getName());
		this.taskService.complete(task.getId());
		
		task = this.taskService.createTaskQuery().processInstanceId(processId).singleResult();
		logger.info(">>>task为null，任务执行完毕：" + task);
		
		result.put("msg", "success");
		result.put("code", "200");
		return result;
	}
}
