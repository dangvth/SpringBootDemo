package com.tma.SpringBootDemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.dto.RoleDTO;

public interface IRoleService {

	List<RoleDTO> findAll();
	
	RoleDTO findById(Long id);
	
	RoleDTO save(RoleDTO role);
	
	void delete(Long[] ids);
}
