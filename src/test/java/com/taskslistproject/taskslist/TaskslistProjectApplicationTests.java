package com.taskslistproject.taskslist;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.taskslistproject.taskslist.domain.User;
import com.taskslistproject.taskslist.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Rollback(true)
class TaskslistProjectApplicationTests {
	
	@Autowired
	UserService userService;
	
	//Logger
	private final static Logger log=LoggerFactory.getLogger(User.class);
	
	@Test
	@Order(4)
	void D_userTestServiceFindById() {
		
		assertTrue(userService.findById(1).isPresent());
		
	}
	
	@Test
	@Order(3)
	void C_userTestServiceFindByEmail() {
		
		Optional<User> userOptional = userService.findByEmail("steven.trivinog@hotmail.com");
		assertTrue(userOptional.isPresent());
		
		log.info("User name: "+userOptional.get().getName());
		
		
	}

	@Test
	@Order(1)
	void A_userTestServiceSave() {
		
		User user = new User();
		
		user.setUserId(-1);
		user.setName("Esteban Rafael Trivino Guerra");
		user.setEmail("steven.trivinog@hotmail.com");
		user.setPassword("1234567");
		
		try {
			assertNotNull(userService.save(user));
		} catch (Exception e) {
			log.info("Error saving user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Order(2)
	void B_userTestServiceUpdate() {
		
		//Get the user with ID -1
		Optional<User> userOptional = userService.findById(1);
		User user = userOptional.get();
		
		log.info("User ID: "+user.getUserId());
		
		//Update the object password
		user.setPassword("7777777");
		
		//Update the user password
		try {
			
			user = userService.update(user);
			assertNotNull(user);
			
		} catch (Exception e) {
			log.info("Error updating user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
			
		}
		
		
	}

	@Test
	@Order(5)
	void E_userTestServicedelete() {
		
		//Get the user
		User user = userService.findByEmail("steven.trivinog@hotmail.com").get();
		
		//Delete the user
		try {
			userService.delete(user);
		} catch (Exception e) {
			log.info("Error deleting user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
			
		}
		
	}

	@Test
	@Order(6)
	void F_userTestServicedeleteById() {
		
		//Creates a user
		User user = new User();
		
		user.setName("Esteban Rafael Trivino Guerra 2");
		user.setEmail("steven.trivinog2@hotmail.com");
		user.setPassword("1234567");
		
		try {
			assertNotNull(userService.save(user));
		} catch (Exception e) {
			log.info("Error creating user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
		}
		
		log.info("User created. ID: "+user.getUserId());
		
		//Delete the user
		try {
			
			userService.deleteById(user.getUserId());
			
		} catch (Exception e) {
			log.info("Error deleting user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	@Order(7)
	void userTestServiceFindAll() {

		//Creates a user
		User user = new User();
		
		user.setName("Esteban Rafael Trivino Guerra 3");
		user.setEmail("steven.trivinog3@hotmail.com");
		user.setPassword("1234567");
		
		try {
			assertNotNull(userService.save(user));
		} catch (Exception e) {
			log.info("Error creating user "+user.getEmail()+": "+e.getMessage());
			e.printStackTrace();
		}
		
		List<User> users = userService.findAll();
		
		assertNotNull(users);
		
		try {
			userService.delete(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
