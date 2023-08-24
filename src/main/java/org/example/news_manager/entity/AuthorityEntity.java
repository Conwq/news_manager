package org.example.news_manager.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "authority")
	private String authority;

	@OneToMany(mappedBy = "authority")
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
}
