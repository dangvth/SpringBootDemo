package com.tma.SpringBootDemo.dto;

import java.util.Date;

/**
 * 
 * @author dangv
 *
 */
public class RoleDTO extends BaseDTO {

	private String name;
	private String[] employees;

	public RoleDTO(String name) {
		super();
		this.name = name;
	}

	public RoleDTO(Long id, Date createdAt, Date modifiedAt, Integer status, String name) {
		super(id, createdAt, modifiedAt, status);
		this.name = name;
	}

	public RoleDTO() {
		super();
	}

	public String[] getEmployees() {
		return employees;
	}

	public void setEmployees(String[] employees) {
		this.employees = employees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
