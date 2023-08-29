package org.example.news_manager.models.bean;

import java.util.List;
import java.util.Objects;

public class NewsDataForNewsListBean {
	private int id;
	private String title;
	private String brief;
	private String publicationDate;
	private List<String> tags;

	public NewsDataForNewsListBean(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
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
		NewsDataForNewsListBean that = (NewsDataForNewsListBean) o;
		return id == that.id && Objects.equals(title, that.title) && Objects.equals(brief, that.brief) && Objects.equals(publicationDate, that.publicationDate) && Objects.equals(tags, that.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, brief, publicationDate, tags);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				", tags=" + tags +
				'}';
	}
}
