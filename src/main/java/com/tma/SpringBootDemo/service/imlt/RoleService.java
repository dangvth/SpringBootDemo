package com.tma.SpringBootDemo.service.imlt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.converter.RoleConverter;
import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.model.Role;
import com.tma.SpringBootDemo.repository.RoleRepository;
import com.tma.SpringBootDemo.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private RoleConverter converter;
	
	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> roleDTOs = new ArrayList<>();
		for (Role role : repository.findAll()) {
			roleDTOs.add(converter.toDTO(role));
		}
		return roleDTOs;
	}

	@Override
	public RoleDTO findById(Long id) {
		return converter.toDTO(repository.findById(id).get());
	}

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		
		Role role = new Role();
		
		if (roleDTO.getId() != null) {
			
			Role oldRole = repository.findById(roleDTO.getId()).get();
			role = converter.toModel(roleDTO, oldRole);
			
		} else {
			role = converter.toModel(roleDTO);
		}
		
		return converter.toDTO(repository.save(role));
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			repository.deleteById(id);
		}
	}

}
