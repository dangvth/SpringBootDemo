package com.tma.SpringBootDemo.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.entity.Employee;
import com.tma.SpringBootDemo.entity.Role;

/**
 * 
 * @author dangv
 *
 */
@Component
public class RoleConverter {

	/**
	 * Convert a role to a role DTO
	 * 
	 * @param role the {@link Role} to convert
	 * @return the {@link RoleDTO}
	 */
	public RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();

		if (role.getId() != null) {
			roleDTO.setId(role.getId());
		}

		roleDTO.setName(role.getName());
		roleDTO.setStatus(role.getStatus());
		roleDTO.setCreatedAt(role.getCreatedAt());
		roleDTO.setModifiedAt(role.getModifiedAt());
		roleDTO.setCreatedBy(role.getCreatedBy());
		roleDTO.setModifiedBy(role.getModifiedBy());

		List<Employee> eList = role.getEmployees();
		String[] employees = new String[eList.size()];

		for (int i = 0; i < eList.size(); i++) {
			employees[i] = eList.get(i).getFirstName() + " " + eList.get(i).getLastName();
		}

		roleDTO.setEmployees(employees);

		return roleDTO;
	}

	/**
	 * Convert a role DTO to a role
	 * 
	 * @param roleDTO the {@link RoleDTO} to convert
	 * @return the {@link Role}
	 */
	public Role toEntity(RoleDTO roleDTO) {
		Role role = new Role();

		role.setName(roleDTO.getName());
		role.setStatus(roleDTO.getStatus());

		return role;
	}

	/**
	 * Convert a role DTO to an old role
	 * 
	 * @param roleDTO the {@link RoleDTO} to convert
	 * @param oldRole the old {@link Role} to set new value
	 * @return the {@link Role}
	 */
	public Role toEntity(RoleDTO roleDTO, Role oldRole) {

		oldRole.setName(roleDTO.getName());
		oldRole.setStatus(roleDTO.getStatus());

		return oldRole;
	}
}
