package com.tma.SpringBootDemo.dto;

public class EmployeeDTO extends BaseDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String[] roles;

	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
