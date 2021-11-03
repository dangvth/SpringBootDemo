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
import com.tma.SpringBootDemo.service.IRoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService service;
	
	@GetMapping("")
	public List<RoleDTO> getAllRole() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public RoleDTO getRoleById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("")
	public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
		return service.save(roleDTO);
	}
	
	@PutMapping("/{id}")
	public RoleDTO updateRole(@RequestBody RoleDTO roleDTO, @PathVariable(value = "id") Long id) {
		roleDTO.setId(id);
		return service.save(roleDTO);
	}
	
	@DeleteMapping("")
	private void deleteRole(@RequestBody Long[] ids) {
		service.delete(ids);
	}
}
