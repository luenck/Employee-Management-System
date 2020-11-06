package com.cognixia.jump.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private Connection conn = ConnectionManager.getConnection();
	
	public List<Employee> getAllEmployees() {
		
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		try (PreparedStatement pstmt = conn.prepareStatement("select * from employee");
				ResultSet rs = pstmt.executeQuery(); 
			) {
			
			while(rs.next()) {
				int id = rs.getInt("id_number");
				String name = rs.getString("name");
				String dept = rs.getString("dept_name");
				String number = rs.getString("phone_number");
				int salary = rs.getInt("salary");
				
				Employee emp = new Employee(id, name, dept, number, salary);
				
				result.add(emp);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return result;
	}
	@Override
	public boolean doesEmployeeExist(String name) {
		ResultSet rs = null;
		try (PreparedStatement state = conn.prepareStatement("select * from employee where name = ?");
				
			) {
			
			state.setString(1, name);
			rs = state.executeQuery();
			if ( rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public Employee getEmployeeById(int employeeID) throws EmployeeNotFoundException {
		ResultSet rs = null;
		try(PreparedStatement state = conn.prepareStatement("select * from employee where id_number = ?");
				){
			
			state.setInt(1, employeeID);
			rs = state.executeQuery();
			
			if(rs.next()) {
				int id = rs.getInt("id_number");
				String name = rs.getString("name");
				String deptName = rs.getString("dept_name");
				String phone = rs.getString("phone_number");
				int salary = rs.getInt("salary");
				Employee emp = new Employee(id, name, deptName, phone, salary);
				return emp;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		throw new EmployeeNotFoundException(employeeID);
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		try  (PreparedStatement state = conn.prepareStatement("insert into employee values(null, ?,?,?,?)");
				){
			state.setString(1, employee.getName());
			state.setInt(2, employee.getSalary());
			state.setString(3, employee.getNumber());
			state.setString(4, employee.getDepartmentName());
			int count = state.executeUpdate();
			if(count == 1) {
				return true;
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteEmployeebyId(int employeeId) {
		try(PreparedStatement state = conn.prepareStatement("delete from employee where id_number = ?");
				){
			
			state.setInt(1, employeeId);
			int updated = state.executeUpdate();
			if(updated == 1) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
			
			
		return false;
	}
	//Updates employee phone, salary, and dept by the employee's name. 
	//Employee ID should not be updated in the DB
	//At this time, we don't update employee name. 
	@Override
	public boolean updateEmployee(Employee employee) {
		
		try { PreparedStatement state = conn.prepareStatement("update employee set salary = ?, phone_number = ?, dept_name = ? where name = ?");
		
			state.setInt(1, employee.getSalary());
			state.setString(2, employee.getNumber());
			state.setString(3, employee.getDepartmentName());
			state.setString(4, employee.getName());
			
			int count = state.executeUpdate();
			if(count == 1) {
				return true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	

}
