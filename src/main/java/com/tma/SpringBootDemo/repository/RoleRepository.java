package com.tma.SpringBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tma.SpringBootDemo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Find a role by name
	 * 
	 * @param name the name to find
	 * @return the {@link Role}
	 */
	Role findOneByName(String name);
}
