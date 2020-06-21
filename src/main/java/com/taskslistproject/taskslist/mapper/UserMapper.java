package com.taskslistproject.taskslist.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.taskslistproject.taskslist.domain.User;
import com.taskslistproject.taskslist.dto.UserDTO;

/**
 * @author Esteban Triviño
 * This interface is used by the mapstruct library to map the class Status
 * into an equivalent class to be show as JSON
 * 
 * The declared methods within the interface are implemented by mapstruct
 */
@Mapper
public interface UserMapper {

	/** This method converts an object of User to an object of UserDTO */
	UserDTO toUserDTO(User tasklist);
	
	/** This method converts an object of UserDTO to an object of User */
	User toUser(UserDTO userDTO);
	
	/** This method converts an List of UserDTO to an List of User */
	List<User> toUser(List<UserDTO> usersDTOs);
	
	/** This method converts an List of User to an List of UserDTO */
	List<UserDTO> toUserDTOs(List<User> users);
	
}
