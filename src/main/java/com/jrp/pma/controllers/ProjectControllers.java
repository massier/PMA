package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectControllers {
	
	@Autowired //allows Spring to auto inject empty instance to CRUD repo
	IProjectRepository proRep; //INTERFACE
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping //using default "/projects"
	public String displayProjects(Model model) {
		List<Project> projects = proRep.findAll();
		model.addAttribute("projectsList", projects);
		return "projects/list-projects"; //html		
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project(); //leave attrs to the user via form, must have empty constructor
		
		//note: model needs to be defined under form display, NOT ONLY under processing. OW parse template err
		model.addAttribute("project", aProject); // KV syntax: "html obj": java obj
		
		List<Employee> employees = empRep.findAll();
		model.addAttribute("employeesList", employees); //KV syntax: "html obj": java obj
		
		return "projects/new-project"; //html file name NOT url	
	}
	
	@PostMapping("/save") //defined by action in html
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) {
		//save to DB using project CRUD repository
		proRep.save(project);
		
//	Only needed for many-one, no need for many-many	(no need for manual foreign key)
//		Iterable<Employee> chosenEmployees = empRep.findAllById(employees);
//		for (Employee emp : chosenEmployees) {
//			emp.setProject(project); //Hibernate will set foreign key
//			empRep.save(emp);
//		}
		
		//use redirect to prevent duplicate submissions, ALWAYS use after saving data 
		return "redirect:/projects/"; //redirect need full path, to url NOT html file
		
		
	}
	
}
