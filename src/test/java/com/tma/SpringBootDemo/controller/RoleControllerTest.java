package com.tma.SpringBootDemo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tma.SpringBootDemo.dto.RoleDTO;
import com.tma.SpringBootDemo.service.imlt.EmployeeService;
import com.tma.SpringBootDemo.service.imlt.RoleService;

/**
 * @author dangv
 *
 */
@WebMvcTest(RoleController.class)
class RoleControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	RoleService roleService;
	
	@MockBean
	EmployeeService employeeService;

	RoleDTO roleDTO_1 = new RoleDTO("admin");
	RoleDTO roleDTO_2 = new RoleDTO("manager");
	RoleDTO roleDTO_create = new RoleDTO("user");
	RoleDTO roleDTO_update = new RoleDTO(1L, new Date(), new Date(), 1, "admin");

	MockHttpServletRequestBuilder mockRequest;

	@Test
	void getAllRole_success() throws Exception {
		List<RoleDTO> roleDTOs = new ArrayList<>(Arrays.asList(roleDTO_1, roleDTO_2));

		Mockito.when(roleService.findAll()).thenReturn(roleDTOs);

		mockRequest = MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].name", is("manager")));
	}

	@Test
	void getAllRole_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void getAllRole_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void getRoleById_success() throws Exception {

		Mockito.when(roleService.findById(1L)).thenReturn(roleDTO_1);

		mockRequest = MockMvcRequestBuilders.get("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("admin")));
	}

	@Test
	void getRoleById_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/roles/1").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void getRoleById_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void getRoleById_badRequest() throws Exception {

		mockRequest = MockMvcRequestBuilders.get("/api/roles/1}").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("BAD REQUEST-Given param is not matched to controller")));
	}

	@Test
	void getRoleById_notFound() throws Exception {

		Mockito.when(roleService.findById(3L)).thenThrow(NoSuchElementException.class);

		mockRequest = MockMvcRequestBuilders.get("/api/roles/3").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("NOT FOUND DATA EXCEPTION-Given param not exist in the database")));
	}

	@Test
	void createRole_success() throws Exception {

		RoleDTO returnRoleDTO = new RoleDTO(3L, new Date(), new Date(), 1, "user");

		Mockito.when(roleService.save(any())).thenReturn(returnRoleDTO);

		mockRequest = MockMvcRequestBuilders.post("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_create))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("user"))).andExpect(jsonPath("$.id", is(3)));
	}

	@Test
	void createRole_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_create));

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void createRole_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_create))
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void createRole_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.post("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_create))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}

	@Test
	void updateRole_success() throws Exception {

		RoleDTO returnRoleDTO = new RoleDTO(1L, new Date(), new Date(), 1, "user");

		Mockito.when(roleService.save(any())).thenReturn(returnRoleDTO);

		mockRequest = MockMvcRequestBuilders.put("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_update))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("user")));
	}

	@Test
	void updateRole_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_update));

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void updateRole_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_update))
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void updateRole_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.put("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(roleDTO_update))
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}

	@Test
	void deletetRoleById_success() throws Exception {

		Mockito.when(roleService.delete(1L)).thenReturn("Delete successful");

		mockRequest = MockMvcRequestBuilders.delete("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$", is("Delete successful")));
	}

	@Test
	void deletetRoleById_unauthorized() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/roles/1").contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest).andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.message", is("UNAUTHORIZED-not authentication")));
	}

	@Test
	void deleteRoleById_forbidden() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/roles/1").contentType(MediaType.APPLICATION_JSON)
				.with(user("user").password("user").authorities(new SimpleGrantedAuthority("user")));

		mockMvc.perform(mockRequest).andExpect(status().isForbidden())
				.andExpect(jsonPath("$.message", is("FORBIDDEN-not authentication")));
	}

	@Test
	void deleteRoleById_badRequest() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/roles/1}").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message", is("BAD REQUEST-Given param is not matched to controller")));
	}

	@Test
	void deleteRoleById_notFound() throws Exception {

		Mockito.when(roleService.delete(3L)).thenThrow(EmptyResultDataAccessException.class);

		mockRequest = MockMvcRequestBuilders.delete("/api/roles/3").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError()).andExpect(
				jsonPath("$.message", is("INTERNAL EXCEPTION-exception that occurred somewhere in the code")));
	}

	@Test
	void deleteRole_unknown() throws Exception {

		mockRequest = MockMvcRequestBuilders.delete("/api/roles").contentType(MediaType.APPLICATION_JSON)
				.with(user("admin").password("admin").authorities(new SimpleGrantedAuthority("admin")));

		mockMvc.perform(mockRequest).andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message", is("Unknown error")));
	}
}
