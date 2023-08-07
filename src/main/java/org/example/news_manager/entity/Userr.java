package org.example.news_manager.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Userr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@OneToOne(mappedBy = "user")
	private Locale locale;
}
