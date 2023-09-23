package org.example.newsmanager.models.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "locales")
@Data
@NoArgsConstructor
public class LocaleEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "locale_id")
	private int id;
	@Column(name = "country")
	private String country;
	@Column(name = "language")
	private String language;
	@OneToMany(mappedBy = "localeEntity", fetch = FetchType.LAZY)
	private List<UserEntity> usersEntity;
}