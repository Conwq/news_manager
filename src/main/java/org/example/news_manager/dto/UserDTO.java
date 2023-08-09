package org.example.news_manager.dto;

import java.io.Serializable;
import java.util.Locale;

public class UserDTO implements Serializable{
	private int id;
	private String email;
	private String login;
	private String password;
	private Locale locale;
	private Role role;
	
	public UserDTO() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getRoleName() {
		return role.getRoleName();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", email=" + email + ", login=" + login + ", password=" + password + ", locale="
				+ locale + ", role=" + role + "]";
	}
}
