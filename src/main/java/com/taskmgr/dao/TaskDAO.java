package com.taskmgr.dao;

import org.springframework.data.repository.CrudRepository;

import com.taskmgr.model.Task;

public interface TaskDAO extends CrudRepository<Task,Long>{

}
