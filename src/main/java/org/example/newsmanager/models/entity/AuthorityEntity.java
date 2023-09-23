package org.example.newsmanager.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class AuthorityEntity implements Serializable {
	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "authority")
	private String authority;
	@OneToMany(mappedBy = "authority", fetch = FetchType.LAZY)
	private List<UserEntity> userEntity;
}