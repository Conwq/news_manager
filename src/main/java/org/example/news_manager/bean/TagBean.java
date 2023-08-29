package org.example.news_manager.bean;

import java.util.Objects;

public class TagBean {
	private int id;
	private String name;

	public TagBean(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TagBean tagBean = (TagBean) o;
		return id == tagBean.id && Objects.equals(name, tagBean.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
