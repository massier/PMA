package com.jrp.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO) //auto sequencing, only works if thru java, not working for sql seeding
	@GeneratedValue(strategy=GenerationType.IDENTITY) //H rely on DB sequencing, thus works for both java & sql. Slow for batch
	private long employeeId;
	
	private String firstname;
	private String lastname;
	private String email;
	
	//Hibernate: assign 1 proj -> many employees, across entities foreign key=project
	//cascading: how changes in parent (proj) affect children (emp) - DEFINED ON CHILDREN SIDE ONLY
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
//			fetch = FetchType.LAZY) //lazy usually suffice & okay for perf
//	@JoinColumn(name="project_id") //many-one: new column name in Employee table to store mapping
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY) //params be defined on BOTH sides
//foreign key for THIS table: joinColumns | foreign key for the OTHER TABLE: inverseJoinColumns
	@JoinTable(name="project_employee", 
				joinColumns=@JoinColumn(name="employee_id"),
				inverseJoinColumns=@JoinColumn(name="project_id")) //intermediary table for many-many, define for BOTH
	private List<Project> projects; //IF OneToMany: MUST match OneToMany(mappedBy="project") in Project entity. many-many is based on intermediary table so  no impact
	
	public Employee() {}
	
	public Employee(String firstname, String lastname, String email) {
		super();
//		this.employeeId = employeeId; //Hibernate DB to handle
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	//source - generate getters and setters
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
