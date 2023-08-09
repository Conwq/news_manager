package org.example.news_manager.dto;

public enum Role {
	USER,
	ADMIN;
	
	public String getRoleName() {
		return this.toString().toLowerCase();
	}
}
