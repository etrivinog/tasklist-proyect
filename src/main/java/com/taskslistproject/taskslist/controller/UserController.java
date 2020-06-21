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

import com.taskslistproject.taskslist.domain.User;
import com.taskslistproject.taskslist.dto.UserDTO;
import com.taskslistproject.taskslist.mapper.UserMapper;
import com.taskslistproject.taskslist.service.UserService;


/**
 * @author Esteban Triviño
 *
 */
@RestController
@RequestMapping("/user/")
@CrossOrigin("*")
public class UserController {
	
	/**
	 * Access to the instance of UserService through the Autowired tag.
	 * This is possible thanks to Spring who uses the Factory Method Pattern.
	 */
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	/***
	 * Save the received user
	 * @param userDTO
	 * @return
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO){
		/*
		 * This method receives a User as a JSON and try to save it
		 * into the database.
		 */
		
        try {
        	//Converts the UserDTO to User
        	User user = userMapper.toUser(userDTO);
        	
        	//Save the user
        	user = userService.save(user);
        	
        	//Converts the tasklist to DTO
        	UserDTO returnDTO = userMapper.toUserDTO(user);
        	
        	//Returns the object inserted with the assigned ID
			return  ResponseEntity.ok().body(returnDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("500", e.getMessage())
						);
		}
	}
          
	/**
	 * This method search an user list with the indicated id.
	 * If the user exists, then retuns the user as a JSON,
	 * else, creates a ResponseMessage and returns it as a JSON.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("findById/{userId}")
	public ResponseEntity<?> findById(@PathVariable("userId") Integer id) {
		
		//Search the user
		Optional<User> userOptional = userService.findById(id);
		
		//If the searched tasklist does not exist, returns a badRequest
		if(userOptional.isPresent() == false) {
			return ResponseEntity.badRequest().body(new ResponseMessage("400", "There are any user with ID: ["+id+"]"));
		}

		//Converts the Tasklist to TasklistDTO
		UserDTO userDTO = userMapper.toUserDTO(userOptional.get());
		
		//Return the DTO
		return ResponseEntity.ok().body(userDTO);
		
		
	}
	
	/** 
	 * This method receive a tasklist as JSON, converts it into a Tasklist
	 * object and try to update the equivalent tasklist into the system.
	 *
	 * @param user
	 * @return
	 */
	@PutMapping("update")
	public ResponseEntity<?> update(@Valid @RequestBody UserDTO userDTO){
		
		try {
			
			//Converts the userDTO to user
			User user = userMapper.toUser(userDTO);
			
        	//Update the user
        	user = userService.update(user);
        	
        	//Converts the user to DTO
        	UserDTO returnDTO = userMapper.toUserDTO(user);
        	
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
	
	/***
	 * Deletes the user with the indicated Id 
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<?> deleteById(@PathVariable("userId") Integer id) {
		
		try {
			
			userService.deleteById(id);
			
			return ResponseEntity.ok().body(new ResponseMessage("200", "The user was deleted"));
			
		} catch (Exception e) {
			return ResponseEntity.
					badRequest().
					body(
							new ResponseMessage("400", e.getMessage())
						);		
		}
		
	}
	
	/***
	 * Returns a list with all the users
	 * @return
	 */
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		
		//Gets the list with all the users registered
		List<User> users = userService.findAll();
		
		//Converts users list to usersDTO list
		List<UserDTO> usersDTO = userMapper.toUserDTOs(users);
		
		//Returns the DTO list
		return ResponseEntity.ok().body(usersDTO);
		
	}
	
}
