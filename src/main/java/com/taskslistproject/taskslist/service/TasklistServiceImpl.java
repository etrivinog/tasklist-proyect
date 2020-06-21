package com.taskslistproject.taskslist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taskslistproject.taskslist.domain.Tasklist;
import com.taskslistproject.taskslist.repository.TasklistRepository;

/**
 * @author Esteban Triviño
 * This contains has the business logic to manage Tasklists
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is auto-implemented by Spring Data JPA by default due to the
 * use of the "@Service" tag.
 *  
 */
@Service
public class TasklistServiceImpl implements TasklistService {
	
	@Autowired
	TasklistRepository tasklistRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tasklist save(Tasklist entity) throws Exception {
		
		//Verify if there are a tasklist with the same name
		if (tasklistRepository.findByName(entity.getName()).isPresent() == true) {
			
			throw new Exception("There are already an takslist with the name [" + entity.getName() + "].");
			
			
			
		}else if (	entity.getTasklistId() != null &&
				  	tasklistRepository.findById(entity.getTasklistId()).isPresent()) {
			/*
			 * If there are a tasklist with the same ID
			 */
			
			//Throws an exception indicating that there are an user with the same ID
			throw new Exception("There are already an takslist with the ID [" + entity.getTasklistId() + "].");
			
		}
		
		return tasklistRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tasklist update(Tasklist entity) throws Exception {
		
		//Verify if the tasklist does exist
		if (tasklistRepository.findById(entity.getTasklistId()).isPresent() == false) {
			//If the tasklist does not exist
			throw new Exception("The tasklist with ID [" + entity.getTasklistId() + "] does not exist!");
		}
		
		return tasklistRepository.save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tasklist entity) throws Exception {
		
		//Verify if the tasklist exists
		if (tasklistRepository.findById(entity.getTasklistId()).isPresent() == false) {
			//If the tasklist does not exist
			throw new Exception("The user with ID [" + entity.getTasklistId() + "] does not exist!");
		}
		
		//Get tasklist to open session (this is because of LAZY LOAD)
		entity = tasklistRepository.findById(entity.getTasklistId()).get();
		
		//Verify if the tasklist has tasks
		if(entity.getTasks().size() > 0) {
			throw new Exception("The tasklist has tasks and cannot be deleted!");
		}
		
		tasklistRepository.delete(entity);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		
		//Verify either if the id is valid or that the user tasklist does not exist
		if(id == null || id < 1) {
			throw new Exception("The indicated ID is not valid.");
		}else if(!tasklistRepository.findById(id).isPresent()) {
			//If user does not exist.
			throw new Exception("The tasklist with ID ["+id+"] does not exist.");
		}
		
		tasklistRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tasklist> findById(Integer id) {
		return tasklistRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tasklist> findAll() {
		return tasklistRepository.findAll();
	}

}
