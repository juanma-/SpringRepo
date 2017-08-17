package com.example.rest;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.rest.model.Employee;
import com.example.rest.model.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class EmployeeControllerTest {
	private MockMvc mockMvc;
	private List<Employee> employeeList = new ArrayList<>();
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(wac).build();

		this.employeeRepository.deleteAll();
		this.employeeList.add(
				employeeRepository.save(
						new Employee(138, "Lucious", "Steuber", "Lucious.Steuber@example.com",
								"977-372-2840", "1961-11-24T16:19:53.598Z", "District Creative Supervisor", 
								"Mobility")));
		this.employeeList.add(
				employeeRepository.save(
						new Employee(139, "Kacey", "Kilback", "Kacey.Kilback@example.com",
								"268-777-2011", "1957-09-06T10:07:09.719Z", "Corporate Mobility Agent", 
								"Infrastructure")));
		this.employeeList.add(
				employeeRepository.save(
						new Employee(130, "Preston", "Stark", "Preston.Stark@example.com",
								"080-698-9552", "1994-02-02T10:24:40.312Z", "Corporate Program Orchestrator", 
								"Integration")));
	}

	@Test
	public void getEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(this.employeeList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(this.employeeList.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(this.employeeList.get(0).getLastName())))
                .andExpect(jsonPath("$[0].email", is(this.employeeList.get(0).getEmail())))
                .andExpect(jsonPath("$[0].phone", is(this.employeeList.get(0).getPhone())))
                .andExpect(jsonPath("$[0].birthDate", is(this.employeeList.get(0).getBirthDate())))
                .andExpect(jsonPath("$[0].title", is(this.employeeList.get(0).getTitle())))
                .andExpect(jsonPath("$[0].dept", is(this.employeeList.get(0).getDept())))
                .andExpect(jsonPath("$[1].id", is(this.employeeList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", is(this.employeeList.get(1).getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(this.employeeList.get(1).getLastName())))
                .andExpect(jsonPath("$[1].email", is(this.employeeList.get(1).getEmail())))
                .andExpect(jsonPath("$[1].phone", is(this.employeeList.get(1).getPhone())))
                .andExpect(jsonPath("$[1].birthDate", is(this.employeeList.get(1).getBirthDate())))
                .andExpect(jsonPath("$[1].title", is(this.employeeList.get(1).getTitle())))
                .andExpect(jsonPath("$[1].dept", is(this.employeeList.get(1).getDept())))
				.andExpect(jsonPath("$[2].id", is(this.employeeList.get(2).getId().intValue())))
                .andExpect(jsonPath("$[2].firstName", is(this.employeeList.get(2).getFirstName())))
                .andExpect(jsonPath("$[2].lastName", is(this.employeeList.get(2).getLastName())))
                .andExpect(jsonPath("$[2].email", is(this.employeeList.get(2).getEmail())))
                .andExpect(jsonPath("$[2].phone", is(this.employeeList.get(2).getPhone())))
                .andExpect(jsonPath("$[2].birthDate", is(this.employeeList.get(2).getBirthDate())))
                .andExpect(jsonPath("$[2].title", is(this.employeeList.get(2).getTitle())))
                .andExpect(jsonPath("$[2].dept", is(this.employeeList.get(2).getDept())));
	}

	@Test
	public void getSingleEmployee() throws Exception {
		mockMvc.perform(get("/employees/" + this.employeeList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.employeeList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(this.employeeList.get(0).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(this.employeeList.get(0).getLastName())))
                .andExpect(jsonPath("$.email", is(this.employeeList.get(0).getEmail())))
                .andExpect(jsonPath("$.phone", is(this.employeeList.get(0).getPhone())))
                .andExpect(jsonPath("$.birthDate", is(this.employeeList.get(0).getBirthDate())))
                .andExpect(jsonPath("$.title", is(this.employeeList.get(0).getTitle())))
                .andExpect(jsonPath("$.dept", is(this.employeeList.get(0).getDept())));
	}
	
	@Test
	public void notFoundEmployee() throws Exception {
		mockMvc.perform(get("/employees/666")).andExpect(status().isNotFound());
	}
	
	@Test
	public void getEmployeeByLastName() throws Exception {
		mockMvc.perform(get("/employees/lastname/" + this.employeeList.get(0).getLastName()))
                .andExpect(status().isOk())				
                .andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(this.employeeList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(this.employeeList.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(this.employeeList.get(0).getLastName())))
                .andExpect(jsonPath("$[0].email", is(this.employeeList.get(0).getEmail())))
                .andExpect(jsonPath("$[0].phone", is(this.employeeList.get(0).getPhone())))
                .andExpect(jsonPath("$[0].birthDate", is(this.employeeList.get(0).getBirthDate())))
                .andExpect(jsonPath("$[0].title", is(this.employeeList.get(0).getTitle())))
                .andExpect(jsonPath("$[0].dept", is(this.employeeList.get(0).getDept())));
	}
/*
	@Test
	public void testGetByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByDepartament() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}
*/
}
