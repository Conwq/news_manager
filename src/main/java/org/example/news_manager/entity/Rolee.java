package org.example.news_manager.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rolee {

	private int id;
	private String role_name;
}
