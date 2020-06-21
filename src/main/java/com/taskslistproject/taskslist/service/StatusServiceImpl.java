/**
 * 
 */
package com.taskslistproject.taskslist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskslistproject.taskslist.domain.Status;
import com.taskslistproject.taskslist.repository.StatusRepository;

/**
 * @author Esteban Triviño
 * This contains has the business logic to manage Status
 * 
 * The implementation of this class uses the Singleton design pattern
 * which is auto-implemented by Spring Data JPA by default due to the
 * use of the "@Service" tag.
 * 
 */
@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusRepository statusRepository;
	
	@Override
	public Status save(Status entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status update(Status entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Status entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Status> findById(Integer id) {
		return statusRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Status> findAll() {
		return statusRepository.findAll();
	}

}
