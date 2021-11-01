package com.tma.SpringBootDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tma.SpringBootDemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findOneById(Long id);
}
