package org.example.news_manager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "news")
public class NewsEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private int id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "brief")
	private String brief;

	@Column(name = "content")
	private String content;

	@Column(name = "publication_date")
	private String publicationDate;

	public NewsEntity(){
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewsEntity newsEntity = (NewsEntity) o;
		return id == newsEntity.id && Objects.equals(title, newsEntity.title) && Objects.equals(brief, newsEntity.brief) && Objects.equals(content, newsEntity.content) && Objects.equals(publicationDate, newsEntity.publicationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, brief, content, publicationDate);
	}

	@Override
	public String toString() {
		return getClass().getName() +" {" +
				"id=" + id +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				'}';
	}
}
