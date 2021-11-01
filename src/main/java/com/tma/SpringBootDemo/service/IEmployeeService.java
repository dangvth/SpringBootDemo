package com.tma.SpringBootDemo.service;

import java.util.List;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.exception.ResourceNotFoundException;

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
	 * @throws ResourceNotFoundException 
	 */
	EmployeeDTO findById(Long id) throws ResourceNotFoundException;
	
	/**
	 * Save an employee
	 * @param employeeDTO
	 * @return an employee dto
	 */
	EmployeeDTO save(EmployeeDTO employeeDTO);

	void delete(Long[] ids);
}
