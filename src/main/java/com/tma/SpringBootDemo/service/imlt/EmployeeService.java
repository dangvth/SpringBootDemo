package com.tma.SpringBootDemo.service.imlt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.converter.EmployeeConverter;
import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.entity.Employee;
import com.tma.SpringBootDemo.entity.Role;
import com.tma.SpringBootDemo.repository.EmployeeRepository;
import com.tma.SpringBootDemo.repository.RoleRepository;
import com.tma.SpringBootDemo.search.EmployeePredicate;
import com.tma.SpringBootDemo.service.IEmployeeService;
import com.tma.SpringBootDemo.utils.LogUtil;

/**
 * 
 * @author dangv
 *
 */
@Service
public class EmployeeService implements IEmployeeService, UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private EmployeeConverter converter;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public List<EmployeeDTO> findAll() {
		LogUtil.logDebug(logger, "Find all employees");

		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		List<Employee> employees = employeeRepository.findAll();

		for (Employee employee : employees) {
			employeeDTOs.add(converter.toDTO(employee));
		}
		LogUtil.logDebug(logger, "Find all employee done");
		return employeeDTOs;
	}

	@Override
	public EmployeeDTO findById(Long id) {
		LogUtil.logDebug(logger, "Find employee by id: " + id);

		Employee employee = employeeRepository.findById(id).get();

		LogUtil.logDebug(logger, "Find employee by id: " + id + " done");
		return converter.toDTO(employee);
	}

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		LogUtil.logDebug(logger, "Save employee");

		Employee employee = new Employee();

		if (employeeDTO.getId() != null) {
			Employee oldEmployee = employeeRepository.findById(employeeDTO.getId()).get();
			employee = converter.toEntity(employeeDTO, oldEmployee);

		} else {
			employee = converter.toEntity(employeeDTO);
		}

		List<Role> roles = new ArrayList<>();

		for (String role : employeeDTO.getRoles()) {
			roles.add(roleRepository.findOneByName(role));
		}

		employee.setRoles(roles);

		LogUtil.logDebug(logger, "Save employee done");
		return converter.toDTO(employeeRepository.save(employee));
	}

	@Override
	public String delete(Long id) {
		LogUtil.logDebug(logger, "Delete employee by id: " + id);

		employeeRepository.deleteById(id);

		LogUtil.logDebug(logger, "Delete employee by id: " + id + " done");
		return "Delete successful";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LogUtil.logDebug(logger, "Load employee by username: " + username);
		
		Employee employee = employeeRepository.findOneByUsername(username);

		if (employee == null) {
			throw new UsernameNotFoundException(username);
		}

		LogUtil.logDebug(logger, "Load employee by username: " + username + " done");
		return employee;
	}

	@Override
	public List<EmployeeDTO> findAllByQueryDSL(String name) {
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		
		Iterator<Employee> resultList = employeeRepository.findAll(EmployeePredicate.nameContain(name)).iterator();
		
		while (resultList.hasNext()) {
			Employee employee = (Employee) resultList.next();
			employeeDTOs.add(converter.toDTO(employee));
		}
		
		return employeeDTOs;
	}

}
