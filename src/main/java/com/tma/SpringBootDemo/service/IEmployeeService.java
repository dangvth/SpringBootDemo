package com.tma.SpringBootDemo.service;

import java.util.List;

import com.tma.SpringBootDemo.dto.EmployeeDTO;

public interface IEmployeeService {
	/**
	 * Find all of employee
	 * @return a list of employee dto
	 */
	List<EmployeeDTO> findAll();
	
	/**
	 * Find an employee by id
	 * @param id
	 * @return an employee dto
	 */
	EmployeeDTO findById(Long id);
	
	/**
	 * Save an employee
	 * @param employeeDTO
	 * @return an employee dto
	 */
	EmployeeDTO save(EmployeeDTO employeeDTO);

	void delete(Long[] ids);
}
