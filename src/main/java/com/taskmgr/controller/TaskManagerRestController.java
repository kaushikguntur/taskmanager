package com.taskmgr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmgr.service.TaskManagerService;
import com.taskmgr.to.TaskTO;

@RestController
@RequestMapping("/")
public class TaskManagerRestController {
	
	@Autowired
	private TaskManagerService taskManager;
	
	
	@PostMapping("/AddTask")
	public boolean addTask(@RequestBody TaskTO task) {
		try {
			taskManager.addTask(task);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@GetMapping(value= {"/ParentTask","/GetTaskList"})
	public List<TaskTO> getTaskList() {
		List<TaskTO> taskList=new ArrayList<TaskTO>();
		try {
			taskList.addAll(taskManager.getTaskList());
			return taskList;
		}catch (Exception e) {
			return taskList;
		}		
	}
	
	@PostMapping("/EndTask")
	public TaskTO endTask(@RequestBody TaskTO task){
		try {
			return taskManager.endTask(task.getTaskId());
		}catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/GetTaskById/{taskId}")
	public TaskTO getTask(@PathVariable("taskId") long taskId) {
		try {
			return taskManager.getTask(taskId);
		}catch (Exception e) {
			return null;
		}		
	}
	
	@PostMapping("/UpdateTask")
	public boolean updateTask(@RequestBody TaskTO task) {
		try {
			taskManager.updateTask(task);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
