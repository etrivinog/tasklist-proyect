/**
 * 
 */
package com.taskslistproject.taskslist.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * @author Esteban Triviño
 * This class represents the database entity "Tasks"
 * This class is used by JPA to access to the database
 */
@Entity
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable{
	
	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="task_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer taskId;
	
	@Column(name="description")
	@NotNull
	private String description;
	
	//Bi-directional many-to-one association to Tasklist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tasklist")
	private Tasklist tasklist;
	
	@Column(name="done")
	private String done;
	
	//Bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	@NotNull
	private User user;
	
	//Bi-directional many-to-one association to Status
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status")
	@NotNull
	private Status status;
	
	public Task() {
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tasklist getTasklist() {
		return tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
