package org.example.news_manager.models.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LocaleEntity that = (LocaleEntity) o;
		return id == that.id && Objects.equals(country, that.country) && Objects.equals(language, that.language) && Objects.equals(usersEntity, that.usersEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, country, language, usersEntity);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", country='" + country + '\'' +
				", language='" + language + '\'' +
				", usersEntity=" + usersEntity +
				'}';
	}
}
