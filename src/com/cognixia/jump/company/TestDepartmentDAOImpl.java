package com.cognixia.jump.company;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestDepartmentDAOImpl {

	private DepartmentDAOImpl departmentDAOImpl = new DepartmentDAOImpl();
	
	private static final String TEST_DEPARTMENT_NAME = "testDepartment";
	private static final String TEST_DEPARTMENT_PHONE = "2223334524";
	
	@Test
	void testGetAllDepartments() {
		List<Department> departments = departmentDAOImpl.getAllDepartments();
		assertNotNull(departments);
	}
	
	@Test
	void testGetDepartmentByName() {
		assertThrows(DepartmentNotFoundException.class, 
				() -> {departmentDAOImpl.getDepartmentByName("fake department"); });
	}
	
	@Test
	void testAddDepartment() {
		Department department = new Department(TEST_DEPARTMENT_NAME, TEST_DEPARTMENT_PHONE, 112);
		assertEquals(true, departmentDAOImpl.addDepartment(department));
	}
	
	@Test
	void testUpdateDepartment() {
		try {
			String newPhoneNumber = "3456662234";
			Department department = departmentDAOImpl.getDepartmentByName(TEST_DEPARTMENT_NAME);
			assertEquals(department.getPhoneNumber(), TEST_DEPARTMENT_PHONE);
			department.setPhoneNumber(newPhoneNumber);
			if (departmentDAOImpl.updateDepartment(department)) {
				department = departmentDAOImpl.getDepartmentByName(TEST_DEPARTMENT_NAME);
				assertEquals(department.getPhoneNumber(), newPhoneNumber);
			}
		} catch (DepartmentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testDeleteDepartmentByName() {
		assertEquals(true, 
				departmentDAOImpl.deleteDepartmentByName(TEST_DEPARTMENT_NAME));
	}
}
