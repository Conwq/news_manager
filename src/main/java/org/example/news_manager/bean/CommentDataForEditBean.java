package org.example.news_manager.bean;

import java.util.Objects;

public class CommentDataForEditBean {
	private int id;
	private String text;

	public CommentDataForEditBean() {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CommentDataForEditBean that = (CommentDataForEditBean) o;
		return id == that.id && Objects.equals(text, that.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, text);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", text='" + text + '\'' +
				'}';
	}
}
