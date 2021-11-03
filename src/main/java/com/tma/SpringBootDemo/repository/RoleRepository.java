package com.tma.SpringBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tma.SpringBootDemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findOneByCode(String code);
}
