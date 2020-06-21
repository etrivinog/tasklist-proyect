/**
 * 
 */
package com.taskslistproject.taskslist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.taskslistproject.taskslist.domain.Task;
import com.taskslistproject.taskslist.repository.TaskRepository;

/**
 * @author Esteban Triviño
 * This contains has the business logic to manage Task
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is auto-implemented by Spring Data JPA by default due to the
 * use of the "@Service" tag.
 *  
 */
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TasklistService tasklistService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	UserService userService;
	
	
	/***
	 * Receives a task and validates its values, if everything is OK, save the task
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Task save(Task entity) throws Exception {
		
		//If there are a task with the same ID
		if (entity.getTaskId() != null &&
			taskRepository.findById(entity.getTaskId()).isPresent()) {
			
			//Throws an exception indicating that there are an user with the same ID
			throw new Exception("There are already an taks with the ID [" + entity.getTaskId() + "].");
			
		}else if(!entity.getDone().equals("N") && !entity.getDone().equals("Y")) {
			//Verify constraint in field "done".

			//Throws an exception indicating that the "done" field must be "N" or "Y"
			throw new Exception("The field 'Done' must be 'N' or 'Y'.");
			
		}else 
			//Verifies if the indicated tasklist does exist.
			if( ! tasklistService.findById(
					entity.getTasklist().getTasklistId()).isPresent()) {
			
			//Throws an exception indicating that the tasklist does not exist.
			throw new Exception("The tasklist ["+entity.getTasklist().getTasklistId()+"] "
									+ "does not exist");
			
		}else 
			//Verifies if the indicated status does exist
			if(! statusService.findById(entity.getStatus().getStatusId()).isPresent()) {
			
			//Throws an exception indicating that the status does not exist.
			throw new Exception("The status ["+entity.getStatus().getStatusId()+"] "
					+ "does not exist");
			
		}else 
			//If the user is not null and does not exist
			if(	entity.getUser().getUserId() != null &&
					! userService.findById(entity.getUser().getUserId()).isPresent()) {
						
			//Throws an exception indicating that the status does not exist.
			throw new Exception("The user ["+entity.getUser().getUserId()+"] "
					+ "does not exist");
			
			
		}
		
		return taskRepository.save(entity);
	}
	
	/**
	 * Receives a task and update the equivalent task into the system
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Task update(Task entity) throws Exception {

		//Verify if the task does exist
		if (taskRepository.findById(entity.getTaskId()).isPresent() == false) {
			//If the task does not exist
			throw new Exception("The task with ID [" + entity.getTaskId() + "] does not exist!");
		}
		
		return taskRepository.save(entity);
	}
	
	/***
	 * Receives a task and delete that task from the system
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Task entity) throws Exception {

		//Verify if the task exists
		if (taskRepository.findById(entity.getTaskId()).isPresent() == false) {
			//If the task does not exist
			throw new Exception("The user with ID [" + entity.getTaskId() + "] does not exist!");
		}
		
		//Get tasklist to open session (this is because of LAZY LOAD)
		entity = taskRepository.findById(entity.getTaskId()).get();
		
		taskRepository.delete(entity);
	}
	
	/***
	 * Receives a taskId and delete the task with the indicated ID.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {

		//Verify either if the id is valid or that the user task does not exist
		if(id == null || id < 1) {
			throw new Exception("The indicated ID is not valid.");
		}else if(!taskRepository.findById(id).isPresent()) {
			//If user does not exist.
			throw new Exception("The task with ID ["+id+"] does not exist.");
		}
		
		taskRepository.deleteById(id);
		

	}
	
	/***
	 * Receives a taskId and return the task with the indicated Id.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Task> findById(Integer id) {
		return taskRepository.findById(id);
	}
	
	/***
	 * Returns a list with all the task in the system
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Task> findAll() {
		return taskRepository.findAll();
	}
	
	/**
	 * Returns a list of task filtered by the received tasklistID.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Task> findByTasklist(Integer tasklistID){
		return taskRepository.findByTasklist(tasklistID);
	}

}
