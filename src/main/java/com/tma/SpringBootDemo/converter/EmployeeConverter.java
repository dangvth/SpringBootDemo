package com.tma.SpringBootDemo.converter;


import org.springframework.stereotype.Component;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.model.Employee;

@Component
public class EmployeeConverter {

	/**
	 * Convert a model (an entity) to a dto
	 * 
	 * @param employee
	 * @return a dto
	 */
	public EmployeeDTO toDTO(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();

		if (employee.getId() != null) {
			employeeDTO.setId(employee.getId());
		}

		employeeDTO.setUsername(employee.getUsername());
		employeeDTO.setPassword("********");
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setEmail(employee.getEmail());
		employeeDTO.setStatus(employee.getStatus());
		employeeDTO.setCreatedAt(employee.getCreatedAt());
		employeeDTO.setModifiedAt(employee.getModifiedAt());
		
		String[] roles = new String[employee.getRoles().size()];
		for (int i = 0; i < employee.getRoles().size(); i++) {
			roles[i] = employee.getRoles().get(i).getCode();
		}
		employeeDTO.setRoles(roles);

		return employeeDTO;
	}

	/**
	 * Convert a dto to a model (an entity)
	 * 
	 * @param employeeDTO
	 * @return a model (an entity)
	 */
	public Employee toModel(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();

		employee.setUsername(employeeDTO.getUsername());
		employee.setPassword(employeeDTO.getPassword());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setEmail(employeeDTO.getEmail());
		employee.setStatus(employeeDTO.getStatus());

		return employee;
	}

	/**
	 * Convert a dto to an old model (entity)
	 * @param employeeDTO
	 * @param oldEmployee
	 * @return an old model (entity)
	 */
	public Employee toModel(EmployeeDTO employeeDTO, Employee oldEmployee) {

		oldEmployee.setUsername(employeeDTO.getUsername());
		oldEmployee.setPassword(employeeDTO.getPassword());
		oldEmployee.setFirstName(employeeDTO.getFirstName());
		oldEmployee.setLastName(employeeDTO.getLastName());
		oldEmployee.setEmail(employeeDTO.getEmail());
		oldEmployee.setStatus(employeeDTO.getStatus());

		return oldEmployee;
	}
}
