package com.fun.club.auth.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by daz on 29/06/2017.
 */
@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	// @GeneratedValue
	// private Long id;
	@Column(name = "user_name", length = 128)
	private String username;
	@Column(name = "password", length = 128)
	private String password;
	@Column(name = "role", length = 128)
	private String role;
	@Column(name = "is_default_password_reset", length = 128)
	private Boolean isDefaultPasswordReset;

	public UserDetails() {
	}

	public UserDetails(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getIsDefaultPasswordReset() {
		return isDefaultPasswordReset;
	}

	public void setIsDefaultPasswordReset(Boolean isDefaultPasswordReset) {
		this.isDefaultPasswordReset = isDefaultPasswordReset;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
