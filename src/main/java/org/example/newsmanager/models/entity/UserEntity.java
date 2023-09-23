package org.example.newsmanager.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity implements Serializable {
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
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "locale_id", referencedColumnName = "locale_id")
	private LocaleEntity localeEntity;
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
	private List<CommentEntity> comments;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
	private AuthorityEntity authority;
}