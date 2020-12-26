package com.jrp.pma.dto;

public interface EmployeeProject {
	//Must begin with get so Spring knows to populate from table
	//get* need to match exactly as SQL, defined in IEmployeeRepository
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();

}
