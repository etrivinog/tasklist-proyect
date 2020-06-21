/**
 * 
 */
package com.taskslistproject.taskslist.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.taskslistproject.taskslist.domain.Task;
import com.taskslistproject.taskslist.dto.TaskDTO;

/**
 * @author Esteban Triviño
 * This interface is used by the mapstruct library to map the class Task
 * into an equivalent class to be show as JSON
 * 
 * The declared methods within the interface are implemented by mapstruct
 */
@Mapper
public interface TaskMapper {

	/** This method converts an object of Task to an object of TaskDTO */
	@Mapping(source = "tasklist.tasklistId", target = "tasklist")
	@Mapping(source = "status.statusId", target = "status")
	@Mapping(source = "user.userId", target = "user")
	TaskDTO toTaskDTO(Task task);
	
	/** This method converts an object of TaskDTO to an object of Task */
	@Mapping(source = "tasklist", target = "tasklist.tasklistId")
	@Mapping(source = "status", target = "status.statusId")
	@Mapping(source = "user", target = "user.userId")
	Task toTask(TaskDTO taskDTO);
	
	/** This method converts an List of TaskDTO to an List of Task */
	List<Task> toTasks(List<TaskDTO> taskDTOs);
	
	/** This method converts an List of Task to an List of TaskDTO */
	List<TaskDTO> toTaskDTOs(List<Task> tasklists);
	
}
