package com.tma.SpringBootDemo.service;

import java.util.List;

import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.entity.Role;

/**
 * 
 * @author dangv
 *
 */
public interface IRoleService {

	/**
	 * Find all {@link Role} and convert to {@link RoleDTO}
	 * 
	 * @return the list of {@link RoleDTO}
	 */
	List<RoleDTO> findAll();

	/**
	 * Find the {@link Role} by id and convert to the {@link RoleDTO}
	 * 
	 * @param id the id to find
	 * @return the {@link RoleDTO}
	 */
	RoleDTO findById(Long id);

	/**
	 * Convert the {@link RoleDTO} to the {@link Role} and save to datasource
	 * 
	 * @param roleDTO the {@link RoleDTO} to save
	 * @return the {@link RoleDTO}
	 */
	RoleDTO save(RoleDTO roleDTO);

	/**
	 * Delete the roles by list of id
	 * 
	 * @param id the id to delete
	 * @return the string
	 */
	String delete(Long id);
}
