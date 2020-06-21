/**
 * This class represents the database entity "Users"
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
 * This class represents the database entity "Users"
 * This class is used by JPA to access to the database
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable{
	
	/**
	 * Serial Version number
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@NotNull
	@Column(name="name")
	private String name;

	@Column(name="email")
	@NotNull
	private String email;

	@Column(name="pass")
	private String password;
	
	/*
	// Many to many relationship with Tasklists entity
	@ManyToMany
	@JoinTable(
	  name = "tasklists_has_users", 
	  joinColumns = @JoinColumn(name = "users_user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "takslists_tasklist_id"))
	private List<Tasklist> tasklists;
	*/
	
	//many-to-one association to Task
	@OneToMany(mappedBy="user")
	private List<Task> tasks;
	
	
	public User() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	public List<Tasklist> getTasklists() {
		return tasklists;
	}

	public void setTasklists(List<Tasklist> tasklists) {
		this.tasklists = tasklists;
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
