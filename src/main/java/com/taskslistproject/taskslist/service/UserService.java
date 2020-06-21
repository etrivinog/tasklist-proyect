/**
 * 
 */
package com.taskslistproject.taskslist.service;

import java.util.Optional;

import com.taskslistproject.taskslist.domain.User;

/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to auto-generate the 
 * implemantation of the service
 * 
 * The implementation of this interface uses the Singleton design pattern
 * which is implemented by Spring Data JPA by default.
 * 
 */
public interface UserService extends GenericService<User, Integer> {
	
	/***
	 * Method auto-implemented by Spring Data JPA to find a user by email.
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
}
