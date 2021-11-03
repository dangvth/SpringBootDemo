package com.tma.SpringBootDemo.dto;

public class RoleDTO extends BaseDTO {

	private String code;
	private String name;
	private String[] employees;

	public String[] getEmployees() {
		return employees;
	}
	public void setEmployees(String[] employees) {
		this.employees = employees;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
