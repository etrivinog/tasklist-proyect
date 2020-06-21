package com.taskslistproject.taskslist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taskslistproject.taskslist.domain.User;
import com.taskslistproject.taskslist.repository.UserRepository;

/**
 * @author Esteban Triviño
 * This class contains the business logic to manage users
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is auto-implemented by Spring Data JPA by default due to the
 * use of the "@Service" tag.
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User save(User entity) throws Exception {
		
		//Verify if there are a client with the same email
		if (userRepository.findByEmail(entity.getEmail()).isPresent() == true) {
			
			throw new Exception("There are already an user with the email [" + entity.getEmail() + "].");
			
		}else if (	entity.getUserId() != null &&
			  		userRepository.findById(entity.getUserId()).isPresent() == true) {
			
			/*
			 * If there are a client with the same ID
			 */
			
			//Throws an exception indicating that there are an user with the same ID
			throw new Exception("There are already an user with the ID [" + entity.getUserId() + "].");
		}
		
		return userRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public User update(User entity) throws Exception {
		
		//Verify if the user does exist
		if (userRepository.findById(entity.getUserId()).isPresent() == false) {
			//If the user does not exist
			throw new Exception("The user with ID [" + entity.getUserId() + "] does not exist!");
		}
		
		return userRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(User entity) throws Exception {
		
		//Verify if the user exists
		if (userRepository.findById(entity.getUserId()).isPresent() == false) {
			//If the user does not exist
			throw new Exception("The user with ID [" + entity.getUserId() + "] does not exist!");
		}
		
		//Get user to open session
		entity = userRepository.findById(entity.getUserId()).get();
		
		//Verify if the user has assigned tasks
		if(entity.getTasks().size() > 0) {
			throw new Exception("The user has assigned tasks and cannot be deleted!");
		}
		
		userRepository.delete(entity);
		
	}
	
	@Override
	public void deleteById(Integer id) throws Exception {
		
		//Verify is the id is valid and that the user does exist
		if(id == null || id < 1) {
			throw new Exception("The indicated ID is not valid.");
		}else if(!userRepository.findById(id).isPresent()) {
			//If user does not exist.
			throw new Exception("The user with ID ["+id+"] does not exist.");
		}
		
		userRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	//Find an user by email
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
