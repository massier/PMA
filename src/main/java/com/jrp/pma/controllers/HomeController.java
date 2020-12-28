package com.jrp.pma.controllers;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;

@Controller
public class HomeController {
	//Autowired: auto dependency (field, constructor, setter) injection to Controller w/ existing bean instance (i.e. CRUD repo) from Spring context
	//Need to define instances (i.e. IProjectRepository & its primary keys) and EXTENDS CrudRepository, 
	//Or (if custom Class/Interface), add @Bean def. in app main OR ManufactureConfig class
	//Equiv. to: IProjectRepository proRep = new IProjectRepository(); AND implement ALL crud methods (add, del, getBy*)
	
	@Value("${version}") //user-defined in application.properties
	private String ver;
	
	//Field injections
	@Autowired
	IProjectRepository proRep;
	
	@Autowired
	IEmployeeRepository empRep;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
//		Want list of projects, findAll() return Iterable type. Overridden in IProjectRepository interface to return List
		List<Project> projects = proRep.findAll(); //Query via CRUD repo
		model.addAttribute("projectsList", projects); //pass to view in home.html
		
		List<ChartData> projectData = proRep.getProjectStatus(); //custom method to get custom query: project stage counts
//		convert projectData to JSON
		ObjectMapper objMapper = new ObjectMapper();
		String jsonString = objMapper.writeValueAsString(projectData);
		model.addAttribute("projectStatusCnt", jsonString);
		
		
//		List<Employee> employees = empRep.findAll();
		List<EmployeeProject> employeesProjectCnt = empRep.employeeProjects(); //custom method to get custom query-joined table
		model.addAttribute("employeesList", employeesProjectCnt);
		
		return "main/home";	
	}
}
