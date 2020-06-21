/**
 * 
 */
package com.taskslistproject.taskslist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskslistproject.taskslist.domain.Tasklist;

/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to auto-generate the 
 * implemantation of the repository
 * 
 * The implementation of this interface uses the Singleton design pattern
 * which is implemented by Spring Data JPA by default.
 * 
 */
public interface TasklistRepository extends JpaRepository<Tasklist, Integer> {
	
	//Method auto-implemented by Spring Data JPA to find a user by email.
	Optional<Tasklist> findByName(String description);
	
}
