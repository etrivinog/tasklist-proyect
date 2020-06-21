package com.taskslistproject.taskslist.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {

	T save(T entity) throws Exception;
	T update(T entity) throws Exception;
	void delete(T entity) throws Exception;
	void deleteById(ID id) throws Exception;

	Optional<T> findById(ID id);
	List<T> findAll();

}
