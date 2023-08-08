package org.example.news_manager.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	public int hashCode() {
		return Objects.hash(id, roleName, usersEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		return id == other.id && Objects.equals(roleName, other.roleName)
				&& Objects.equals(usersEntity, other.usersEntity);
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", roleName=" + roleName + ", usersEntity=" + usersEntity + "]";
	}
}
