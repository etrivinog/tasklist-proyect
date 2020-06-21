/**
 * 
 */
package com.taskslistproject.taskslist.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.taskslistproject.taskslist.domain.Status;
import com.taskslistproject.taskslist.dto.StatusDTO;

/**
 * @author Esteban Triviño
 * This interface is used by the mapstruct library to map the class Status
 * into an equivalent class to be show as JSON
 * 
 * The declared methods within the interface are implemented by mapstruct
 */
@Mapper
public interface StatusMapper {
	
	/** This method converts an object of Status to an object of StatusDTO */
	StatusDTO toStatusDTO(Status status);
	
	/** This method converts an object of StatusDTO to an object of Status */
	Status toStatus(StatusDTO statusDTO);
	
	/** This method converts an List of StatusDTO to an List of Status */
	List<Status> toClientes(List<StatusDTO> clientesDTOs);
	
	/** This method converts an List of Status to an List of StatusDTO */
	List<StatusDTO> toClienteDTOs(List<Status> clientes);
	
}
