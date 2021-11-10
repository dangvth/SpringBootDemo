package com.tma.SpringBootDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tma.SpringBootDemo.service.imlt.EmployeeService;

/**
 * 
 * @author dangv
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private EmployeeService service;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/employees", "/api/employees/*").hasAnyAuthority("admin", "manager")
				.antMatchers(HttpMethod.POST, "/api/employees").hasAnyAuthority("admin", "manager")
				.antMatchers(HttpMethod.PUT, "/api/employees/*").hasAnyAuthority("admin", "manager")
				.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAnyAuthority("admin", "manager")
				.antMatchers(HttpMethod.GET, "/api/roles", "/api/roles/*").hasAuthority("admin")
				.antMatchers(HttpMethod.POST, "/api/roles").hasAuthority("admin")
				.antMatchers(HttpMethod.PUT, "/api/roles/*").hasAuthority("admin")
				.antMatchers(HttpMethod.DELETE, "/api/roles/*").hasAuthority("admin")
				.anyRequest().authenticated()
//		.anyRequest().permitAll()
				.and().httpBasic().and().csrf().disable();
		http.exceptionHandling().accessDeniedHandler(new SimpleAccessDeniedHandler())
				.authenticationEntryPoint(new SimpleAuthenticationEntryPoint());
	}

}
