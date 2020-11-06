package com.cognixia.jump.company;


import java.util.Scanner;

public class Menu {
	
	public static void main(String[] args) {
		
		EmployeeDAO emp = new EmployeeDAOImpl();
		DepartmentDAO dept = new DepartmentDAOImpl();
		Scanner in = new Scanner(System.in);
		Menu menu = new Menu();
		while(true) {
		menu.mainMenu(in, emp, dept);
		}
	}
	
	
	public void mainMenu(Scanner in, EmployeeDAO emp, DepartmentDAO dept) {
		while(true) {
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println();
			System.out.println("+++++++++++++   EMPLOYEE MANAGEMENT SYSTEM   ++++++++++++++");
			System.out.println();
			System.out.println("+++++++++++++++++++  EMPLOYEE MENU  +++++++++++++++++++++++");
			System.out.println("\nENTER 1 TO ADD A NEW EMPLOYEE.");
			System.out.println("\nENTER 2 TO VIEW EMPLOYEE RECORDS.");
			System.out.println("\nENTER 3 TO UPDATE AN EMPLOYEE RECORD.");
			System.out.println("\nENTER 4 TO REMOVE AN EMPLOYEE.");
			System.out.println("\nENTER 5 TO VIEW DEPARTMENTS");
			System.out.println("\nENTER 6 TO ADD A DEPARTMENT.");
			System.out.println("\nENTER 7 TO UPDATE A DEPARTMENT.");
			System.out.println("\nENTER 8 TO REMOVE A DEPARTMENT.");
			System.out.println("\nENTER 0 TO EXIT");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

			
			
			// Get user input
			System.out.println("Enter your menu choice:");
			
			int menuChoice = in.nextInt();
			
			switch (menuChoice) {
			case 1: {
					try {
						addNewEmployee(in, emp, dept );
					} catch (DepartmentNotFoundException e) {
						System.out.println(e.getMessage());
						System.out.println("Press Enter to continue");
						in.nextLine();
					}
					break;
					}
			case 2: {
					printEmployees(in, emp, dept );
					break;
			}
			case 3: {
				try {
					updateEmployee(in, emp, dept );
				} catch (EmployeeNotFoundException e) {
					System.out.println(e.getMessage());
					System.out.println("Press Enter to continue");
					in.nextLine();
				} catch (DepartmentNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					System.out.println("Press Enter to continue");
					in.nextLine();
				}
				break;
			}
			case 4: {
				try {
					deleteEmployee(in, emp, dept );
				} catch (EmployeeNotFoundException e) {
					System.out.println(e.getMessage());
					System.out.println("Press Enter to continue");
					in.nextLine();
				}
				break;
			}
			case 5: {
				printDepartments(in, emp, dept );
				break;
			}
			case 6: {
				try {
					addDepartment(in, emp, dept );
				} catch (DepartmentNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					
				}
				break;
			}
			case 7: {
				try {
					updateDepartment(in, emp, dept );
				} catch (DepartmentNotFoundException e) {
					System.out.println(e.getMessage());
					System.out.println("Press Enter to continue");
					in.nextLine();
				}
				break;
			}
			case 8: {
				try {
					deleteDepartment(in, emp, dept );
				} catch (DepartmentNotFoundException e) {
					System.out.println(e.getMessage());
					System.out.println("Press Enter to continue");
					in.nextLine();
					}
				break;
			}

			
			case 0: {
				System.exit(0);
				return;
			
			}
			
			
			
			default:
				System.out.println("Not good at following directions, huh?");

				
				
				
			}
			
		}	
	}
	
