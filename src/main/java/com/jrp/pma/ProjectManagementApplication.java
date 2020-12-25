package com.jrp.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;

@SpringBootApplication
public class ProjectManagementApplication {
	
	@Autowired //allows Spring to auto inject empty instance to CRUD repo
	IProjectRepository proRep; //INTERFACE
	@Autowired
	IEmployeeRepository empRep;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
}
