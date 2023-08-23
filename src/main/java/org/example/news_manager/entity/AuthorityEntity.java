package org.example.news_manager.entity;


import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class AuthorityEntity {

	@Id
	@Column(name = "authority")
	private String authority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserEntity userEntity;

	public AuthorityEntity(){

	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
