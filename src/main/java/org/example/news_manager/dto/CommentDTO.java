package org.example.news_manager.dto;

import org.example.news_manager.entity.NewsEntity;
import org.example.news_manager.entity.UserEntity;

public class CommentDTO {
	private int id;
	private String text;
	private String publicationDate;
	private UserEntity userEntity;
	private NewsEntity newsEntity;
	private String username;
	
	public CommentDTO() {
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [id=" + id + ", text=" + text + ", publicationDate=" + publicationDate + ", userEntity="
				+ userEntity + ", newsEntity=" + newsEntity + ", username=" + username + "]";
	}

}
