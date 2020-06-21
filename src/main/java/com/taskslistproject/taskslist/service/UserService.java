/**
 * 
 */
package com.taskslistproject.taskslist.service;

import java.util.Optional;

import com.taskslistproject.taskslist.domain.User;

/**
 * @author Esteban Triviño
 *
 */
public interface UserService extends GenericService<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
}
