package org.example.newsmanager.models.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class NewsDataForNewsListBean {
	private int id;
	private String title;
	private String brief;
	private String publicationDate;
	private List<String> tags;
}