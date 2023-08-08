package org.example.news_manager.entity;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "locales")
public class LocaleEntity implements Serializable{

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
	
	public LocaleEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<UserEntity> getUsersEntity() {
		return usersEntity;
	}

	public void setUsersEntity(List<UserEntity> usersEntity) {
		this.usersEntity = usersEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, id, language, usersEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocaleEntity other = (LocaleEntity) obj;
		return Objects.equals(country, other.country) && id == other.id && Objects.equals(language, other.language)
				&& Objects.equals(usersEntity, other.usersEntity);
	}

	@Override
	public String toString() {
		return "LocaleEntity [id=" + id + ", country=" + country + ", language=" + language + ", usersEntity="
				+ usersEntity + "]";
	}
}
