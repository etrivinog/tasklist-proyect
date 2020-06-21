/**
 * 
 */
package com.taskslistproject.taskslist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskslistproject.taskslist.domain.Task;

/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to apply the Factory Method
 * Design Pattern.
 * 
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	/***
	 * Method auto-implemented by Spring Data JPA to return a list of tasks
	 * filtered by tasklist.
	 * @return
	 */
	List<Task> findByTasklist(Integer tasklist);
	
}
