package org.example.newsmanager.models.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentInfoBean {
	private int id;
	private String text;
	private String username;
	private String publicationDate;
}
