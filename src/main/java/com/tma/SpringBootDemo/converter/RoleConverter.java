package com.tma.SpringBootDemo.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.model.Employee;
import com.tma.SpringBootDemo.model.Role;

@Component
public class RoleConverter {

	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();

		if (role.getId() != null) {
			roleDTO.setId(role.getId());
		}

		roleDTO.setName(role.getName());
		roleDTO.setCode(role.getCode());
		roleDTO.setStatus(role.getStatus());
		roleDTO.setCreatedAt(role.getCreatedAt());
		roleDTO.setModifiedAt(role.getModifiedAt());
		
		List<Employee> eList = role.getEmployees();
		String[] employees = new String[eList.size()];
		
		for (int i = 0; i < eList.size(); i++) {
			employees[i] = eList.get(i).getFirstName() + " " + eList.get(i).getLastName();
		}

		roleDTO.setEmployees(employees);
		
		return roleDTO;
	}
	
	public Role toModel(RoleDTO roleDTO) {
		Role role = new Role();

		role.setName(roleDTO.getName());
		role.setCode(roleDTO.getCode());
		role.setStatus(roleDTO.getStatus());

		return role;
	}
	
	public Role toModel(RoleDTO roleDTO, Role oldRole) {
		

		oldRole.setName(roleDTO.getName());
		oldRole.setCode(roleDTO.getCode());
		oldRole.setStatus(roleDTO.getStatus());

		return oldRole;
	}
}
