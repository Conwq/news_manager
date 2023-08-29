package org.example.news_manager.bean;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;


public class UserRegistrationDataBean {
	@NotBlank(message = "- The 'login' field cannot be empty")
	private String login;
	@NotBlank(message = "- The 'email' field cannot be empty")
	@Size(min = 1, max = 10, message = "- The `email` field must be between 1 and 10 characters")
	private String email;
	@NotBlank(message = "- The 'password' field cannot be empty")
	private String password;
	@NotBlank(message = "- The 'confirm password' field cannot be empty")
	private String confirmPassword;
	private int localeId;
	
	public UserRegistrationDataBean(){
		
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

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserRegistrationDataBean that = (UserRegistrationDataBean) o;
		return localeId == that.localeId && Objects.equals(login, that.login) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.confirmPassword);
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, email, password, confirmPassword, localeId);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"login='" + login + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", confirmPassword='" + confirmPassword + '\'' +
				", localeId=" + localeId +
				'}';
	}
}
