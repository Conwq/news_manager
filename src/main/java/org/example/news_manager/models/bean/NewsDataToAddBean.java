package org.example.news_manager.models.bean;

import java.util.List;
import java.util.Objects;

public class NewsDataToAddBean {
	private String title;
	private String brief;
	private String content;
	private List<Integer> tags;

	public NewsDataToAddBean(){
	}

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewsDataToAddBean that = (NewsDataToAddBean) o;
		return Objects.equals(title, that.title) && Objects.equals(brief, that.brief) && Objects.equals(content, that.content) && Objects.equals(tags, that.tags);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, brief, content, tags);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"title='" + title + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", tags=" + tags +
				'}';
	}
}
