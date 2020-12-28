package com.jrp.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrp.pma.dao.IEmployeeRepository;
import com.jrp.pma.dao.IProjectRepository;

//Dependency injection (B -> A):
//Scanning starts from main (& only within that scope, unless scanBasePackages=...) and add annotated classes(A) to Spring context
//@Service, @Repository, @Controller, etc. based on (A)function, if not clear use @Component for catch-all
//W/O annotation (which adds to Spring context), a class(A) won't be instantiated
//Types of injection (field, constructor, setter injections) depends on (A) (Controller class -> needs fields)
//(B) is either existing Spring inst. (or extends/inherits it) OR custom (need to anno. w/ @Bean) 

@SpringBootApplication(scanBasePackages= {"com.jrp.pma", "com.jrp.utils"}) //If specifying scan path, must include main as well
public class ProjectManagementApplication {
	
	//@Bean definition required for custom classes/interfaces if using @Autowired 
	//public Class newInst() {}
	//Need: (1)Class/Interface Declarations (2)@Bean def. here (3)@Autowired in Controllers, etc.
	@Autowired //allows Spring to auto inject empty instance to CRUD repo
	IProjectRepository proRep; //INTERFACE
	@Autowired
	IEmployeeRepository empRep;

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
}
