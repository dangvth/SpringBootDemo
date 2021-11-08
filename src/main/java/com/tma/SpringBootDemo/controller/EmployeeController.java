package com.tma.SpringBootDemo.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.entity.Employee;
import com.tma.SpringBootDemo.service.IEmployeeService;
import com.tma.SpringBootDemo.utils.LogUtil;

/**
 * API for employee
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Get all {@link Employee} and convert to {@link EmployeeDTO}
	 * 
	 * @return the list of {@link EmployeeDTO}
	 */
	@GetMapping("")
	public List<EmployeeDTO> getAllEmployee() {
		LogUtil.logDebug(logger, "Get all employees");

		List<EmployeeDTO> employeeDTOs = service.findAll();

		LogUtil.logDebug(logger, "Get all employee done");
		return employeeDTOs;
	}

	/**
	 * Get the {@link Employee} by id and convert to the {@link EmployeeDTO}
	 * 
	 * @param id the id to get
	 * @return the {@link EmployeeDTO}
	 */
	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long id) {
		LogUtil.logDebug(logger, "Get employee by id: " + id);

		EmployeeDTO employeeDTO = service.findById(id);

		LogUtil.logDebug(logger, "Get employee by id: " + id + " done");
		return employeeDTO;
	}

	/**
	 * Convert the {@link EmployeeDTO} to the {@link Employee} and save to
	 * datasource
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to create
	 * @return the {@link EmployeeDTO}
	 */
	@PostMapping("")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		LogUtil.logDebug(logger, "Create new employee");

		employeeDTO = service.save(employeeDTO);

		LogUtil.logDebug(logger, "Create new employee done");
		return employeeDTO;
	}

	/**
	 * Convert the {@link EmployeeDTO} to the old {@link Employee} and save to
	 * datasource
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to update
	 * @param id          the id to update
	 * @return
	 */
	@PutMapping("/{id}")
	public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO, @PathVariable(value = "id") Long id) {
		LogUtil.logDebug(logger, "Update employee");

		employeeDTO.setId(id);
		employeeDTO = service.save(employeeDTO);

		LogUtil.logDebug(logger, "Update employee done");
		return employeeDTO;
	}

	/**
	 * Delete the list of {@link Employee} by list of id
	 * 
	 * @param ids the list of id
	 */
	@DeleteMapping("")
	private void deleteEmployee(@RequestBody Long[] ids) {
		LogUtil.logDebug(logger, "Delete employees");

		service.delete(ids);

		LogUtil.logDebug(logger, "Delete employees done");
	}
}
