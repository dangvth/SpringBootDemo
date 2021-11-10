package com.tma.SpringBootDemo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Employee entity
 * 
 * @author dangv
 *
 */
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity implements UserDetails {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	/**
	 * Initialize a password encoder
	 * 
	 * @return the {@link PasswordEncoder} help to encode the password
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Get username of employee
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set username for employee
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get password of employee
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password for employee
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = passwordEncoder().encode(password);
	}

	/**
	 * Get all roles of employee
	 * 
	 * @return the list of roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Set roles for employee
	 * 
	 * @param roles the list of roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Get first name of employee
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set first name for employee
	 * 
	 * @param firstName the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get last name of employee
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set last name for employee
	 * 
	 * @param lastName the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get email of employee
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email for employee
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the authorities granted to the employee
	 * 
	 * @return the authorities, sorted by natural key (never <code>null</code>)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : this.roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	/**
	 * Indicates whether the employee's account has expired. An expired account
	 * cannot beauthenticated
	 * 
	 * @return true if the employee's account is valid (ie non-expired), false if no
	 *         longer valid (ie expired)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the employee is locked or unlocked. A locked user cannot be
	 * authenticated
	 * 
	 * @return true if the employee is not locked, false otherwise
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Indicates whether the employee's credentials (password) has expired. Expired
	 * credentials prevent authentication
	 * 
	 * @return true if the employee's credentials are valid (ie non-expired), false
	 *         if no longer valid (ie expired)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Indicates whether the employee is enabled or disabled. A disabled user cannot
	 * be authenticated
	 * 
	 * @return true if the employee is enabled, false otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
