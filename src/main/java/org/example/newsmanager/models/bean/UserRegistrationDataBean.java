package org.example.newsmanager.models.bean;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
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
}