package com.example.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
	@Id
	@GeneratedValue
	private final Long id;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String phone;
	private final String birthDate;
	private final String title;
	private final String dept;
	
	public Employee() {
		super();
		this.id = null;
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.phone = "";
		this.birthDate = "";
		this.title = "";
		this.dept = "";
	}

	public Employee(long id, String firstName, String lastName, String email, String phone, String birthDate, String title, String dept) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.birthDate = birthDate;
		this.title = title;
		this.dept = dept;
	}

	public Long getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDept() {
		return this.dept;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id.longValue() 
				+ " First Name: " + this.firstName
		        + " Last Name: " + this.lastName
		        + " EMail: " + this.email
		        + " Phone: " + this.phone
		        + " Birth Date: " + this.birthDate
		        + " Title: " + this.title
		        + " Department: " + this.dept;
	}
}
