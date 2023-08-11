package org.example.news_manager.dto;

import java.io.Serializable;

public class NewsDTO implements Serializable{
	private int id;
	private String title;
	private String brief;
	private String content;
	private String publicationDate;
	private String imagePath;

	public NewsDTO() {
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
	public String toString() {
		return "NewsDTO{" +
				"id=" + id +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				", imagePath='" + imagePath + '\'' +
				'}';
	}
}
