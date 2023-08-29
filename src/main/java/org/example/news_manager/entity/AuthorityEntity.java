package org.example.news_manager.entity;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class AuthorityEntity implements Serializable {

	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "authority")
	private String authority;

	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
	private List<UserEntity> userEntity;

	public AuthorityEntity(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<UserEntity> getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(List<UserEntity> userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AuthorityEntity that = (AuthorityEntity) o;
		return id == that.id && Objects.equals(authority, that.authority) && Objects.equals(userEntity, that.userEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, authority, userEntity);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", authority='" + authority + '\'' +
				", userEntity=" + userEntity +
				'}';
	}
}
