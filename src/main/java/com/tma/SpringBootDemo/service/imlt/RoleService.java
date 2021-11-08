package com.tma.SpringBootDemo.service.imlt;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tma.SpringBootDemo.converter.RoleConverter;
import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.entity.Role;
import com.tma.SpringBootDemo.repository.RoleRepository;
import com.tma.SpringBootDemo.service.IRoleService;
import com.tma.SpringBootDemo.utils.LogUtil;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository repository;

	@Autowired
	private RoleConverter converter;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public List<RoleDTO> findAll() {
		LogUtil.logDebug(logger, "Find all roles");

		List<RoleDTO> roleDTOs = new ArrayList<>();
		for (Role role : repository.findAll()) {
			roleDTOs.add(converter.toDTO(role));
		}

		LogUtil.logDebug(logger, "Find all roles done");
		return roleDTOs;
	}

	@Override
	public RoleDTO findById(Long id) {
		LogUtil.logDebug(logger, "Find role by id: " + id);

		Role role = repository.findById(id).get();

		LogUtil.logDebug(logger, "Find role by id: " + id + " done");
		return converter.toDTO(role);
	}

	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		LogUtil.logDebug(logger, "Save role");

		Role role = new Role();

		if (roleDTO.getId() != null) {

			Role oldRole = repository.findById(roleDTO.getId()).get();
			role = converter.toEntity(roleDTO, oldRole);

		} else {
			role = converter.toEntity(roleDTO);
		}

		LogUtil.logDebug(logger, "Save role done");
		return converter.toDTO(repository.save(role));
	}

	@Override
	public void delete(Long[] ids) {
		LogUtil.logDebug(logger, "Delete role");

		for (Long id : ids) {
			repository.deleteById(id);
		}

		LogUtil.logDebug(logger, "Delete role done");
	}

}
