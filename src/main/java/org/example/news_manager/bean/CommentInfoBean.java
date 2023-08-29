package org.example.news_manager.bean;

import java.util.Objects;

public class CommentInfoBean {
	private int id;
	private String text;
	private String username;
	private String publicationDate;

	public CommentInfoBean() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		CommentInfoBean that = (CommentInfoBean) o;
		return id == that.id && Objects.equals(text, that.text) && Objects.equals(username, that.username) && Objects.equals(publicationDate, that.publicationDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, text, username, publicationDate);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", text='" + text + '\'' +
				", username='" + username + '\'' +
				", publicationDate='" + publicationDate + '\'' +
				'}';
	}
}
