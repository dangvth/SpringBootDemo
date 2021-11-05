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

import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.entity.Role;
import com.tma.SpringBootDemo.service.IRoleService;

/**
 * API for role
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private IRoleService service;

	/**
	 * Get all {@link Role}
	 * 
	 * @return the list of {@link Role}
	 */
	@GetMapping("")
	public List<RoleDTO> getAllRole() {
		return service.findAll();
	}

	/**
	 * Get the {@link Role} by id and convert to the {@link RoleDTO}
	 * 
	 * @param id the id to get
	 * @return the {@link RoleDTO}
	 */
	@GetMapping("/{id}")
	public RoleDTO getRoleById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	/**
	 * Convert the {@link RoleDTO} to the {@link Role} and save to datasouce
	 * 
	 * @param roleDTO the {@link RoleDTO} to create
	 * @return the {@link RoleDTO}
	 */
	@PostMapping("")
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
		return service.save(roleDTO);
	}

	/**
	 * Convert the {@link RoleDTO} to the old {@link Role} and save to datasouce
	 * 
	 * @param roleDTO the {@link RoleDTO} to update
	 * @param id      the id to update
	 * @return the {@link RoleDTO}
	 */
	@PutMapping("/{id}")
	public RoleDTO updateRole(@RequestBody RoleDTO roleDTO, @PathVariable(value = "id") Long id) {
		roleDTO.setId(id);
		return service.save(roleDTO);
	}

	/**
	 * Delete the roles by the list of id
	 * 
	 * @param ids the list of id to delete
	 */
	@DeleteMapping("")
	private void deleteRole(@RequestBody Long[] ids) {
		service.delete(ids);
	}
}
