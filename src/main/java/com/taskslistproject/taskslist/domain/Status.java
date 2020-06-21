/**
 * 
 */
package com.taskslistproject.taskslist.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author Esteban Triviño
 *This class represents the database entity "Status"
 * This class is used by JPA to access to the database
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable{

	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer statusId;
	
	@Column(name="description")
	@NotNull
	private String description;
	
	//bi-directional many-to-one association to Taks
	@OneToMany(mappedBy="status")
	private List<Task> tasks;
	
	public Status() {
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
