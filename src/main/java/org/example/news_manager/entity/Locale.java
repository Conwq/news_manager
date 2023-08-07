package org.example.news_manager.entity;


import javax.persistence.*;

@Entity
@Table(name = "locales")
public class Locale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "country")
	private String country;

	@Column(name = "language")
	private String language;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Userr user;
}
