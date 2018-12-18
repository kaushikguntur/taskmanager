package com.taskmgr.to;

import java.io.Serializable;

public class TaskTO implements Serializable {

	private static final long serialVersionUID = -1752671927744885527L;

	private long taskId;

	private String taskName;

	private String startDate;

	private String endDate;

	private int priority;

	private long parentId;

	private String parentTask;

	private int isTaskEnded;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public int getIsTaskEnded() {
		return isTaskEnded;
	}

	public void setIsTaskEnded(int isTaskEnded) {
		this.isTaskEnded = isTaskEnded;
	}

}
