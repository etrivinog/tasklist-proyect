package com.taskslistproject.taskslist.service;

import java.util.List;

import com.taskslistproject.taskslist.domain.Task;
/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to apply the Factory Method
 * Design Pattern.
 * 
 */
public interface TaskService extends GenericService<Task, Integer> {

	/***
	 * Returns a list of tasks filtered by tasklist.
	 * @return
	 * @throws Exception 
	 */
	List<Task> findByTasklist(Integer tasklist);
	
}
