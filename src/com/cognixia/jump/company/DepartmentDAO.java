package com.cognixia.jump.company;

import java.util.List;

public interface DepartmentDAO {

	public List<Department> getAllDepartments();
	
	public Department getDepartmentByName(String deptName) throws DepartmentNotFoundException;
	
	public boolean addDepartment(Department dept);
	
	public boolean deleteDepartmentByName(String deptName);
	
	public boolean updateDepartment(Department dept);
	
	public void removeEmployees(String name);

}
