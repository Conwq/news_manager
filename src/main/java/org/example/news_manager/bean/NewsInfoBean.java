package org.example.news_manager.bean;

import java.util.Objects;

public class NewsInfoBean {
	private int id;
	private String title;
	private String brief;
	private String content;
	private String publicationDate;
	private String imagePath;

	public NewsInfoBean(){}

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
		NewsInfoBean that = (NewsInfoBean) o;
		return id == that.id && Objects.equals(title, that.title) && Objects.equals(brief, that.brief) && Objects.equals(content, that.content) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(imagePath, that.imagePath);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, brief, content, publicationDate, imagePath);
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
				'}';
	}
}
