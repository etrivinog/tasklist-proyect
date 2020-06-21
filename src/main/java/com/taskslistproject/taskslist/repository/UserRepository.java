package com.taskslistproject.taskslist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskslistproject.taskslist.domain.User;
/**
 * @author Esteban Triviño
 * This interface is used by Spring Data JPA to auto-generate the 
 * implemantation of the repository.
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is implemented by Spring Data JPA by default.
 * 
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//Method auto-implemented by Spring Data JPA to find a user by email.
	Optional<User> findByEmail(String email);
	
}
