package com.cognixia.jump.company;

import java.util.List;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
	
	public boolean addEmployee(Employee employee);
	
	public boolean deleteEmployeebyId(int employeeId);
	
	public boolean updateEmployee(Employee employee);
	
	public boolean doesEmployeeExist(String name);

}
