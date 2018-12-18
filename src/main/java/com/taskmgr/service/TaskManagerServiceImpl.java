package com.taskmgr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmgr.dao.ParentDAO;
import com.taskmgr.dao.TaskDAO;
import com.taskmgr.model.Parent;
import com.taskmgr.model.Task;
import com.taskmgr.to.TaskTO;
import com.taskmgr.util.DateUtils;

@Service
public class TaskManagerServiceImpl implements TaskManagerService{
	
	@Autowired
	private ParentDAO parentDAO;
	
	@Autowired
	private TaskDAO taskDAO;

	public void addTask(TaskTO taskTo) throws Exception {
		Task task;
		try {
			task=new Task();			
			if(0!=taskTo.getParentId()) {
				task.setParentIid(
						this.addParent(taskTo.getParentId()));				
			}			
			task.setTask(taskTo.getTaskName());
			task.setStartDate(DateUtils.stringToDate(taskTo.getStartDate()));
			task.setEndDate(DateUtils.stringToDate(taskTo.getEndDate()));
			task.setPriority(taskTo.getPriority());
			task.setStatus(0);
			taskDAO.save(task);
		}catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	private long addParent(long id) throws Exception{
		Parent parent = null;
		try {
			parent=new Parent();
			parent.setTask(taskDAO.findOne(id).getTask());
			return parentDAO.save(parent).getId();
		}catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<TaskTO> getTaskList() throws Exception {
		List<TaskTO> taskList=new ArrayList<TaskTO>();
		TaskTO to;
		Parent parent;
		try {
			for(Task task:taskDAO.findAll()) {
				 to=new TaskTO();
				 to.setTaskId(task.getId());
				 to.setTaskName(task.getTask());
				 to.setPriority(task.getPriority());
				 to.setIsTaskEnded(task.getStatus());
				 to.setStartDate(DateUtils.dateToString(task.getStartDate()));
				 to.setEndDate(DateUtils.dateToString(task.getEndDate()));
				  if(0!=task.getParentIid()) {
					 parent=task.getParent();
					 to.setParentId(parent.getId());
					 to.setParentTask(parent.getTask());
				 }				 
				 taskList.add(to);
			}
		}catch (Exception e) {
			throw new Exception(e);
		}		
		return taskList;
	}

	public TaskTO endTask(long taskId) throws Exception {
		Task task;
		TaskTO to=new TaskTO(); 
		try {
			task=taskDAO.findOne(taskId);
			task.setStatus(1);
			task=taskDAO.save(task);
			to.setIsTaskEnded(task.getStatus());
			to.setTaskId(task.getId());
		}catch (Exception e) {
			throw new Exception(e);
		}
		return to;
	}

	public TaskTO getTask(long taskId) throws Exception {
		Task task;
		TaskTO to=new TaskTO(); 
		Parent parent;
		try {
			task=taskDAO.findOne(taskId);
			to.setTaskId(task.getId());
			 to.setTaskName(task.getTask());
			 to.setPriority(task.getPriority());
			 to.setIsTaskEnded(task.getStatus());
			 to.setStartDate(DateUtils.dateToString(task.getStartDate()));
			 to.setEndDate(DateUtils.dateToString(task.getEndDate()));
			  if(0!=task.getParentIid()) {
				 parent=task.getParent();
				 to.setParentId(parent.getId());
				 to.setParentTask(parent.getTask());
			 }			
		}catch (Exception e) {
			throw new Exception(e);
		}
		return to;
	}

	public void updateTask(TaskTO taskTo) throws Exception {
		Task task;
		try {
			task=new Task();	
			task.setId(taskTo.getTaskId());
			if(0!=taskTo.getParentId()) {
				task.setParentIid(
						this.addParent(taskTo.getParentId()));				
			}			
			task.setTask(taskTo.getTaskName());
			task.setStartDate(DateUtils.stringToDate(taskTo.getStartDate()));
			task.setEndDate(DateUtils.stringToDate(taskTo.getEndDate()));
			task.setPriority(taskTo.getPriority());
			task.setStatus(0);
			taskDAO.save(task);
		}catch (Exception e) {
			throw new Exception(e);
		}
		
	}

}
