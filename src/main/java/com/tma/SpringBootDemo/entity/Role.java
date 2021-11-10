package com.tma.SpringBootDemo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Role entity
 * 
 * @author dangv
 *
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "roles")
	List<Employee> employees = new ArrayList<>();

	/**
	 * Get name of role
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name for role
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get a list of employees
	 * 
	 * @return the list of employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Set a list of employees
	 * 
	 * @param employees the list of employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
