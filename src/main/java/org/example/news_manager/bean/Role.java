package org.example.news_manager.bean;

public enum Role {
	USER,
	ADMIN;
	
	public String getRoleName() {
		return this.toString().toLowerCase();
	}
}
