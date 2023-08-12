package org.example.news_manager.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDTOForRegistration {
	@NotBlank(message = "Поле Login не должно быть пустым")
	private String login;
	@NotBlank(message = "Поле Email не должно содержать пробелов")
	@Size(min = 1, max = 10, message = "Поле Email должно быть от 1 до 10 символов")
	private String email;
	@NotBlank(message = "Поле Password не должно быть пустым и содержать пробелов")
	private String password;
	@NotBlank(message = "Поле Confirm Password не должно быть пустым и содержать пробелов")
	private String confirmPassword;
	private int localeId;
	
	public UserDTOForRegistration(){
		
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
}
