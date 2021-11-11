package com.tma.SpringBootDemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tma.SpringBootDemo.dto.EmployeeDTO;
import com.tma.SpringBootDemo.service.imlt.EmployeeService;

/**
 * @author dangv
 *
 */
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	EmployeeService employeeService;

	EmployeeDTO employeeDTO_1 = new EmployeeDTO("admin", "admin", "Dang", "Vo", "admin@gmail.com",
			new String[] { "admin" });
	EmployeeDTO employeeDTO_2 = new EmployeeDTO("manager", "manager", "A", "Nguyen", "manager@gmail.com",
			new String[] { "manager" });
	EmployeeDTO employeeDTO_create = new EmployeeDTO("vthdang", "123456", "Dang", "Vo", "vthdang@tma.com.vn",
			new String[] { "user" });
	EmployeeDTO employeeDTO_update = new EmployeeDTO(1L, new Date(), new Date(), 1, "vthdang", "123456", "Dang", "Vo",
			"vthdang@tma.com.vn", new String[] { "user" });

	MockHttpServletRequestBuilder mockRequest;

	@Test
	void getAllEmployee_success() throws Exception {
		List<EmployeeDTO> employeeDTOs = new ArrayList<>(Arrays.asList(employeeDTO_1, employeeDTO_2));

		Mockito.when(employeeService.findAll()).thenReturn(employeeDTOs);

		mockRequest = MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].email", is("manager@gmail.com")));
	}

	@Test
	void getAllEmployee_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void getAllEmployee_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void getEmployeeById_success() throws Exception {

		Mockito.when(employeeService.findById(1L)).thenReturn(employeeDTO_1);

		mockRequest = MockMvcRequestBuilders.get("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.email", is("admin@gmail.com")));
	}

	@Test
	void getEmployeeById_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees/1").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void getEmployeeById_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void getEmployeeById_badRequest() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees/1}").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("BAD REQUEST-Given param is not matched to controller")));
	}

	@Test
	void getEmployeeById_notFound() throws Exception {

		Mockito.when(employeeService.findById(3L)).thenThrow(NoSuchElementException.class);

		mockRequest = MockMvcRequestBuilders.get("/api/employees/3").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("NOT FOUND DATA EXCEPTION-Given param not exist in the database")));
	}

	@Test
	void createEmployee_success() throws Exception {

		EmployeeDTO returnEmployeeDTO = new EmployeeDTO(1L, new Date(), new Date(), 1, "vthdang", "123456", "Dang",
				"Vo", "vthdang@tma.com.vn", new String[] { "user" });

		Mockito.when(employeeService.save(any())).thenReturn(returnEmployeeDTO);

		mockRequest = MockMvcRequestBuilders.post("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_create))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.email", is("vthdang@tma.com.vn"))).andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	void createEmployee_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_create));

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void createEmployee_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_create))
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void createEmployee_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_create))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}

	@Test
	void updateEmployee_success() throws Exception {

		EmployeeDTO returnEmployeeDTO = new EmployeeDTO(1L, new Date(), new Date(), 1, "vthdang", "123456", "Dang",
				"Vo", "vthdang@tma.com.vn", new String[] { "admin" });

		Mockito.when(employeeService.save(any())).thenReturn(returnEmployeeDTO);

		mockRequest = MockMvcRequestBuilders.put("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_update))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.email", is("vthdang@tma.com.vn"))).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.roles[0]", is("admin")));
	}

	@Test
	void updateEmployee_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_update));

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void updateEmployee_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_update))
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void updateEmployee_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(employeeDTO_update))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}

	@Test
	void deletetEmployeeById_success() throws Exception {

		Mockito.when(employeeService.delete(1L)).thenReturn("Delete successful");

		mockRequest = MockMvcRequestBuilders.delete("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", is("Delete successful")));
	}

	@Test
	void deletetEmployeeById_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/employees/1").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void deleteEmployeeById_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/employees/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void deleteEmployeeById_badRequest() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/employees/1}").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("BAD REQUEST-Given param is not matched to controller")));
	}

	@Test
	void deleteEmployeeById_notFound() throws Exception {

		Mockito.when(employeeService.delete(3L)).thenThrow(EmptyResultDataAccessException.class);

		mockRequest = MockMvcRequestBuilders.delete("/api/employees/3").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError()).andExpect(
				jsonPath("$.message", is("INTERNAL EXCEPTION-exception that occurred somewhere in the code")));
	}

	@Test
	void deleteEmployee_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/employees").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}
	
	@Test
	void searchEmployeeByName_success() throws Exception {

		Mockito.when(employeeService.findAllByQueryDSL("a")).thenReturn(Arrays.asList(employeeDTO_1, employeeDTO_2));

		mockRequest = MockMvcRequestBuilders.get("/api/employees/search?name=a").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].firstName", is("A")));
	}

	@Test
	void searchEmployeeByName_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees/search?name=a").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void searchEmployeeByName_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/employees/search?name=a").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

}
