package org.example.newsmanager.models.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsInfoBean {
	private int id;
	private String title;
	private String brief;
	private String content;
	private String publicationDate;
	private String imagePath;
}