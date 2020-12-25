package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

//CrudRepository<class of primary id, type of primary id> primary id = employeeId
//must have @Autowired in controller to inject instance
public interface IEmployeeRepository extends CrudRepository<Employee, Long> { 
	@Override
	public List<Employee> findAll();
	
//	Copying from SQL: replace all /n with space, '+' are okay
	@Query(nativeQuery=true, value="SELECT e.firstname as firstname, e.lastname as lastname, COUNT(pe.project_id) as projectCount "
			+ "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.firstname, e.lastname ORDER by 3 DESC")
	public List<EmployeeProject> employeeProjects();
//		returns a data transfer object (DTO), create an interface to capture that -> dto/EmployeeProject

}
