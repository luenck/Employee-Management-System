package com.cognixia.jump.company;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

class EmployeeTest {
	
	private Connection conn = ConnectionManager.getConnection();
	private EmployeeDAO test = new EmployeeDAOImpl();
	
	private static final int TEST_EMPLOYEE_ID = 0;
	private static final String TEST_EMPLOYEE_NAME = "testName";
	private static final String TEST_EMPLOYEE_DEPT = "testDept";
	private static final String TEST_EMPLOYEE_NUMBER = "1234567890";
	private static final int TEST_EMPLOYEE_SALARY = 100000;
	
	
	@Test
	void testGetAllEmployees() throws SQLException {
		
		List<Employee> result = test.getAllEmployees();
		
		ResultSet rs = null;
		
		for (Employee employee : result) {
			try(PreparedStatement state = conn.prepareStatement("select * from employee where id_number = ?");
					){
				
				state.setInt(1, employee.getId());
				rs = state.executeQuery();
				
				if(rs.next()) {
					int id = rs.getInt("id_number");
					String name = rs.getString("name");
					String deptName = rs.getString("dept_name");
					String phone = rs.getString("phone_number");
					int salary = rs.getInt("salary");
					
					assertEquals(employee.getId(), id);
					assertEquals(employee.getName(), name);
					assertEquals(employee.getDepartmentName(), deptName);
					assertEquals(employee.getNumber(), phone);
					assertEquals(employee.getSalary(), salary);
				}
			}
		}
		
	}
	
	@Test
	void testAddEmployee() {
		Employee emp = new Employee(TEST_EMPLOYEE_ID, TEST_EMPLOYEE_NAME, TEST_EMPLOYEE_DEPT, TEST_EMPLOYEE_NUMBER, TEST_EMPLOYEE_SALARY);
		assertEquals(true, test.addEmployee(emp));
	}

}
