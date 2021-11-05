package com.tma.SpringBootDemo.converter;

import org.springframework.stereotype.Component;

import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.entity.Employee;

@Component
public class EmployeeConverter {

	/**
	 * Convert an employee to an employee DTO
	 * 
	 * @param employee the {@link Employee} to convert
	 * @return the {@link EmployeeDTO}
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
			roles[i] = employee.getRoles().get(i).getName();
		}
		employeeDTO.setRoles(roles);

		return employeeDTO;
	}

	/**
	 * Convert an employee DTO to an employee
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to convert
	 * @return the {@link Employee}
	 */
	public Employee toEntity(EmployeeDTO employeeDTO) {
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
	 * Convert an employee DTO to an old employee
	 * 
	 * @param employeeDTO the {@link EmployeeDTO} to convert
	 * @param oldEmployee the old {@link Employee} to set new value
	 * @return the {@link Employee}
	 */
	public Employee toEntity(EmployeeDTO employeeDTO, Employee oldEmployee) {

		oldEmployee.setUsername(employeeDTO.getUsername());
		oldEmployee.setPassword(employeeDTO.getPassword());
		oldEmployee.setFirstName(employeeDTO.getFirstName());
		oldEmployee.setLastName(employeeDTO.getLastName());
		oldEmployee.setEmail(employeeDTO.getEmail());
		oldEmployee.setStatus(employeeDTO.getStatus());

		return oldEmployee;
	}
}
