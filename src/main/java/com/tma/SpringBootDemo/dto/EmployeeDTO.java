package com.tma.SpringBootDemo.dto;

import java.util.Date;

/**
 * 
 * @author dangv
 *
 */
public class EmployeeDTO extends BaseDTO {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String[] roles;

	public EmployeeDTO(Long id, Date createdAt, Date modifiedAt, Integer status, String username, String password,
			String firstName, String lastName, String email, String[] roles) {
		super(id, createdAt, modifiedAt, status);
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public EmployeeDTO(String username, String password, String firstName, String lastName, String email,
			String[] roles) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public EmployeeDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
