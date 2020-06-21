/**
 * 
 */
package com.taskslistproject.taskslist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskslistproject.taskslist.domain.Task;

/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to auto-generate the 
 * implemantation of the repository
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is implemented by Spring Data JPA by default.
 * 
 */
public interface TaksRepository extends JpaRepository<Task, Integer> {

}
