package com.tma.SpringBootDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.dto.RoleDTO;

public interface IRoleService {

	/**
	 * Find all roles and convert to DTO
	 * 
	 * @return the list of {@link RoleDTO}
	 */
	List<RoleDTO> findAll();

	/**
	 * Find a role by id and convert to DTO
	 * 
	 * @param id the id to find
	 * @return the {@link RoleDTO}
	 */
	RoleDTO findById(Long id);

	/**
	 * Convert a role DTO to role and save to datasource
	 * 
	 * @param roleDTO the {@link RoleDTO} to save
	 * @return the {@link RoleDTO}
	 */
	RoleDTO save(RoleDTO roleDTO);

	/**
	 * Delete the roles by list of id
	 * 
	 * @param ids the list of id to delete
	 */
	void delete(Long[] ids);
}
