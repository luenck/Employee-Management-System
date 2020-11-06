package com.cognixia.jump.company;

public class DepartmentNotFoundException extends Exception {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public DepartmentNotFoundException(String deptName) {
			super("Department with name " + deptName + " was not found." );
		}

}
