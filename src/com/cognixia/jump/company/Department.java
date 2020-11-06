package com.cognixia.jump.company;

public class Department {
	
	private String name;
	private String phoneNumber;
	private int budget;
	
	public Department(String name, String phoneNumber, int budget) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.budget = budget;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	@Override
	public String toString() {
		return "Department [name=" + name + ", phoneNumber=" + phoneNumber + ", budget=" + budget + "]";
	}
	
	
	
	

}