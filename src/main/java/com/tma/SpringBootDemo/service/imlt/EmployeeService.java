package com.tma.SpringBootDemo.service.imlt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.converter.EmployeeConverter;
import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.exception.NotFoundDataException;
import com.tma.SpringBootDemo.model.Employee;
import com.tma.SpringBootDemo.model.Role;
import com.tma.SpringBootDemo.repository.EmployeeRepository;
import com.tma.SpringBootDemo.repository.RoleRepository;
import com.tma.SpringBootDemo.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private EmployeeConverter converter;
	
	@Override
	public List<EmployeeDTO> findAll() {
		
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();
		
		for (Employee employee : employees) {
			employeeDTOs.add(converter.toDTO(employee));
		}
		
		return employeeDTOs;
	}

	@Override
	public EmployeeDTO findById(Long id) {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
//		if (employeeRepository.findById(id).isEmpty()) {
//			throw new NotFoundDataException("Cannot found employee with this id: " + id);
//		}
		
		Employee employee = employeeRepository.findById(id).get();
		converter.toDTO(employee);
		
		return employeeDTO;
	}

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		
		if (employeeDTO.getId() != null) {
			
			Employee oldEmployee = employeeRepository.findById(employeeDTO.getId()).get();
			employee = converter.toModel(employeeDTO, oldEmployee);
			
		} else {
			employee = converter.toModel(employeeDTO);
		}
		
		List<Role> roles = new ArrayList<>();
		
		for (String role : employeeDTO.getRoles()) {
			roles.add(roleRepository.findOneByCode(role));
		}
		
		employee.setRoles(roles);
		
		return converter.toDTO(employeeRepository.save(employee));
	}

	@Override
	public void delete(Long[] ids) {
		
		for (Long id : ids) {
			employeeRepository.deleteById(id);
		}
		
	}

}
