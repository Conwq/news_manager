package org.example.news_manager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int id;
	
	@Column(name = "role_name")
	private String roleName;
	
	@OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
	private List<UserEntity> usersEntity;
	
	public RoleEntity() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserEntity> getUsersEntity() {
		return usersEntity;
	}

	public void setUsersEntity(List<UserEntity> usersEntity) {
		this.usersEntity = usersEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoleEntity that = (RoleEntity) o;
		return id == that.id && Objects.equals(roleName, that.roleName) && Objects.equals(usersEntity, that.usersEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleName, usersEntity);
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", roleName=" + roleName + ", usersEntity=" + usersEntity + "]";
	}
}
