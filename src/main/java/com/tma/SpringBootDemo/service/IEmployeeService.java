package com.tma.SpringBootDemo.service;

import java.util.List;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.entity.Employee;

/**
 * 
 * @author dangv
 *
 */
public interface IEmployeeService {

	/**
	 * Find all {@link Employee} and convert to {@link EmployeeDTO}
	 * 
	 * @return the list of {@link EmployeeDTO}
	 */
	List<EmployeeDTO> findAll();

	/**
	 * Find the {@link Employee} by id and convert to the {@link EmployeeDTO}
	 * 
	 * @param id the id to find
	 * @return the {@link EmployeeDTO}
	 */
	EmployeeDTO findById(Long id);

	/**
	 * Convert the {@link EmployeeDTO} to the {@link Employee} and save to
	 * datasource
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to save
	 * @return the {@link EmployeeDTO}
	 */
	EmployeeDTO save(EmployeeDTO employeeDTO);

	/**
	 * Delete the {@link Employee} by id
	 * 
	 * @param id the id to delete
	 * @return the string
	 */
	String delete(Long id);
}
