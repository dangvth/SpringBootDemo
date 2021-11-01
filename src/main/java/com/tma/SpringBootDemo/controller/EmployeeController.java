package com.tma.SpringBootDemo.controller;

import java.util.List;

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
import com.tma.SpringBootDemo.exception.ResourceNotFoundException;
import com.tma.SpringBootDemo.service.IEmployeeService;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	
	/**
	 * Get all of employee
	 * @return List<EmployeeDTO> 
	 */
	@GetMapping("employees")
	public List<EmployeeDTO> getAllEmployee() {
		return service.findAll();
	}
	
	/**
	 * Get an employee by id
	 * @param id
	 * @return EmployeeDTO
	 * @throws ResourceNotFoundException 
	 */
	@GetMapping("employees/{id}")
	public EmployeeDTO getEmployeeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		return service.findById(id);
	}
	
	@PostMapping("employees")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return service.save(employeeDTO);
	}
	
	@PutMapping("employees/{id}")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable(value = "id") Long id) {
		employeeDTO.setId(id);
		return service.save(employeeDTO);
	}
	
	@DeleteMapping("employees")
	private void deleteEmployee(@RequestBody Long[] ids) {
		service.delete(ids);
	}
}
