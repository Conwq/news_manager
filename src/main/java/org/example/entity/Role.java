package org.example.entity;

public enum Role {
	USER,
	ADMIN;
	
	public String getRoleName() {
		return this.toString().toLowerCase();
	}
}
