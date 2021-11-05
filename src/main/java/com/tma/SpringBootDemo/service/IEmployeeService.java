package com.tma.SpringBootDemo.service;

import java.util.List;

import com.tma.SpringBootDemo.dto.EmployeeDTO;

public interface IEmployeeService {

	/**
	 * Find all employees and convert to DTO
	 * 
	 * @return the list of {@link EmployeeDTO}
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Find an employee by id and convert to DTO
	 * 
	 * @param id the id to find
	 * @return the {@link EmployeeDTO}
	 */
	EmployeeDTO findById(Long id);

	/**
	 * Convert an employee DTO to employee and save to datasource
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to save
	 * @return the {@link EmployeeDTO}
	 */
	EmployeeDTO save(EmployeeDTO employeeDTO);

	/**
	 * Delete the employees by list of id
	 * 
	 * @param ids the list of id to delete
	 */
	void delete(Long[] ids);
}
