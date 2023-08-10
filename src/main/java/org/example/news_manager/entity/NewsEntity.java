package org.example.news_manager.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
	@Generated(GenerationTime.ALWAYS)
	private String publicationDate;
	
	@Column(name = "image_path")
	private String imagePath;

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewsEntity that = (NewsEntity) o;
		return id == that.id && Objects.equals(title, that.title) && Objects.equals(brief, that.brief) && Objects.equals(content, that.content) && Objects.equals(publicationDate, that.publicationDate);
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
