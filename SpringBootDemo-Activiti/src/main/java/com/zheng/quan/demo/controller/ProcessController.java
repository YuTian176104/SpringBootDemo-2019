package com.zheng.quan.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/process/deal")
public class ProcessController {

	private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 启动流程，创建流程实例 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2020年2月15日 下午3:12:47 <br/>
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "startProcess", method = RequestMethod.GET)
	public Map<String, Object> startProcess(@RequestParam(required = false) String key) {
		Map<String, Object> result = new HashMap<>();
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(key);
		String processInstanceId = pi.getId();
		result.put("processInstanceId", processInstanceId);
		return result;
	}
	
	/**
	 * 根据流程实例ID查询当前待执行的任务 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2020年2月15日 下午3:13:21 <br/>
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "queryCurTaskByProcessInstanceId", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray queryCurTaskByProcessInstanceId(String processInstanceId) {
		List<Task> taskList = this.taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		JSONArray tasks = new JSONArray();
		if (!ObjectUtils.isEmpty(taskList) && taskList.size() > 1) {
			for (Task task : taskList) {
				JSONObject obj = new JSONObject();
				
				obj.put("taskId", task.getId());
				obj.put("taskName", task.getName());
				obj.put("taskExecutionId", task.getExecutionId());
				tasks.add(obj);
			}
		} else if (!ObjectUtils.isEmpty(taskList) && taskList.size() == 1) {
			logger.info(">>>任务执行前，任务名称：" + taskList.get(0).getName());
			JSONObject task = new JSONObject();
			task.put("taskId", taskList.get(0).getId());
			task.put("taskName", taskList.get(0).getName());
			task.put("taskExecutionId", taskList.get(0).getExecutionId());
			tasks.add(task);
		} else {
			logger.info(">>>任务结束");
		}
		return tasks;
	}
	
	/**
	 * 根据任务ID，结束任务 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2020年2月15日 下午3:16:12 <br/>
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "completeTaskByTaskId", method = RequestMethod.GET)
	public JSONObject completeTaskByTaskId(@RequestParam(required = false) String taskId, 
			@RequestParam(required = false) String taskExecutionId, 
			@ApiParam(allowableValues = "0, 1") @RequestParam(required = false) String flag) {
		JSONObject result = new JSONObject();
		Task task = this.taskService.createTaskQuery().executionId(taskExecutionId).singleResult();
		result.put("taskId", task.getId());
		result.put("taskName", task.getName());
		result.put("flag", flag);
		Map<String, Object> v = null;
		if (!StringUtils.isEmpty(flag)) {
			v = new HashMap<>();
			v.put("flag", flag);
		}
		this.taskService.complete(taskId, v);
		
		return result;
	}
	
	/**
	 * 根据主流程实例ID查询其子流程任务信息 <br/>
	 * @author ZHENG.Q <br/>
	 * @createDate 2020年2月15日 下午8:18:06 <br/>
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "querySubProcessByMainProcessInstanceId", method = RequestMethod.GET)
	public JSONArray querySubTasksByMainProcessInstanceId(@RequestParam(required = true) String processInstanceId) {
		JSONArray subPros = new JSONArray();
		String sql = "select a.* from act_ru_execution e, act_ru_execution a "
				+ "where e.ID_ = a.SUPER_EXEC_ and a.ROOT_PROC_INST_ID_ = #{processInstanceId}";
		Execution execution = this.runtimeService.createNativeExecutionQuery()
				.sql(sql)
				.parameter("processInstanceId", processInstanceId)
				.singleResult();
		if (ObjectUtils.isEmpty(execution)) {
			return subPros;
		}
		String subProInstanceId = execution.getProcessInstanceId();
		List<Task> tasks = this.taskService.createTaskQuery().processInstanceId(subProInstanceId).list();
		if (!ObjectUtils.isEmpty(tasks) && tasks.size() > 1) {
			for (Task t : tasks) {
				JSONObject task = new JSONObject();
				task.put("taskId", t.getId());
				task.put("taskName", t.getName());
				task.put("taskExecutionId", t.getExecutionId());
				subPros.add(task);
			}
		} else if (!ObjectUtils.isEmpty(tasks) && tasks.size() == 1) {
			JSONObject task = new JSONObject();
			task.put("taskId", tasks.get(0).getId());
			task.put("taskName", tasks.get(0).getName());
			task.put("taskExecutionId", tasks.get(0).getExecutionId());
			subPros.add(task);
		}
		return subPros;
	}
}
