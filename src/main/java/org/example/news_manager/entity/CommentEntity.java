package org.example.news_manager.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "comment_id")
	private int id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "publication_date")
	@Generated(GenerationTime.ALWAYS)
	private String publicationDate;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserEntity userEntity;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "news_id", referencedColumnName = "news_id")
	private NewsEntity newsEntity;
	
	public CommentEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public NewsEntity getNewsEntity() {
		return newsEntity;
	}

	public void setNewsEntity(NewsEntity newsEntity) {
		this.newsEntity = newsEntity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CommentEntity that = (CommentEntity) o;
		return id == that.id && Objects.equals(text, that.text) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(userEntity, that.userEntity) && Objects.equals(newsEntity, that.newsEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, text, publicationDate, userEntity, newsEntity);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", text='" + text + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				", userEntity=" + userEntity +
				", newsEntity=" + newsEntity +
				'}';
	}
}
