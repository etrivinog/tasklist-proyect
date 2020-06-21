/**
 * 
 */
package com.taskslistproject.taskslist.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.taskslistproject.taskslist.domain.Tasklist;
import com.taskslistproject.taskslist.dto.TasklistDTO;

/**
 * @author Esteban Triviño
 * This interface is used by the mapstruct library to map the class Tasklist
 * into an equivalent class to be show as JSON
 * 
 * The declared methods within the interface are implemented by mapstruct
 */
@Mapper
public interface TasklistMapper {
	
	/** This method converts an object of Tasklist to an object of TasklistDTO */
	TasklistDTO toTasklistDTO(Tasklist tasklist);
	
	/** This method converts an object of TasklistDTO to an object of Tasklist */
	Tasklist toTasklist(TasklistDTO tasklistDTO);
	
	/** This method converts an List of TasklistDTO to an List of Tasklist */
	List<Tasklist> toTasklist(List<TasklistDTO> tasklistsDTOs);
	
	/** This method converts an List of Tasklist to an List of TasklistDTO */
	List<TasklistDTO> toTasklistDTOs(List<Tasklist> tasklists);
	
}
