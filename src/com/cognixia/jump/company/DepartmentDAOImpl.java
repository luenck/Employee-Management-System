package com.cognixia.jump.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO{
	
	private Connection conn = ConnectionManager.getConnection();

	@Override
	public List<Department> getAllDepartments() {
		
		ArrayList<Department> result = new ArrayList<Department>();
		
		try (PreparedStatement pstmt = conn.prepareStatement("select * from department");
				ResultSet rs = pstmt.executeQuery(); 
			) {
			
			while (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int budget = rs.getInt("budget");
				
				Department dept = new Department(name, phone, budget);
				
				result.add(dept);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public Department getDepartmentByName(String deptName) throws DepartmentNotFoundException {
		
		ResultSet rs = null;
		try (PreparedStatement pstmt 
				= conn.prepareStatement("select * from department where name = ?"); 
			) {
			pstmt.setString(1, deptName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				int budget = rs.getInt("budget");
				
				Department dept = new Department(name, phone, budget);
				return dept;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		throw new DepartmentNotFoundException(deptName);
	}

	@Override
	public boolean addDepartment(Department dept) {
		
		try(PreparedStatement pstmt = conn.prepareStatement("insert into department values(?,?,?)")) {
			
			pstmt.setString(1, dept.getName());
			pstmt.setString(2, dept.getPhoneNumber());
			pstmt.setInt(3, dept.getBudget());
			
			int count = pstmt.executeUpdate();
			
			if (count > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public boolean deleteDepartmentByName(String deptName) {
		
		try (PreparedStatement pstmt 
				= conn.prepareStatement("delete from department where name = ?"); 
			) {
			pstmt.setString(1, deptName);
			int count = pstmt.executeUpdate();
			if (count > 0) return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateDepartment(Department dept) {
		
		final String prepareStatement = 
									"UPDATE department "
									+ "SET "
									+ "budget = ? , "
									+ "phone = ? "
									+ "WHERE name = ?";
		try (PreparedStatement ptmt = conn.prepareStatement(prepareStatement);
				){
			ptmt.setInt(1, dept.getBudget());
			ptmt.setString(2, dept.getPhoneNumber());
			ptmt.setString(3, dept.getName());
			int count = ptmt.executeUpdate();
			if (count > 0) return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void removeEmployees(String name) {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update employee set dept_name = ' ' where dept_name = ?"); 
			) {
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	

}
