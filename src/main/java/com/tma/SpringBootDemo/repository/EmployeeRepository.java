package com.tma.SpringBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tma.SpringBootDemo.entity.Employee;

/**
 * 
 * @author dangv
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/**
	 * Find an employee by username
	 * 
	 * @param username the username to find
	 * @return the {@link Employee}
	 */
	Employee findOneByUsername(String username);
}
