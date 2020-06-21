/**
 * 
 */
package com.taskslistproject.taskslist.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taskslistproject.taskslist.domain.Task;
import com.taskslistproject.taskslist.dto.TaskDTO;
import com.taskslistproject.taskslist.mapper.TaskMapper;
import com.taskslistproject.taskslist.service.TaskService;

/**
 * @author Esteban Triviño
 * Rest controller fro Task
 */
@RestController
@RequestMapping("/task/")
@CrossOrigin("*")
public class TaskController {
	
	/**
	 * Access to the instance of StatusService through the Autowired tag.
	 * This is possible thanks to Spring who uses the Factory Method Pattern.
	 */
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskMapper taskMapper;
	
	/**
	 * Receives a task and try to save it into the system
	 * @param tasklistDTO
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody TaskDTO taskDTO){
		
        try {
        	
        	//Converts the tasklistDTO to Task
        	Task task = taskMapper.toTask(taskDTO);
        	
        	//Save the task
        	task = taskService.save(task);
        	
        	//Converts the task to DTO
        	TaskDTO returnDTO = taskMapper.toTaskDTO(task);
        	
        	//Returns the object inserted with the assigned ID
			return  ResponseEntity.ok().body(returnDTO);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//Creates a ResponseMesaje object and returns a badRequest with it within
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("500", e.getMessage())
						);
		}
		
	}

    /**
	 * This method search an task with the indicated id.
	 * If the task exists, then retuns the task as a JSON,
	 * else, creates a ResponseMessage and returns it as a JSON.
	 * 
     * @param id
     * @return
     */
	@GetMapping("findById/{tasklistId}")
	public ResponseEntity<?> findById(@PathVariable("tasklistId") Integer id) {
		
		//Search the task by Id
		Optional<Task> taskOptional = taskService.findById(id);
		
		//If the searched task does not exist, returns a badRequest
		if(taskOptional.isPresent() == false) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", "The task with the ID: ["+id+"] does not exist.")
						);
		}
		
		//Converts the Task to TasklistDTO
		TaskDTO tasklistDTO = taskMapper.toTaskDTO(taskOptional.get());
		
		//Return the DTO
		return ResponseEntity.ok().body(tasklistDTO);
		
	}
	
	/**
	 * This method receive a task as JSON, converts it into a Task
	 * object and try to update the equivalent task into the system.
	 */
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody TaskDTO taskDTO){
		
		try {
			
			//Map the taskDTO to task
			Task task = taskMapper.toTask(taskDTO);
			
        	//Save the task
        	task = taskService.update(task);
        	
        	//Converts the task to DTO
        	TaskDTO returnDTO = taskMapper.toTaskDTO(task);
        	
        	//Returns the object inserted with the assigned ID
			return  ResponseEntity.ok().body(returnDTO);
			
		}catch (Exception e) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", e.getMessage())
						);		
		}
		
	}
	
	/**
	 * Receives the Id of a task and deletes the task.
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete/{taskId}")
	public ResponseEntity<?> deleteById(@PathVariable("taskId") Integer id) {
		
		try {
			
			taskService.deleteById(id);
			
			return ResponseEntity.ok().body(new ResponseMessage("200", "The task was deleted"));
			
		} catch (Exception e) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", e.getMessage())
						);		
		}
		
	}
	
	/**
	 * Returns a list with all the tasks in the system
	 * @return
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		
		//Gets the list with all the task registered
		List<Task> tasks = taskService.findAll();
		
		//Converts task to taskDTO
		List<TaskDTO> tasksDTO = taskMapper.toTaskDTOs(tasks);
		
		//Returns the DTO list
		return ResponseEntity.ok().body(tasksDTO);
		
	}
	
	/**
	 * Returns a list of task filtered by the received tasklistID.
	 */
	@GetMapping("findByTasklist/{tasklistId}")
	public ResponseEntity<?> findByTasklist(@PathVariable("tasklistId") Integer tasklistID){

		//Gets the list of tasks filtered by tasklist
		List<Task> tasks = taskService.findByTasklist(tasklistID);
		
		//Converts task to taskDTO
		List<TaskDTO> tasksDTO = taskMapper.toTaskDTOs(tasks);
		
		//Returns the DTO list
		return ResponseEntity.ok().body(tasksDTO);
		
	}
}
