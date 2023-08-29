package org.example.news_manager.bean;

import java.util.Locale;
import java.util.Objects;

public class UserInfoBean {
	private int id;
	private String email;
	private String login;
	private String password;
	private Locale locale;

	public UserInfoBean() {
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserInfoBean that = (UserInfoBean) o;
		return id == that.id && Objects.equals(email, that.email) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(locale, that.locale);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, login, password, locale);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", locale=" + locale +
				'}';
	}
}
