/**
 * This class represents the database entity "Tasklists"
 * This class is used by JPA to access to the database
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
 * This class represents the database entity "Tasklists"
 * This class is used by JPA to access to the database
 */
@Entity
@NamedQuery(name="Tasklist.findAll", query="SELECT t FROM Tasklist t")
public class Tasklist implements Serializable{

	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="tasklist_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer tasklistId;
	
	@Column(name="description")
	@NotNull
	private String name;
	
	/*
	// Many-to-many association with Users entity
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "tasklists")
	private List<User> users;
	*/
	
	//One-to-many association with Task
	@OneToMany(mappedBy = "tasklist")
	private List<Task> tasks;
	
	public Tasklist() {
	}

	public Integer getTasklistId() {
		return tasklistId;
	}

	public void setTasklistId(Integer tasklistId) {
		this.tasklistId = tasklistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	*/
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
