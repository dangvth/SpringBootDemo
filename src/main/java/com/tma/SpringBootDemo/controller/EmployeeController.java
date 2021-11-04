package com.tma.SpringBootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.service.IEmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	/**
	 * Get all of employee
	 * 
	 * @return List<EmployeeDTO>
	 */
	@GetMapping("")
	public List<EmployeeDTO> getAllEmployee() {
		return service.findAll();
	}

	/**
	 * Get an employee by id
	 * 
	 * @param id
	 * @return EmployeeDTO
	 */
	@GetMapping("/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@PostMapping("")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return service.save(employeeDTO);
	}

	@PutMapping("/{id}")
	public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO, @PathVariable(value = "id") Long id) {
		employeeDTO.setId(id);
		return service.save(employeeDTO);
	}

	@DeleteMapping("")
	private void deleteEmployee(@RequestBody Long[] ids) {
		service.delete(ids);
	}
}
