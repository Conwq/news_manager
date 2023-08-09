package org.example.news_manager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{
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

	@ManyToOne (fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private RoleEntity roleEntity;
	
	@ManyToOne (fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "locale_id", referencedColumnName = "locale_id")
	private LocaleEntity localeEntity;
	
	public UserEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity role) {
		this.roleEntity = role;
	}
	
	public LocaleEntity getLocaleEntity() {
		return localeEntity;
	}

	public void setLocaleEntity(LocaleEntity localeEntity) {
		this.localeEntity = localeEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id == that.id && Objects.equals(email, that.email) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(roleEntity, that.roleEntity) && Objects.equals(localeEntity, that.localeEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, login, password, roleEntity, localeEntity);
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", email=" + email + ", login=" + login + ", password=" + password + ", localeEntity="
				+ localeEntity + ", role=" + roleEntity + "]";
	}
}
