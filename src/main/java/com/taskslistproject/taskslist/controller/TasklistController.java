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

import com.taskslistproject.taskslist.domain.Tasklist;
import com.taskslistproject.taskslist.dto.TasklistDTO;
import com.taskslistproject.taskslist.mapper.TasklistMapper;
import com.taskslistproject.taskslist.service.TasklistService;

/**
 * @author Esteban Triviño
 * Rest controller for Tasklist
 */
@RestController
@RequestMapping("/tasklist/")
@CrossOrigin("*")
public class TasklistController {
	
	/**
	 * Access to the instance of TasklistService through the Autowired tag.
	 * This is possible thanks to Spring who uses the Factory Method Pattern.
	 */
	@Autowired
	TasklistService tasklistService;
	
	@Autowired
	TasklistMapper tasklistMapper;
	
	/**
	 * This method receives a Tasklist and try to save it into the system.
	 * @param tasklistDTO
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody TasklistDTO tasklistDTO){
		
        try {
        	
        	//Converts the tasklistDTO to Tasklist
        	Tasklist tasklist = tasklistMapper.toTasklist(tasklistDTO);
        	
        	//Save the tasklist
        	tasklist = tasklistService.save(tasklist);
        	
        	//Converts the tasklist to DTO
        	TasklistDTO returnDTO = tasklistMapper.toTasklistDTO(tasklist);
        	
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
	 * This method search an tasklist with the indicated id.
	 * If the tasklist exists, then retuns the takslist as a JSON,
	 * else, creates a ResponseMessage and returns it as a JSON.
	 * 
     * @param id
     * @return
     */
	@GetMapping("findById/{tasklistId}")
	public ResponseEntity<?> findById(@PathVariable("tasklistId") Integer id) {
		
		//Search the takslist by Id
		Optional<Tasklist> tasklistOptional = tasklistService.findById(id);
		
		//If the searched tasklist does not exist, returns a badRequest
		if(tasklistOptional.isPresent() == false) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", "Does not exist a takslist with the ID: ["+id+"]")
						);
		}
		
		//Converts the Tasklist to TasklistDTO
		TasklistDTO tasklistDTO = tasklistMapper.toTasklistDTO(tasklistOptional.get());
		
		//Return the DTO
		return ResponseEntity.ok().body(tasklistDTO);
		
	}
	
	
	/**
	 * This method receive a tasklist as JSON, converts it into a Tasklist
	 * object and try to update the equivalent tasklist into the system.
	 */
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody TasklistDTO tasklistDTO){
		
		try {
			
			Tasklist tasklist = tasklistMapper.toTasklist(tasklistDTO);
			
        	//Save the tasklist
        	tasklist = tasklistService.update(tasklist);
        	
        	//Converts the tasklist to DTO
        	TasklistDTO returnDTO = tasklistMapper.toTasklistDTO(tasklist);
        	
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
	
	@DeleteMapping("delete/{tasklistId}")
	public ResponseEntity<?> deleteById(@PathVariable("tasklistId") Integer id) {
		
		try {
			
			tasklistService.deleteById(id);
			
			return ResponseEntity.ok().body(new ResponseMessage("200", "The tasklist was deleted"));
			
		} catch (Exception e) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", e.getMessage())
						);		
		}
		
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		
		//Gets the list with all the tasklists registered
		List<Tasklist> tasklists = tasklistService.findAll();
		
		//Converts tasklist to tasklistDTO
		List<TasklistDTO> tasklistsDTO = tasklistMapper.toTasklistDTOs(tasklists);
		
		//Returns the DTO list
		return ResponseEntity.ok().body(tasklistsDTO);
		
	}
	
}
