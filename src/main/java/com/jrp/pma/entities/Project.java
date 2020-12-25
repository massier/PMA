package com.jrp.pma.entities;

import java.util.ArrayList;
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

@Entity //Entity: for Hibernate to pick up and creates entity table  via jpa; can also show sql in app.properties
public class Project {
	
	@Id //primary key of each entity
//	@GeneratedValue(strategy=GenerationType.AUTO) //auto sequencing, only works if thru java, not working for sql seeding
	@GeneratedValue(strategy=GenerationType.IDENTITY) //H rely on DB sequencing, thus works for both java & sql. Slow for batch
	private long projectId; //unique identifier
	private String name;
	private String stage; //i.e. NEW, INPROGRESS, COMPLETED
	private String description;
	
	//Hibernate creates tables and handles join 
//	@OneToMany(mappedBy="project") //mappedBy MUST match ManyToOne property (in Employee entity)
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY) //params be defined on BOTH sides
	//foreign key for THIS table: joinColumns | foreign key for the OTHER TABLE: inverseJoinColumns
	@JoinTable(name="project_employee", 
				joinColumns=@JoinColumn(name="project_id"),
				inverseJoinColumns=@JoinColumn(name="employee_id")) //intermediary table for many-many, define for BOTH
	private List<Employee> employees; //assign 1 proj -> many employees, across entities foreign key=project
	
	
	public Project() {} //in case need to create instance w/o setting values
	
	public Project(String name, String stage, String description) {
		super();
//		this.projectId = projectId; //outsourcing responsibility to DB
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	//source - generate getters and setters
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	//Allow adding one employee to self.employees to enable DB manual seeding
	public void addEmployee(Employee employee) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(employee); //arrayList method
	}
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	

}
