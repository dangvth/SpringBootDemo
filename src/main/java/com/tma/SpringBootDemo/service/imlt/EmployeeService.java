package com.tma.SpringBootDemo.service.imlt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.converter.EmployeeConverter;
import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.exception.ResourceNotFoundException;
import com.tma.SpringBootDemo.model.Employee;
import com.tma.SpringBootDemo.repository.EmployeeRepository;
import com.tma.SpringBootDemo.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeConverter converter;
	
	@Override
	public List<EmployeeDTO> findAll() {
		
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		List<Employee> employees = repository.findAll();
		
		for (Employee employee : employees) {
			employeeDTOs.add(converter.toDTO(employee));
		}
		
		return employeeDTOs;
	}

	@Override
	public EmployeeDTO findById(Long id) throws ResourceNotFoundException {
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		Employee employee = repository.findOneById(id);
		
		if (employee != null) {
			converter.toDTO(employee);
		} else {
			throw new ResourceNotFoundException("Employee not found for this id: " + id);
		}
		
		return employeeDTO;
	}

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		
		Employee employee = new Employee();
		
		if (employeeDTO.getId() != null) {
			
			Employee oldEmployee = repository.findOneById(employeeDTO.getId());
			employee = converter.toModel(employeeDTO, oldEmployee);
			
		} else {
			employee = converter.toModel(employeeDTO);
		}
		
		return converter.toDTO(repository.save(employee));
	}

	@Override
	public void delete(Long[] ids) {
		
		for (Long id : ids) {
			repository.deleteById(id);
		}
		
	}

}
