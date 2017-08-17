package com.example.rest.model;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	Iterable<Employee> findByDeptContaining(String dept);
	Iterable<Employee> findByLastNameContaining(String lastName);
	Iterable<Employee> findByTitleContaining(String title);
}
