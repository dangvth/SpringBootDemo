package com.tma.SpringBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tma.SpringBootDemo.entity.Employee;

/**
 * 
 * @author dangv
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

	/**
	 * Find an employee by username
	 * 
	 * @param username the username to find
	 * @return the {@link Employee}
	 */
	Employee findOneByUsername(String username);
	
}
