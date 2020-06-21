package com.taskslistproject.taskslist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskslistproject.taskslist.domain.Status;
import com.taskslistproject.taskslist.dto.StatusDTO;
import com.taskslistproject.taskslist.mapper.StatusMapper;
import com.taskslistproject.taskslist.service.StatusService;

/**
 * @author Esteban Triviño
 * 
 */
@RestController
@RequestMapping("/status/")
@CrossOrigin("*")
public class StatusController {
	
	/**
	 * Access to the instance of StatusService through the Autowired tag.
	 * This is possible thanks to Spring who uses the Factory Method Pattern.
	 */
	@Autowired
	StatusService statusService;
	
	/**
	 * Access to the instance of StatusMapper through the Autowired tag.
	 * This is possible thanks to Spring who uses the Factory Method Pattern. 
	 */
	@Autowired
	StatusMapper statusMapper;
    
	@GetMapping("findById/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") Integer id) {
		/*
		 * This method search an status list with the indicated id.
		 * If the user exists, then retuns the user as a JSON,
		 * else, creates a ResponseMessage and returns it as a JSON.
		 */
		
		Optional<Status> userOptional = statusService.findById(id);
		
		if(userOptional.isPresent() == false) {
			return ResponseEntity.badRequest().body(new ResponseMessage("400", "There are status user with ID: ["+id+"]"));
		}
		
		return ResponseEntity.ok().body(userOptional.get());
		
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		
		//Receives the statuses list
		List<Status> statuses = statusService.findAll();
		
		//Converts the list to a statusesDTO list
		List<StatusDTO> statusesDTO = statusMapper.toClienteDTOs(statuses);
		
		//Returns the list
		return ResponseEntity.ok().body(statusesDTO);
		
	}
	
}
