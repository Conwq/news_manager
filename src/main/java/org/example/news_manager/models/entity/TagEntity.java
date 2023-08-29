package org.example.news_manager.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
public class TagEntity implements Serializable {

	@Column(name = "tag_id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
	private List<NewsEntity> news;

	public TagEntity(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<NewsEntity> getNews() {
		return news;
	}

	public void setNews(List<NewsEntity> news) {
		this.news = news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TagEntity tagEntity = (TagEntity) o;
		return id == tagEntity.id && Objects.equals(name, tagEntity.name) && Objects.equals(news, tagEntity.news);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, news);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", name='" + name + '\'' +
				", news=" + news +
				'}';
	}
}
