package com.taskmgr.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class Parent implements Serializable{
	
	private static final long serialVersionUID = -4538725260433105142L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="parent_id")
	private long id;
	
	@Column(name="parent_task")
	private String task;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

}
