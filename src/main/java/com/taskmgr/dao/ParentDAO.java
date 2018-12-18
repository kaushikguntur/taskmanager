package com.taskmgr.dao;

import org.springframework.data.repository.CrudRepository;

import com.taskmgr.model.Parent;


public interface ParentDAO extends CrudRepository<Parent,Long>{

}