	public void addNewEmployee(Scanner in, EmployeeDAO e, DepartmentDAO d) throws DepartmentNotFoundException {
		System.out.println("Please Enter Employee's  Name");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Please Enter Salary");
		int salary = in.nextInt();
		System.out.println("Please enter 10-Digit Phone number");
		in.nextLine();
		String phoneNumber = in.nextLine();
		System.out.println("Please select Department to add Employee");
		for(Department dept : d.getAllDepartments()) {
			System.out.println(dept.getName());
		}
		String deptName = in.nextLine();
		Department check = d.getDepartmentByName(deptName);
		Employee temp = new Employee(0, name, deptName, phoneNumber, salary);
		e.addEmployee(temp);
		
		System.out.println("Press enter to continue");
		in.nextLine();
		
	}
	
	
	

	
	public void printEmployees(Scanner in, EmployeeDAO e, DepartmentDAO d) {
		for(Employee emp : e.getAllEmployees()) {
			System.out.println(emp);
		}
		
		System.out.println("\nPress enter to continue");
		in.nextLine();
		in.nextLine();
	}
	

	
	public void updateEmployee(Scanner in, EmployeeDAO e, DepartmentDAO d) throws EmployeeNotFoundException, DepartmentNotFoundException {
		printEmployees(in, e, d);
		System.out.println("What is the name of the employee you wish to update?");
		String employeeName = in.nextLine();
		if (!e.doesEmployeeExist(employeeName)) {
			throw new EmployeeNotFoundException(employeeName);
		}
		System.out.println("Please enter the updated employee information");
		System.out.println("Department Name?");
		String deptName = in.nextLine();
		Department checkForDepartment = d.getDepartmentByName(deptName);
		System.out.println("Salary?");
		int sal = in.nextInt();
		System.out.println("Phone number?");
		in.nextLine();
		String phone = in.nextLine();
		Employee temp = new Employee(0, employeeName, deptName, phone, sal);
		if(e.updateEmployee(temp)) {
			System.out.println("Employee updated");
		} else {
			throw new EmployeeNotFoundException(employeeName);
		}
		
		
		System.out.println("Press enter to continue");
		in.nextLine();
	}
 
	public void deleteEmployee(Scanner in, EmployeeDAO e, DepartmentDAO d) throws EmployeeNotFoundException {
		printEmployees(in, e, d);
		System.out.println("please enter ID of the employee to delete");
		int id = in.nextInt();
		if(e.deleteEmployeebyId(id)) {
			System.out.println("Employee deleted");
		} else {
			throw new EmployeeNotFoundException(id);
		}
		
		System.out.println("Press enter to continue");
		in.nextLine();
	}

	
	
	public void printDepartments(Scanner in, EmployeeDAO e, DepartmentDAO d) {
		for(Department dept : d.getAllDepartments()) {
			System.out.println(dept.getName());
			System.out.println("Budget : " + dept.getBudget());
			System.out.println("Phone number : " + dept.getPhoneNumber());
		}
		
		System.out.println("\nPress enter to continue");
		in.nextLine();
		in.nextLine();
	}
	

	
	
	
	public void addDepartment(Scanner in, EmployeeDAO e, DepartmentDAO d) throws DepartmentNotFoundException {
		
		System.out.println("Enter the name of the new department");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Enter their budget");
		int budget = in.nextInt();
		System.out.println("Enter their 10 digit phone number");
		in.nextLine();
		String phone = in.nextLine();
		Department temp = new Department(name, phone, budget);
		 if(d.addDepartment(temp)) {
			 System.out.println("Department added");
		 } else {
			 throw new DepartmentNotFoundException(name);
		 }
		System.out.println("Press enter to continue");
		in.nextLine();
	}
	
	public void updateDepartment(Scanner in, EmployeeDAO e, DepartmentDAO d) throws DepartmentNotFoundException {
		printDepartments(in, e, d);
		System.out.println("Which department do you wish to update?");
		String name = in.nextLine();
		Department checkForDepartment = d.getDepartmentByName(name);
		System.out.println("What is their new budget?");
		int budget = in.nextInt();
		System.out.println("What is their new phone number?");
		in.nextLine();
		String phone = in.nextLine();
		
		Department temp = new Department(name, phone, budget);
		
		if(d.updateDepartment(temp)) {
			System.out.println("Department updated");
		} else {
			throw new DepartmentNotFoundException(name);
		}
		
		
		System.out.println("Press enter to continue");
		in.nextLine();
	}
	

	
	public void deleteDepartment(Scanner in, EmployeeDAO e, DepartmentDAO d) throws DepartmentNotFoundException {
		printDepartments(in, e, d);
		System.out.println("Which department would you like to delete?");
		String name = in.nextLine();
		if(d.deleteDepartmentByName(name)) {
			System.out.println("Department deleted");
			d.removeEmployees(name);
		} else {
			throw new DepartmentNotFoundException(name);
		}
		System.out.println("Press enter to continue");
		in.nextLine();
	
	
	}

	

}
