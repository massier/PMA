package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired //allows Spring to auto inject empty instance to CRUD repo
	IProjectRepository proRep; //INTERFACE
	@Autowired
	IEmployeeRepository empRep;
	
	@GetMapping //using default "/employees"
	public String displayEmployees(Model model) {
		List<Employee> employees = empRep.findAll();
		model.addAttribute("employeesList", employees);
		return "employees/list-employees"; //html		
	}
	
	@GetMapping("/new")
	public String displayEmployeetForm(Model model) {
		Employee aEmployee = new Employee(); //leave attrs to the user via form, must have empty constructor
		
		//note: model needs to be defined under form display, NOT ONLY under processing. OW parse template err
		model.addAttribute("employee", aEmployee); // KV syntax: "html obj": java obj
		
		List<Project> projects = proRep.findAll();
		model.addAttribute("projectsList", projects); //KV syntax: "html obj": java obj
		
		return "employees/new-employee"; //html
	}
	
	@PostMapping("/save") //defined by action in html
	public String createEmployee(Employee employee, Model model) {
		//save to DB using employee CRUD repository
		empRep.save(employee);
		//use redirect to prevent duplicate submissions, ALWAYS use after saving data 
		return "redirect:/employees/"; //redirect need full path, to url NOT html
		
		
	}
}
