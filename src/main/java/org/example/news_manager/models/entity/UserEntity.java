package org.example.news_manager.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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

	@Column(name = "username")
	private String login;

	@Column(name = "password")
	private String password;
	
	@ManyToOne (fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "locale_id", referencedColumnName = "locale_id")
	private LocaleEntity localeEntity;
	
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private List<CommentEntity> comments;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
	private AuthorityEntity authority;
	
	public UserEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AuthorityEntity getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityEntity authority) {
		this.authority = authority;
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

	public LocaleEntity getLocaleEntity() {
		return localeEntity;
	}

	public void setLocaleEntity(LocaleEntity localeEntity) {
		this.localeEntity = localeEntity;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserEntity that = (UserEntity) o;
		return id == that.id && Objects.equals(email, that.email) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(localeEntity, that.localeEntity) && Objects.equals(comments, that.comments) && Objects.equals(authority, that.authority);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, email, login, password, localeEntity, comments, authority);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", email='" + email + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", localeEntity=" + localeEntity +
				", comments=" + comments +
				", authority=" + authority +
				'}';
	}
}