package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired //auto dependency injection w/ primary key 
	IProjectRepository proRep;
	
	@Autowired
	IEmployeeRepository empRep;
	
	
	@GetMapping("/")
	public String displayHome(Model model) {
//		Want list of projects, findAll() return Iterable type. 
//		Overridden in IProjectRepository interface to return List
		List<Project> projects = proRep.findAll(); //Query via CRUD repo
		model.addAttribute("projectsList", projects); //pass to view in home.html
		
		List<Employee> employees = empRep.findAll();
//		List<EmployeeProject> employees = empRep.employeeProjects(); //custom method to get joined table
		model.addAttribute("employeesList", employees);
		
		return "main/home";	
	}
}
