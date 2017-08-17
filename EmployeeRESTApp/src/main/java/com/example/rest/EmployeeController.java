package com.example.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.rest.model.Employee;
import com.example.rest.model.EmployeeRepository;

@CrossOrigin
@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	// Get all employees
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Employee> getAll() {
		return employeeRepository.findAll();
	}

	// Get an employee
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Employee> get(@PathVariable long id) {
		Optional<Employee> match = employeeRepository.findById(id);
		if (match.isPresent()) {
			return ResponseEntity.ok(match.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Get employees by lastName
	@RequestMapping(method = RequestMethod.GET, value = "/lastname/{name}")
	public Iterable<Employee> getByLastName(@PathVariable String name) {
		return employeeRepository.findByLastNameContaining(name);
	}

	// Get employee by title
	@RequestMapping(method = RequestMethod.GET, value = "/title/{title}")
	public Iterable<Employee> getByTitle(@PathVariable String title) {
		return employeeRepository.findByTitleContaining(title);
	}

	// Get employee by dept
	@RequestMapping(method = RequestMethod.GET, value = "/department/{dept}")
	public Iterable<Employee> getByDepartament(@PathVariable String dept) {
		return employeeRepository.findByDeptContaining(dept);
	}

	// Add an employee
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Employee employee) {
		if (employee.getId() != null) {
			// ¿Se almacena con dicho ID? ¿Se resetea el ID? ¿Se devuelve conflicto HTTP
			// 409?
		}
		Employee newEmployee = employeeRepository.save(employee);
		if (newEmployee != null) {
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newEmployee.getId()).toUri()).build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Update an employee
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<?> update(@PathVariable long id, @RequestBody Employee employee) {
		if (id != employee.getId()) {
			// Error
		}
		if (employeeRepository.findById(id).isPresent()) {
			employeeRepository.save(employee);
			return ResponseEntity.ok().build();
		} else { // Es posible realizar el create aquí.
			return ResponseEntity.notFound().build();
		}
	}

	// Delete a employee
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		if (employeeRepository.findById(id).isPresent()) {
			employeeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}