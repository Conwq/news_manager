package org.example.news_manager.models.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
	
	@OneToMany(mappedBy = "newsEntity")
	private List<CommentEntity> comments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "news_tags",
			joinColumns = @JoinColumn(name = "news_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<TagEntity> tags;

	public NewsEntity(){
	}

	public int getId() {
		return id;
	}

	public List<TagEntity> getTags() {
		return tags;
	}

	public void setTags(List<TagEntity> tags) {
		this.tags = tags;
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
	
	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewsEntity that = (NewsEntity) o;
		return id == that.id && Objects.equals(title, that.title) && Objects.equals(brief, that.brief) && Objects.equals(content, that.content) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(imagePath, that.imagePath) && Objects.equals(comments, that.comments) && Objects.equals(tags, that.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, brief, content, publicationDate, imagePath, comments, tags);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				", imagePath='" + imagePath + '\'' +
				", comments=" + comments +
				", tags=" + tags +
				'}';
	}
}